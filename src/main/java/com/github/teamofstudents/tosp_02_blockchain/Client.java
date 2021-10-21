package com.github.teamofstudents.tosp_02_blockchain;
import java.net.*;
import java.io.*;


public class Client
{

  public static void main(String[] args) {

    try {
        
        // Socket socket = new Socket("2a02:908:1570:d2a0:c5c:9613:4cf8:58ed", 33333);
        Socket socket = new Socket("localhost", 33333);
        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF("Hallo Maurice, nochmal zum 2.!");
        System.out.println("Daten gesendet");    
        stream.flush();
        stream.close();
        socket.close();

    }

    catch(Exception e) {

      System.out.println(e.getMessage());
    }

  }


}