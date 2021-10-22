package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;


import org.slf4j.LoggerFactory;
// import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Block {
    private static Logger logger = (Logger) LoggerFactory.getLogger(Block.class);
    private Date timestamp;
    private String lastHash;
    private String hash;
    private ArrayList<String> data;
    private long nonce;
    private int id;
    private static long counter = 0;

    private static int leadingZeros = 5;
    private static int nonceLimit = 10000000;

    public Block(Date timestamp, String lastHash, String hash, ArrayList<String> data, long nonce) {
        this.timestamp = timestamp;
        this.lastHash = lastHash;
        this.hash = hash;
        this.data = data;
        this.nonce = nonce;

        // logger.setLevel(Level.INFO);
        // logger.setLevel(Level.DEBUG);
        // logger.setLevel(Level.TRACE);
    }

    

    @Override
    public String toString() {
        return "\n Block \n\t [data=" + data + 
            "\n\t lastHash=" + lastHash + 
            "\n\t hash=" + hash + 
            "\n\t nonce=" + nonce + 
            "\n\t timestamp=" + timestamp.getTime() + "]";
    }



    public static Block genesis(ArrayList<String> data){
        Date timestamp = new Date();
        String lastHash = "Genesis";
        String hash = leadingZeroHash(timestamp + lastHash + data);
        return new Block(timestamp, lastHash, hash, data, counter);
    }

    public static Block mineBlock(Block lastBlock, ArrayList<String> data){
        Date timestamp = new Date();
        String lastHash = lastBlock.hash;
        String hash = leadingZeroHash(timestamp + lastHash + data);
        return new Block(timestamp, lastHash, hash, data, counter);
    }

    private static String leadingZeroHash(String message) {
        long startTime = new Date().getTime();
        
        String pattern = "^0{"+leadingZeros+"}\\w*";
        counter = 0;
        String hash = hash(message + counter);

        while (!hash.matches(pattern) && counter < nonceLimit) {
            counter++;
            hash = hash(message + counter);
            if (counter % 1000000 == 0) {
                logger.trace("Still running, counter: {}", counter);
            }
        }
        if (!hash.matches(pattern)) {
            logger.warn("No valid Hash found");   
        }
        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
     
        logger.debug("counter: {}, Laufzeit {} ms mit {} Berechnungen pro Sekunde, \n\t Hash {} ", counter, duration, counter*1000/(duration), hash);
        
        return hash;
    }

    private static String hash(String message) {
        return DigestUtils.sha256Hex(message);
    }
    
}
