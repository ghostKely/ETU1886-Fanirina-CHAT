package note;
import java.io.*;
import java.util.*;

public class Note extends File{
      public Note(String stg) throws IOException {
            super(stg);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
            if(!this.exists()) {
                  this.createNewFile();
            }
      }      
      
      public void writer(String nom) throws IOException {
            String text = this.reader();
            FileWriter writer=new FileWriter(this);
            BufferedWriter inline = new BufferedWriter(writer);
            if (text!=null) {
                  inline.write(text+nom);
                  inline.close();
            }
            else {
                  inline.write(nom);
                  inline.close();
            }
      }
      public String reader() throws IOException {
            BufferedReader read=new BufferedReader(new InputStreamReader(new FileInputStream(this)));
            String text=read.readLine();
                
            return text;
      }
}
