package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread{
    private Socket Socket;
    private Vector<Server> Threads;
    private PrintWriter Writer;

    public Server(Socket Socket, Vector<Server> Threads) {
        this.Socket=Socket;
        this.setThreads(Threads);
    }

/*================GETTERS================ */
    public Socket getSocket() {return this.Socket;}
    public Vector<Server> getThreads() {return this.Threads;}
    public PrintWriter getWriter() {return this.Writer;}

/*==============SETTERS============== */
    // public void setSocket(Socket socket) {this.Socket=Socket;}
    public void setThreads(Vector<Server> Threads) {this.Threads=Threads;} 
    public void setWriter(PrintWriter Writer) {this.Writer=Writer;}

    public void allClients(String message) {
        PrintWriter Writer = this.getWriter();
        for(Server autresPers: Threads) {
            autresPers.Writer.println(message);
        }
    }

    public void run() {
        String user = "Client";
        try {
            InputStream in = this.getSocket().getInputStream();
            BufferedReader Input = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.getSocket().getOutputStream();
            PrintWriter writer = new PrintWriter(out,true);
            this.setWriter(writer);

            while (true) {
                String message = Input.readLine();
                user = message;
                this.allClients(message);
                if (message.equals("leave")) {
                    user = message;
                    break;
                }//why not atao button de asina key listener
                System.out.println(message);
            }

        } catch (Exception e) {
            System.out.println(user+" left");   
        }
    }
}
