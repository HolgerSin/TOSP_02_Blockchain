package com.github.teamofstudents.tosp_02_blockchain;
import java.net.*;
import java.io.*;

// Aufruf mit:
// cd target/classes/
// java com.github.teamofstudents.tosp_02_blockchain.Server
// java com.github.teamofstudents.tosp_02_blockchain.Client

public class Server
{


  public static void main(String[] args) {

    try {

      ServerSocket serverSocket = new ServerSocket(33333);
      System.out.println("Server wartet auf Clients....");
      Socket clientSocket = serverSocket.accept();

      DataInputStream stream = new DataInputStream(clientSocket.getInputStream());
      String message = (String)stream.readUTF();

      System.out.println(message);
      stream.close();
      serverSocket.close();

    }

    catch(Exception e) {

      System.out.println(e.getMessage());

    }

  }


}