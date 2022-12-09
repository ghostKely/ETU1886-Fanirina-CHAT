package client;
import java.io.*;
import java.net.*;

public class Client extends Thread{
    private Socket Socket;
    private BufferedReader Input;

    public Client(Socket Socket)  throws IOException{
        this.setSocket(Socket);
        this.setBuff(new BufferedReader(new InputStreamReader(this.getSocket().getInputStream())));
    }

/*================GETTERS================ */
    public Socket getSocket() {return this.Socket;}  
    public BufferedReader getBuff() {return this.Input;}

/*==============SETTERS============== */
    public void setSocket(Socket Socket) {this.Socket=Socket;}
    public void setBuff(BufferedReader Input) {this.Input=Input;}

    public void run() {
        try {
            while(true) {
                String rep = this.getBuff().readLine();
                System.out.println(rep);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            try {
                this.getBuff().close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
