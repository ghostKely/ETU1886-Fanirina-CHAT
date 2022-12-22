package server;
import java.io.*;
import java.net.*;
import java.util.*;
import client.*;
import note.Note;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadlist ;
    private PrintWriter output;

    public ServerThread(Socket socket,ArrayList<ServerThread> threadlist ) {
        this.socket = socket;
        this.threadlist = threadlist;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),true);

            while (true) {
                String outputString = input.readLine();
                if (outputString.equals("exit")) {
                    break;
                }
                printToALlClients(outputString);
                System.out.println(outputString);
                Note note = new Note("data.txt");
                note.writer(outputString);
                note.writer("/");
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Error occured" + e.getStackTrace());
        }
    }

    public void printToALlClients(String outputString) {
        for(ServerThread sT: threadlist){
            sT.output.println(outputString);
        }
    }

    

}
