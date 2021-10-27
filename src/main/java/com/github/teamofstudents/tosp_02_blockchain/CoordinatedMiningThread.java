package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public class CoordinatedMiningThread extends Thread {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CoordinatedMiningThread.class);
    // private Thread thread;
    private String threadName;
    private Blockchain blockchain;

    private int maxChainLength = 4;
    private int leadingZeros = 6;
    private long nonceLimit = 100000000;

    // public CoordinatedMiningThread(String threadName) {
    //     this.threadName = threadName;
    //     logger.debug("Creating " + this + " State is now: " + this.getState().toString());
    // }

    public CoordinatedMiningThread(String threadName, Blockchain blockchain){
        super(threadName);
        this.threadName = threadName;
        this.blockchain = blockchain;
        logger.debug("Creating " + this + " State is now: " + this.getState().toString());
    }

    public void run() {
        logger.debug("Running " + this + " State is now: " + this.getState().toString());
        // try {
        // for (int i = 4; i > 0; i--) {
        // logger.debug("Thread: " + threadName + ", " + i);
        // // Let the thread sleep for a while.
        // Thread.sleep(1000);
        // }
        // } catch (InterruptedException e) {
        // logger.debug("Thread " + threadName + " interrupted.");
        // }


        long startTime = new Date().getTime();
        ArrayList<String> data = new ArrayList<String>();
        
        if (blockchain == null) {
            data.add("Dies ist Block Nr 0 berechnet durch: " + this);
            blockchain = new Blockchain(data, leadingZeros, nonceLimit);
        } else {
            int blockIndex = blockchain.size();
            data.add("Dies ist Block Nr " + blockIndex + " berechnet durch: " + this);
            blockchain.addBlock(data);
        }
        

        // for (int i = 1; i <= maxChainLength; i++) {
        //     data = new ArrayList<String>();
        //     data.add("Dies ist Block Nr " + i + " berechnet durch: " + thread);
        //     blockchain.addBlock(data);
        // }

        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
        double durationInSec = (double) duration / 1000;
        logger.info(blockchain.toString());
        logger.info("Running time: {} Sekunden für {} Blocks = {} Sek./Block", durationInSec, blockchain.size(), durationInSec / blockchain.size());
        
        logger.debug("Thread " + this + " exiting." + " State is now: " + this.getState().toString());
    }

    public void start() {
        logger.debug("Starting " + this + " State is now: " + this.getState().toString());
        // if (thread == null) {
            // thread = new Thread(this, threadName);
            super.start();
            logger.debug("Started " + this + " State is now: " + this.getState().toString());
        // }
        // else{
        //     logger.debug("Starting failed, " + threadName + " already started");
        // }
    }

}
