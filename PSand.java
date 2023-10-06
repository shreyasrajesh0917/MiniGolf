package Golf;
import java.awt.*; // access to Container
import javax.swing.*; // access to JFrame and Jcomponents


public class PSand extends JPanel {
    private int width;
    private int height;
    public PSand(int width1, int height1)
    {
        super();
        width = width1;
        height = height1;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(new Color(205, 170, 109));
        g2d.fillOval(0, 0, width, height);

    }
}
