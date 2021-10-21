package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chain = new ArrayList<Block>();

    public Blockchain() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Genesis Data");
        chain.add(Block.genesis(data));
    }
    
    public void addBlock(ArrayList<String> data) {
        chain.add(Block.mineBlock(chain.get(chain.size()-1), data));
    }

    @Override
    public String toString() {
        return "Blockchain [chain=" + chain + "]";
    }

    

}
