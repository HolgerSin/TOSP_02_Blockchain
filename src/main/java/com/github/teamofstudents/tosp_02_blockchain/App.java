package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
// import ch.qos.logback.classic.Level;

/**
 * Hello world!
 *
 */
public class App{
    private static Logger logger = (Logger) LoggerFactory.getLogger(App.class);
    private static Blockchain masterBlockchain;
    private static boolean blockFound;
    private static int THREAD_NUMBER = 5;
    private static final int MAX_CHAIN_LENGHTH = 2;

    private static ArrayList<Thread> threads;
    
    public static void main(String[] args) {

        logger.info("Hello World! " + new Date());

        // MiningThread miningThread1 = new MiningThread("Thread-1");
        // miningThread1.start();
        // MiningThread miningThread2 = new MiningThread("Thread-2");
        // miningThread2.start();

        
        long startTime = new Date().getTime();


        for (int i = 1; i <= MAX_CHAIN_LENGHTH; i++) {

            threads = new ArrayList<>();
            blockFound = false;
            for (int threadCounter = 1; threadCounter <= THREAD_NUMBER; threadCounter++) {
                String threadSymbol = "_" + threadCounter + "_";
                for (int j = 1; j < threadCounter + 1; j++) {
                    threadSymbol = "*" + threadSymbol + "*";
                }
                threadSymbol = "_" + threadSymbol + "_";
                String threadName = "CoordMiningThread-" + threadSymbol;
                threads.add(new CoordinatedMiningThread(threadName, masterBlockchain));
            }
            for (Thread thread : threads) {
                thread.start();
            }
            

            // CoordinatedMiningThread miningThread1 = new CoordinatedMiningThread("CoordMiningThread-1",
            //         masterBlockchain);
            // logger.debug(miningThread1 + " has state " +
            // miningThread1.getState().toString());
            // CoordinatedMiningThread miningThread2 = new CoordinatedMiningThread("CoordMiningThread-2",
            //         masterBlockchain);
            // miningThread1.start();
            // miningThread2.start();
            // logger.debug(miningThread1 + " has state " +
            // miningThread1.getState().toString());

            try {
                // sleep(500);
                for (Thread thread : threads) {
                    thread.join();
                    logger.debug(thread + "should be terminated, state is " + thread.getState().toString());
                }
                // miningThread1.join();
                // miningThread2.join();
                logger.debug("All Threads terminated");
                // logger.debug(miningThread1 + " has state " + miningThread1.getState().toString());
                // logger.debug(miningThread2 + " has state " + miningThread1.getState().toString());
                // sleep(2000);

            } catch (Exception e) {
                logger.error("Interrupted");
            }
        }

        logger.info("Blockchain gesamt: {}", masterBlockchain);

        
        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
        double durationInSec = (double) duration / 1000;
        long sumNonce = 0;
        for (Block block : masterBlockchain.getChain()) {
            sumNonce += block.getNonce();
        }
        logger.info("Running time: {} Sekunden für {} Blocks = {} Sek./Block, Berechnungen pro Sekunde: {}, Threads: {}", durationInSec, masterBlockchain.size(), durationInSec / masterBlockchain.size(), sumNonce * 1000 / duration, THREAD_NUMBER);
        

    }

    public static boolean isBlockFound() {
        return blockFound;
    }

    public static void setBlockFound(boolean blockFound) {
        App.blockFound = blockFound;
    }

    public static Blockchain getMasterBlockchain() {
        return masterBlockchain;
    }

    public static void setMasterBlockchain(Blockchain masterBlockchain) {
        App.masterBlockchain = masterBlockchain;
    }

}
