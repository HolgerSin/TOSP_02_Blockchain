package com.github.teamofstudents.tosp_02_blockchain;

import java.util.ArrayList;
import java.util.Date;


import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

    

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = (Logger) LoggerFactory.getLogger(Block.class);
    // private ArrayList<String> data;
    public static void main( String[] args )
    {
        System.out.println( "Hello World! " + new Date());

        // ArrayList<String> data = new ArrayList<String>();
        // data.add("Testdata");
        // Block myBlock = Block.genesis(data);
        // System.out.println(myBlock);
        // data.add("mehr Daten");
        // Block newBlock = Block.mineBlock(myBlock, data);
        // System.out.println(newBlock);

        long startTime = new Date().getTime();
        Blockchain myBlockchain = new Blockchain();

        for (int i = 1; i <= 4; i++) {
            ArrayList<String> data = new ArrayList<String>();
            data.add("Dies ist Block Nr "+i);
            myBlockchain.addBlock(data);
        }
        long endTime = new Date().getTime();
        long duration = endTime - startTime == 0 ? 1 : endTime - startTime;
        
        logger.info(myBlockchain.toString());
        logger.info("Running time: {} Sekunden", (double) duration/1000);
        
    }
}
