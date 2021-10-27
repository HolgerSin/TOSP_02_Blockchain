package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;


import org.slf4j.LoggerFactory;
// import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Block {
    private static Logger logger = (Logger) LoggerFactory.getLogger(Block.class);
    private long timestamp;
    private String lastHash;
    private String hash;
    private ArrayList<String> data;
    private long nonce;
    private int id;
    // private static long counter = 0;

    // private static int leadingZeros = 6;
    // private static long nonceLimit = 100000000;

    public Block(int id, long timestamp, String lastHash, String hash, ArrayList<String> data, long nonce) {
        this.id = id;
        this.timestamp = timestamp;
        this.lastHash = lastHash;
        this.hash = hash;
        this.data = data;
        this.nonce = nonce;

        
    }

    

    @Override
    public String toString() {
        return "\n Block " + id  + 
            "\n\t [data=" + data + 
            "\n\t lastHash=" + lastHash + 
            "\n\t hash=" + hash + 
            "\n\t nonce=" + nonce + 
            "\n\t timestamp=" + timestamp + "]";
    }



    public static Block genesis(ArrayList<String> data, String pattern, long nonceLimit){
        int newID = 0;
        long timestamp = new Date().getTime();
        String lastHash = "Genesis";
        String[] returnValues = leadingZeroHash(newID, timestamp, lastHash, data, pattern, nonceLimit);
        String hash = returnValues[0];
        long nonce = Long.valueOf(returnValues[1]);
        return new Block(newID, timestamp, lastHash, hash, data, nonce);
    }

    public static Block mineBlock(Block lastBlock, ArrayList<String> data, String pattern, long nonceLimit){
        int newID = lastBlock.id + 1;
        long timestamp = new Date().getTime();
        String lastHash = lastBlock.hash;
        String[] returnValues = leadingZeroHash(newID, timestamp, lastHash, data, pattern, nonceLimit);
        String hash = returnValues[0];
        long nonce = Long.valueOf(returnValues[1]);
        return new Block(newID, timestamp, lastHash, hash, data, nonce);
    }

    private static String[] leadingZeroHash(int newID, long timestamp, String lastHash, ArrayList<String> data, String pattern, long nonceLimit) {
        long startTime = new Date().getTime();
        boolean keepRunning =true;
        // String pattern = "^0{"+leadingZeros+"}\\w*";
        long nonce = 0;
        String hash = hash(newID + "" + timestamp + lastHash + data + nonce);

        while (!hash.matches(pattern) && nonce < nonceLimit && keepRunning) {
            nonce++;
            hash = hash(newID + "" + timestamp + lastHash + data + nonce);
            if (nonce % 1000000 == 0) {
                if (App.isBlockFound()) {
                    keepRunning = false;
                    logger.trace("Other Thread has found block, aborting, Block {}, counter: {}, timestamp: {}", newID,  nonce, timestamp);
                } else{
                    logger.trace("Still running, Block {}, counter: {}, timestamp: {}", newID,  nonce, timestamp);
                }
            }
        }
        if (!hash.matches(pattern)) {
            logger.warn("No valid Hash found");   
        }
        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
     
        logger.debug("Block {}, counter: {}, Laufzeit {} ms mit {} Berechnungen pro Sekunde, \n\t Hash {}, timestamp: {}", newID, nonce, duration, nonce*1000/(duration), hash, timestamp);
        App.setBlockFound(true);
        return new String[] {hash, String.valueOf(nonce)};
    }

    private static String hash(String message) {
        return DigestUtils.sha256Hex(message);
    }



    public String getLastHash() {
        return lastHash;
    }



    public String getHash() {
        return hash;
    }

    
    
}
