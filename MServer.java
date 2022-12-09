package mserver;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import server.*;
import fonction.*;
import fenetre.*;

public class MServer {
    public static void main(String[] args){
        Vector<Server> Threads = new Vector<Server>();

        try(ServerSocket server = new ServerSocket(1234)) {
            
            while (true) {
                Socket Socket = server.accept();
                Server Server = new Server(Socket, Threads);
                System.out.println(Server.getSocket());
                BufferedReader read = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                System.out.println(read.readLine());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Socket.getOutputStream()));
                writer.write(read.readLine());
                Threads.add(Server);
                Server.start();
            }
        } catch (Exception e) {
            System.out.println("tsy mety e");
        }
    }
}
