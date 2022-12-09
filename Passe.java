package passe;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Passe {
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
    String temp;

    public static void main(String[] args) throws UnknownHostException, IOException{
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passe window = new Passe();
					window.frameClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

        con = new Socket("localhost", 1234);
		 while (true) {
			try {
				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				textArea.setText(textArea.getText() + "\n" + "[Server]: " + string);
			} catch (Exception e) {

			}
		}
		
	}

    public Passe() {initialize();}

    private void initialize(){
        frameClient = new JFrame();
        frameClient.setTitle("Chat client");
        frameClient.setDefaultCloseOperation(frameClient.EXIT_ON_CLOSE);
        frameClient.setSize(600, 400);

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
                    textArea.setText(textArea.getText() + "\n" + "[Client] : " + textField.getText());
                    try {
                        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
                        output.write(textField.getText());
                        output.newLine();
                        output.flush();
                        temp = textField.getText();
                    } catch (IOException e1) {
                        e1.printStackTrace();
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
        frameClient.getContentPane().add(BorderLayout.EAST, scrollPane );
        frameClient.setVisible(true);

    }

}
