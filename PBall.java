package Golf;
import java.awt.*; // access to Container
import javax.swing.*; // access to JFrame and Jcomponents

public class PBall extends JPanel {
    private int width;
    public PBall( int wid)
    {
        super();
        width = wid;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);
        g2d.fillOval(0,0,width,width);
    }
    
}
