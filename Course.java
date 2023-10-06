/*
    Description: Displays all the different holes and keeps track of the score.
    Purpose: To display the game on a JFrame.
    Authors: @Aadit Jain, @Avaneesh, @Shreyas, @Maan
    Created: 5/31/23
 */
package Golf;
import java.awt.*; // access to Container
import java.awt.event.*; // access to WindowAdapter, WindowEvent
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*; // access to JFrame and Jcomponents
import javax.swing.Timer;
import static Golf.GolfConstants.*;

public class Course extends JFrame implements ActionListener {
    private Hole hole;
    private int [] scoreSet;
    private int count;
    private JTextField title;
    private JTextArea score;
    private int totalScore;
    Container c;
    private Timer t;
    private JMenu options;
    private JMenuItem quit;
    private JMenuItem skip;
    private JMenuItem restart;
    private BufferedImage img,img_icon,palmT;
    private Color dg;
    public Course()
    {   
        super();
        hole = new Hole();
        scoreSet = new int[NUMHOLES];
 
        totalScore = 0;
        count = 0;
        c = getContentPane(); 
        setLayout(null);
        setSize(1000,1000);
        dg = new Color(1,75,32);
        c.setBackground(dg);
        t = new Timer(500, this); // initializing timer
        // adding hole
        hole.setLayout(null);
        hole.setBounds(10,180,750,750);
        c.add(hole);
        for(int loop = 0; loop < 9; loop ++)
            scoreSet[loop] = 0; // initially score for each hole is 0. Socring is based on number of strokes.
        
        
            //adding title
        title = new JTextField("Hole " + (count + 1));
        title.setEditable(false);
        title.setFont(new Font("Arial",Font.BOLD, 50));
        title.setBackground(dg);
        title.setForeground(Color.RED);
        title.setBounds(470, 30, 150, 110);
        title.setBorder(BorderFactory.createEmptyBorder());
        c.add(title);
        // adding score
        score = new JTextArea();
        setScore();

        score.setEditable(false);
        score.setFont(new Font("Arial",Font.BOLD, 30));        
        score.setBackground(dg);
        score.setForeground(Color.orange);
        score.setBounds(770, 170, 240, 500);
        score.setBorder(BorderFactory.createEmptyBorder());
        c.add(score); 

        // adding menu and menu items
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        options = new JMenu("Menu");
        menubar.add(options);
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        restart = new JMenuItem("Restart Game");
        restart.addActionListener(this);
        skip = new JMenuItem("Skip To Next Level");
        skip.addActionListener(this);
        options.add(quit);
        options.add(restart);
        options.add(skip);
        
        options.setEnabled(true);
        
        try {
            img = ImageIO.read(getClass().getResourceAsStream("water.png"));
            img_icon = ImageIO.read(getClass().getResourceAsStream("icon.png"));
            palmT = ImageIO.read(getClass().getResourceAsStream("PalmTree.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        setIconImage(img_icon);
        t.start();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    public static void main(String [] args)
    {
        new Course();
    }
    // Displays the hole based on the count.
    public void addHole()
    {        
        title.setText("Hole " + (count + 1));
        hole.init(count);
    }
    
    public void actionPerformed(ActionEvent e)
    {   
        int tempScore = hole.getCount();
        if(count == 8) {
            skip.setEnabled(false); // skip level isn't available 
        }
        if(e.getSource().equals(quit))
            exitG();
        else if (e.getSource().equals(restart)) {   
            restart();    
        } else if(count < 8 && e.getSource().equals(skip))  {
            count++;
            addHole();
            repaint();
        }

        if (scoreSet[count] != tempScore) {
            scoreSet[count] = tempScore;
            totalScore = 0;
            for(int loop = 0; loop < scoreSet.length; loop++)
            {
                totalScore += scoreSet[loop];
            }
            setScore();                    
        }

        hole.check_for_ball_start();

        if(hole.ballinHole())
        {
            count++;
            ImageIcon golf = new ImageIcon("golf.png");
            JOptionPane pane;
            Object [] possibleValues;
            if(count == NUMHOLES) {
                possibleValues = new Object[2];
                possibleValues[0] = "Quit";
                possibleValues[1] = "Restart Game";
                pane = new JOptionPane("Congratulations!\nAll Levels Cleared!\nYour Total Score Is: " + totalScore, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, golf, possibleValues, possibleValues[0]);
            } else {
                possibleValues = new Object[3];
                possibleValues[0] = "Quit";
                possibleValues[1] = "Restart Game";
                possibleValues[2] = "Go to next level";
                pane = new JOptionPane("Congratulations!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, golf, possibleValues, possibleValues[0]);
            }
           
            Dialog dialog = pane.createDialog(c, "Success!");
            dialog.setVisible(true);
            Object selectedValue = pane.getValue();
            if(selectedValue.equals(possibleValues[0]))
                exitG();        
            else if (selectedValue.equals(possibleValues[1]))
                restart();
            else if (count != NUMHOLES && selectedValue.equals(possibleValues[2]))                
                addHole();
        }

        if (hole.isBallMoving())
           options.setEnabled(false);
        else
            options.setEnabled(true);

    }

    public void setScore()
    {
        score.setText("Scoreboard:\n\n");
        for (int i = 0; i < NUMHOLES; i++) {
            score.append("Hole " + (i + 1) + ": " + String.format(" %2d", scoreSet[i]) + "\n");
        }
        score.append("\nTotal: " + String.format(" %4d",totalScore));
    }
    public void exitG()
    {
        t.stop();
        dispose();
    }
    public void restart()
    {
        count = 0;        
        totalScore = 0;
        for(int loop = 0; loop < scoreSet.length; loop++)
            scoreSet[loop] = 0;
        setScore();    
        skip.setEnabled(true);
        addHole();
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(img, 820,740, 114, 250, dg, null);
        g2d.drawImage(palmT, 145,70, 135,120,null);
        g2d.drawImage(palmT, 280,70, 135,120,null);
        g2d.drawImage(palmT, 705,70, 135,120,null);
        g2d.drawImage(palmT, 840,70, 135,120,null);
    }
    
   
}
