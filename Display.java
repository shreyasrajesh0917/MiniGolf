package Golf;
import java.awt.*; // access to Container
import java.awt.event.*; // access to WindowAdapter, WindowEvent
import javax.lang.model.util.ElementScanner6;
import javax.swing.*; // access to JFrame and Jcomponents
import javax.swing.event.*; // access to JSlider events
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame 
{
    private JButton playButton;
    private JButton quitButton;
    private JTextField title;
    private Image image;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            new Display();
        });
    }

    public Display() 
    {
        
        title = new JTextField("Dunlea's Mini Golf");
        title.setEditable(false);
        title.setFont(new Font("Times New Roman",Font.BOLD, 50));
        title.setForeground(Color.BLACK);
        title.setBounds(450, 10, 150, 110);
        title.setBorder(BorderFactory.createEmptyBorder());

        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.LIGHT_GRAY);
        quitButton.setFont(new Font("Times New Roman", Font.BOLD, 24)); //change font if needed
        quitButton.setBounds(500, 750, 100, 50); //Adjust?

        playButton = new JButton("Play");
        playButton.setBackground(Color.LIGHT_GRAY);
        playButton.setFont(new Font("Times New Roman", Font.BOLD, 24)); //change font if needed
        playButton.setBounds(350, 750, 100, 50); //Adjust?

        Container contentPane = getContentPane();
        contentPane.add(title);
        contentPane.setLayout(null);
        contentPane.add(playButton);
        contentPane.add(quitButton);
        setLocationRelativeTo(null); 
        setVisible(true);

        playButton.addMouseListener(new java.awt.event.MouseAdapter() //hovering mechanism
        {
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                playButton.setBackground(Color.ORANGE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) 
            {
                playButton.setBackground(Color.GREEN);
            }
        });

        quitButton.addMouseListener(new java.awt.event.MouseAdapter() //hovering mechanism
        {
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                quitButton.setBackground(Color.ORANGE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) 
            {
                quitButton.setBackground(Color.GREEN);
            }
        });

        setTitle("Dunlea's Mini Golf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 1000)); //Adjust?  
        //JLabel background = new JLabel(new ImageIcon("Home Screen Mr.Dunlea.jpg"));
        //contentPane.add(background);

        // Add action listener for play button
        playButton.addActionListener(
        new ActionListener() 
        {
            //@Override
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
                new Course();
            }
        });

        // Add action listener for quit button
        quitButton.addActionListener(
        new ActionListener() 
        {
            //@Override
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
            }
        });
    }
}
