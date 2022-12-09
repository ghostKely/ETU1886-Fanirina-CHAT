package fenetre;
import javax.swing.*;

public class Fenetre extends JFrame
{
    public Fenetre()
    {
        this.setTitle("CHAT");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}