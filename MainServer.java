package server;
import java.io.*;
import java.net.*;
import java.util.*;
import client.*;

/**
 * MainS
 */
public class MainServer {

    public static void main(String[] args) {
        ArrayList<ServerThread> threadlist = new ArrayList<>();
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadlist);
                threadlist.add(serverThread);
                serverThread.start();
                //System.out.println();
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Error occured in main " + e.getStackTrace());
        }
    }
}