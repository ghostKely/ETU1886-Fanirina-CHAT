package utilisateur;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
// import serveur.*;

import javafx.scene.control.TextArea;
import note.Note;

/**
 * Client
 */
public class Client {

    JFrame frameClient;
    JPanel panel;
    static JPanel pannnelShow ;
    JTextField textField;
    static JTextArea textArea;
    JButton btn;
    static ServerSocket server ;
    static Socket con;
    JScrollBar scrollPane;
    static JLabel labelServer;
    DataInputStream input;
    DataOutputStream output;

    public static void main(String[] args) throws UnknownHostException, IOException{
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frameClient.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

        con = new Socket("localhost", 5000);
		 while (true) {
			try {
				
				DataInputStream input = new DataInputStream(con.getInputStream());
                System.out.println("ATO");
				String string = input.readUTF();
                System.out.println("ITO "+string);
				textArea.setText(textArea.getText() + "\n" + "[Server]: " + string);
			} catch (Exception ev) {
				 //textArea.setText(textArea.getText()+" \n" +"Network issues ");

			}
		}
		
	}

    public Client() {
		initialize();
	}

    private void initialize(){
        frameClient = new JFrame();
        frameClient.setTitle("Chat client");
        frameClient.setDefaultCloseOperation(frameClient.EXIT_ON_CLOSE);
        frameClient.setSize(600, 700);


        panel = new JPanel(); // the panel is not visible in output
        panel.setBounds(0,0,600,200);

        labelServer = new JLabel("Ecriver");

        textField = new JTextField(20); 
        textField.setFont(new Font("time",Font.BOLD,18));
        textField.setBackground(Color.LIGHT_GRAY);

        scrollPane = new JScrollBar();
        btn = new JButton("Send");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please write some text !");
                 
                    }
                    else if(textField.isFocusable()){
                        btn.setEnabled(true);
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            PrintWriter output = new PrintWriter(con.getOutputStream(),true);
                            textArea.setText(textArea.getText() + "\n" + "[Client] : " + textField.getText());
                            output.println(textField.getText());
                            Note note = new Note("data.txt");
                            System.out.println(note.reader());
                                String[] splited = note.reader().split("/");
                                JTextArea area = new JTextArea();
                                for (int i = 0; i < splited.length; i++) {
                                    area.setText(splited[i]);
                                }
                                textArea = area;
                                
                                System.out.println(textArea.getText());
                        } catch (IOException e1) {
                        }
                        textField.setText("");
                    }
            }
        });

        
        textArea = new JTextArea();

        panel.add(labelServer); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(btn);

        textArea.setBackground(Color.white);
        textArea.setFont(new Font("Verdana", Font.PLAIN, 21));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.add(scrollPane);

        frameClient.getContentPane().add(BorderLayout.SOUTH, panel);
        frameClient.getContentPane().add(BorderLayout.CENTER, textArea);
        //frame.getContentPane().add(BorderLayout.NORTH, mb);
        frameClient.getContentPane().add(BorderLayout.EAST, scrollPane );
        frameClient.setVisible(true);

    }
}


