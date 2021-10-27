package com.github.teamofstudents.tosp_02_blockchain;

// import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
// import ch.qos.logback.classic.Level;

/**
 * Hello world!
 *
 */
public class App extends Thread {
    private static Logger logger = (Logger) LoggerFactory.getLogger(App.class);
    private static Blockchain masterBlockchain;
    private static boolean blockFound = false;


    // private ArrayList<String> data;
    public static void main(String[] args) {

        logger.info("Hello World! " + new Date());

        // MiningThread miningThread1 = new MiningThread("Thread-1");
        // miningThread1.start();
        // MiningThread miningThread2 = new MiningThread("Thread-2");
        // miningThread2.start();

        CoordinatedMiningThread miningThread1 = new CoordinatedMiningThread("CoordMiningThread-1", masterBlockchain);
        // logger.debug(miningThread1 + " has state " + miningThread1.getState().toString());
        CoordinatedMiningThread miningThread2 = new CoordinatedMiningThread("CoordMiningThread-2", masterBlockchain);
        miningThread1.start();
        miningThread2.start();
        // logger.debug(miningThread1 + " has state " + miningThread1.getState().toString());
        
        try {
            // sleep(500);
            miningThread1.join();
            logger.debug("Thread 1 finished !?");
            logger.debug(miningThread1 + " has state " + miningThread1.getState().toString());
            // sleep(2000);
            
        } catch (Exception e) {
            logger.warn("Interrupted");
        }
    

    }

    public static boolean isBlockFound() {
        return blockFound;
    }

    public static void setBlockFound(boolean blockFound) {
        App.blockFound = blockFound;
    }

}
