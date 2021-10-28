package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public class MiningThread extends Thread {
    private static Logger logger = (Logger) LoggerFactory.getLogger(MiningThread.class);
    private Thread thread;
    private String threadName;

    private int maxChainLength = 40;
    private int leadingZeros = 5;
    private long nonceLimit = 100000000;

    public MiningThread(String threadName) {
        this.threadName = threadName;
        logger.debug("Creating " + threadName);

    }

    public void run() {
        logger.debug("Running " + threadName);
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
        data.add("Dies ist Block Nr 0 berechnet durch: " + thread);
        Blockchain myBlockchain = new Blockchain(data, leadingZeros, nonceLimit);

        for (int i = 1; i <= maxChainLength; i++) {
            data = new ArrayList<String>();
            data.add("Dies ist Block Nr " + i + " berechnet durch: " + thread);
            myBlockchain.createBlock(data);
        }

        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
        double durationInSec = (double) duration / 1000;
        logger.debug(myBlockchain.toString());
        logger.info("Running time: {} Sekunden für {} Blocks = {} Sek./Block", durationInSec, myBlockchain.size(), durationInSec / myBlockchain.size());
        
        logger.debug("Thread " + threadName + " exiting.");
    }

    public void start() {
        logger.debug("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

}
