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
public class App 
{
    private static Logger logger = (Logger) LoggerFactory.getLogger(App.class);
    // private ArrayList<String> data;
    public static void main( String[] args )
    {
        // logger.setLevel(Level.INFO);
        // logger.setLevel(Level.DEBUG);
        // logger.setLevel(Level.TRACE);


        logger.info( "Hello World! " + new Date());

        // ArrayList<String> data = new ArrayList<String>();
        // data.add("Testdata");
        // Block myBlock = Block.genesis(data);
        // System.out.println(myBlock);
        // data.add("mehr Daten");
        // Block newBlock = Block.mineBlock(myBlock, data);
        // System.out.println(newBlock);

        MiningThread miningThread1 = new MiningThread( "Thread-1");
        miningThread1.start();
        MiningThread miningThread2 = new MiningThread( "Thread-2");
        miningThread2.start();

        // long startTime = new Date().getTime();
        // Blockchain myBlockchain = new Blockchain();

        // for (int i = 1; i <= 4; i++) {
        //     ArrayList<String> data = new ArrayList<String>();
        //     data.add("Dies ist Block Nr "+i);
        //     myBlockchain.addBlock(data);
        // }
        // logger.trace(myBlockchain.toString());
        // long endTime = new Date().getTime();
        // long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
        
        
        // logger.info("Running time: {} Sekunden", (double) duration/1000);
        
    }
}
