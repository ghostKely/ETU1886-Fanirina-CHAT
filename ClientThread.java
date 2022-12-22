package client;
import java.io.*;
import java.net.*;
import java.util.*;
import server.*;

public class ClientThread extends Thread{
    private Socket socket;
    private BufferedReader input;

    public  ClientThread(Socket s) throws IOException{
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            while (true) {
                String response = input.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
