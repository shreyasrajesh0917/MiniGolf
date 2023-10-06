package Golf;
import java.awt.*; // access to Container
import javax.swing.*; // access to JFrame and Jcomponents

public class PHole extends JPanel {
    private int width;
    public PHole(int width1)
    {
        super();
        width = width1;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.BLACK);
        g2d.fillOval(0,0,width,width);
    }
}
