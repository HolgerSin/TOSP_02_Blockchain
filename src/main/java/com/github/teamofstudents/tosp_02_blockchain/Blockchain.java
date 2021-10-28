package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;


import org.slf4j.LoggerFactory;
// import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class Blockchain {
    private static Logger logger = (Logger) LoggerFactory.getLogger(Blockchain.class);

    private ArrayList<Block> chain = new ArrayList<Block>();
    // private int leadingZeros;
    private long nonceLimit;
    private String pattern;

    public Blockchain(ArrayList<String> data, int leadingZeros, long nonceLimit) {
        // ArrayList<String> data = new ArrayList<String>();
        // data.add("Genesis Data");
        // this.leadingZeros = leadingZeros;
        this.nonceLimit = nonceLimit;
        this.pattern = "^0{"+leadingZeros+"}\\w*";
        // chain.add(Block.genesis(data, pattern, nonceLimit));
        // CONTINUE HERE
        // Block newBlock = Block.genesis(data, pattern, nonceLimit);
        // addBlockToChain(newBlock);
        createBlock(data);
    }
    
    public boolean createBlock(ArrayList<String> data) {
        int chainSize = chain.size();
        Block lastBlock = chain.size() == 0 ? null : chain.get(chain.size()-1);
        Block newBlock = Block.mineBlock(lastBlock, data, pattern, nonceLimit);
        // addBlockToChain(newBlock);
        // SYNCRONIZED?
        if (newBlock.getHash().matches(pattern) && chain.size() == chainSize) {
            chain.add(newBlock);
            logger.debug("Block added to Chain: {}", newBlock);
            return true;
        } else{
            logger.warn("From Blockchain Class: No valid Hash found OR other Thread already found Hash");
            return false;
        }
    }


    // private void addBlockToChain(Block block) {
    //     if (block.getHash().matches(pattern)) {
    //         chain.add(block);
    //     } else{
    //         logger.warn("From Blockchain Class: No valid Hash found");
    //     }
    // }


    @Override
    public String toString() {
        return "Blockchain [chain=" + chain + "]";
    }

    public int size() {
        return chain.size();
    }

    public Block get(int index) {
        return chain.get(index);
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

        

}
