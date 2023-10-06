package Golf;

import java.awt.*; // access to Container
import java.awt.event.*; // access to WindowAdapter, WindowEvent
import javax.swing.*; // access to JFrame and Jcomponents
import javax.swing.Timer;
import java.lang.Thread;
import static Golf.GolfConstants.*;


public class Hole extends JPanel implements MouseMotionListener, MouseListener, ActionListener {
    private Timer t;
    private int [][] golfArray;
    private Location [] ballArray;
    private Location [] holeArray;
    private int speed;
    private int mouseX;
    private int mouseY;
    private Location  ball; // center
    private double ball_dob_x; 
    private double ball_dob_y; 
    private double prev_dob_x; 
    private double prev_dob_y; 

    private int widthBall;
    private Location hole; // center
    private int widthHole;
    private double slope;
    private int count;
    private int horizontal;
    private int vertical;
    private boolean flag;
    private int strX;
    private int strY;
    private int xCount;
    private int yCount;
    private boolean infinity;
    private final int max_speed = 15000;
    private Color grass;
    private Color grassTR;
    private int delta;
    private PBall pBall;
    public boolean stop_paint;
    private boolean start_ball;
    private PHole pHole;
    private PSand [] pSand;
    private HolePlan [] holes;
    
    private Figure[] borders;
    private Figure[] san;
    private Figure[] obs;
    private Figure[] emp;
    private Figure[] bump;


    public Hole()
    {
        super();
        holes = new HolePlan[NUMHOLES];
        holes[0] = new Hole1();
        holes[1] = new Hole2();
        holes[2] = new Hole3();
        holes[3] = new Hole4();
        holes[4] = new Hole5();
        holes[5] = new Hole6();
        holes[6] = new Hole7();
        holes[7] = new Hole8();
        holes[8] = new Hole9();
    

        ballArray = new Location [4];
        holeArray = new Location [4];
        widthBall = WIDBALL;        
        widthHole = WIDHOLE;

        addMouseMotionListener(this);
        addMouseListener(this);
        t = new Timer(0, this);

        grass = new Color(34, 150, 34);
        grassTR = new Color(34, 150, 34, 0);

        setBackground(grass);
        setLayout(null);    

        pBall = new PBall(widthBall);
        pBall.setLayout(null);
        pBall.setBackground(grassTR);       
        pBall.setBorder(BorderFactory.createEmptyBorder());
        add(pBall);

        pHole = new PHole(widthHole);
        pHole.setLayout(null);
        pHole.setBackground(grassTR);       
        pHole.setBorder(BorderFactory.createEmptyBorder());
        add(pHole);
        
        pSand = new PSand[0];
        init(0);

        pBall.setBounds(ballArray[1].getX(), ballArray[1].getY(), widthBall, widthBall);
        pHole.setBounds(holeArray[1].getX(), holeArray[1].getY(), widthHole, widthHole);      
        
    }

    public void init(int idx)   
    {
        ball = holes[idx].getBallLocation();
        hole = holes[idx].getHoleLocation();
        golfArray = holes[idx].getGolfArray();

        speed = 0;
        slope = 0;
        count = 0;
        
        horizontal = 0;
        vertical = 0;
        flag = false;
        strX = ball.getX();
        strY = ball.getY();
        xCount = 0;
        yCount = 0;
        delta = 1;
        infinity = false;
       
        stop_paint = false;
        start_ball = false;

        ball_dob_x = ball.getX();
        ball_dob_y = ball.getY();
        prev_dob_x = ball.getX();
        prev_dob_y = ball.getY();
                
        fillHoleArray();
        fillBallArray();
       

        pBall.setLocation(ballArray[1].getX(), ballArray[1].getY());
        pHole.setLocation(holeArray[1].getX(), holeArray[1].getY());
        for(int loop = 0; loop < pSand.length; loop++) {
            remove(pSand[loop]);
        }
        
        borders = holes[idx].getBorder();
        obs = holes[idx].getObs();
        san = holes[idx].getSand();
        emp = holes[idx].getEmt();
        bump = holes[idx].getBump();
        pSand = new PSand[san.length];
        for(int loop = 0; loop < san.length; loop++) {
            pSand[loop] = new PSand(san[loop].getWidth(), san[loop].getHeight());
            pSand[loop].setLayout(null);
            pSand[loop].setBackground(grassTR);
            pSand[loop].setBorder(BorderFactory.createEmptyBorder());
            pSand[loop].setBounds(san[loop].getX(), san[loop].getY(), san[loop].getWidth(), san[loop].getHeight());
            add(pSand[loop]);
        }
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);     
       
        if (stop_paint) {
            return;
        } 
 
        Graphics2D g2d = (Graphics2D) g;  
        g2d.setStroke(new BasicStroke(1));                       
        
       
        for(int loop = 0; loop < borders.length; loop++) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(borders[loop].getX(), borders[loop].getY(), borders[loop].getWidth(), borders[loop].getHeight());
        }
        for(int loop = 0; loop < obs.length; loop++) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(obs[loop].getX(), obs[loop].getY(), obs[loop].getWidth(), obs[loop].getHeight());
        }
        for(int loop = 0; loop < emp.length; loop++) {
            g2d.setPaint(new Color(1,75,32));
            g2d.fillRect(emp[loop].getX(), emp[loop].getY(), emp[loop].getWidth(), emp[loop].getHeight());
        }
        for(int loop = 0; loop < bump.length; loop++) {
            g2d.setPaint(Color.YELLOW);
            g2d.fillRect(bump[loop].getX(), bump[loop].getY(), bump[loop].getWidth(), bump[loop].getHeight());
        }

        if(flag && !ballinHole()) {
            float[] dash1 = { 2f, 0f, 2f };
            g2d.setPaint(Color.BLUE);
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,  BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
            g2d.setStroke(bs1);                    
            g2d.drawLine(ball.getX(), ball.getY(), mouseX, mouseY);            
        }
    }
    
    
    public void fillHoleArray()
    {
        holeArray[0] = new Location(hole.getX() + (widthHole/2), hole.getY() - (widthHole/2));        
        holeArray[1] = new Location(hole.getX() - (widthHole/2), hole.getY() - (widthHole/2));
        holeArray[2] = new Location(hole.getX() - (widthHole/2), hole.getY() + (widthHole/2));
        holeArray[3] = new Location(hole.getX() + (widthHole/2), hole.getY() + (widthHole/2));
    }
    public void fillBallArray()
    {        
        ballArray[0] = new Location(ball.getX() + (widthBall/2), ball.getY() - (widthBall/2));
        ballArray[1] = new Location(ball.getX() - (widthBall/2), ball.getY() - (widthBall/2));
        ballArray[2] = new Location(ball.getX() - (widthBall/2), ball.getY() + (widthBall/2));
        ballArray[3] = new Location(ball.getX() + (widthBall/2), ball.getY() + (widthBall/2));
    }

    public void updateBallCoord()
    {
        prev_dob_x = ball_dob_x;
        prev_dob_y = ball_dob_y;

        if(infinity) {
            ball_dob_x = ball.getX();
            ball_dob_y = ball.getY() + slope;

            int y = ball.getY() + (int)(slope);
            ball = new Location(ball.getX(), y);            
        }
        else if (Math.abs(slope) <= 1) {
            xCount += horizontal;
            ball_dob_x = strX + xCount;   
            ball_dob_y = strY + (xCount * slope);

            int y = (int)Math.round(xCount * slope);
            ball = new Location(strX + xCount, strY +y);
        } else {
            yCount += vertical;
            ball_dob_y = strY + yCount;
            ball_dob_x = strX + (yCount / slope);

            int x = (int)Math.round((yCount / slope));
            ball = new Location(strX + x, strY + yCount);          
        }
        fillBallArray();
    }

    public void movement()
    {
        int loop;
        boolean obs_touch = false;
        speed -= delta;
       
        updateBallCoord();
        if(speed >= 40000) {
            speed = 0;
            return;
        }

       if(ball.getX() <= holeArray[0].getX() && ball.getX() >= holeArray[1].getX() &&
        ball.getY() >= holeArray[1].getY() && ball.getY() <= holeArray[2].getY() &&
        speed < 1750) {
           ball = hole;
           fillBallArray();
           return;
        }

        delta = 10;
        for(loop = 0; loop < 4; loop++)  {        
                if(golfArray[ballArray[loop].getX()][ballArray[loop].getY()] == OBSTRUCTION ) {
                    delta = 25;                        
                    obs_touch = true;
                    System.out.println("Found OBS");
                    break;
                } else if(golfArray[ballArray[loop].getX()][ballArray[loop].getY()] == WALL) {
                    delta = 25;                    
                    obs_touch = true;
                    break;
                } else if(golfArray[ballArray[loop].getX()][ballArray[loop].getY()] == BUMPER) {
                    delta = -3500;                    
                    obs_touch = true;
                    break;
                } else if(golfArray[ballArray[loop].getX()][ballArray[loop].getY()] == SAND) {
                    delta = 100;
                    break;
                }
            }

        if (obs_touch) {            
            /* Check to see if we need to reflect (is ball moving toward OR away from obs/wall) */
            double obs_x = (double) ballArray[loop].getX();
            double obs_y = (double) ballArray[loop].getY();                    
            double prev_dis, curr_dis;

            if (is_obst_vertical(ballArray[loop].getX(), ballArray[loop].getY())) {
                // Vertical Wall hit, find horizontal distances
                prev_dis = Math.abs(prev_dob_x - obs_x);
                curr_dis = Math.abs(ball_dob_x - obs_x);
            } else {
                 // Horizontal Wall hit, find vertical distances   
                prev_dis = Math.abs(prev_dob_y - obs_y);
                curr_dis = Math.abs(ball_dob_y - obs_y);
            }
            if (curr_dis < prev_dis) {
                /* Ball Moving towards obs/wall - reflect */                
                reflection(ballArray[loop].getX(), ballArray[loop].getY());                
                updateBallCoord();  
            } else {
                delta = 10;
            }
        }
    }

    public boolean is_obst_vertical(int x, int y)
    {
        if(golfArray[x+1][y] == GRASS || golfArray[x-1][y] == GRASS)
            return true;
        else
            return false;
    }

    public void reflection(int x, int y)
    {    
       if (is_obst_vertical(x,y))
            horizontal *= -1;
        else 
            vertical *= -1;            
        strX = ball.getX();
        strY = ball.getY();
        xCount = 0;
        yCount = 0;
        slope *= -1;
    }

    public int getCount()
    {
        return count;
    }

    public boolean ballinHole()
    {
        return (ball.equals(hole));
    }

    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();          
        flag = true;
        repaint();    
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseReleased(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        flag = false;
        count++;
        removeMouseListener(this);
        removeMouseMotionListener(this);       
        strX = ball.getX();
        strY = ball.getY();
        xCount = 0; yCount = 0; 
        speed = (int)((Math.sqrt(Math.pow(mouseY - ball.getY(), 2) + Math.pow((mouseX - ball.getX()),2))) * 20);
        speed = Math.min(speed, max_speed);
        if(mouseX == ball.getX() && mouseY == ball.getY()) {
            repaint();
        }
        else {
            if(mouseX == ball.getX() && mouseY < ball.getY()) {
                slope = -1;
                infinity = true;
            } else if (mouseX == ball.getX() && mouseY > ball.getY()) {
                slope = 1;
                infinity = true;
            } else {
                slope = (double) (mouseY - ball.getY()) / (mouseX - ball.getX());
                if(Math.abs(slope) <= 1) {
                    if(mouseX < ball.getX())
                    {
                        horizontal = -1;
                    }
                    else
                    {
                        horizontal = 1;
                    }
                } else {
                    if(mouseY < ball.getY())
                        vertical = -1;
                    else
                        vertical = 1;
                }  
            }    

            repaint();
            start_ball = true;
        }                
    }

    public void check_for_ball_start()
    {
        if (start_ball) {
            stop_paint = true;
            start_ball = false;
            t.start(); 
        }
    }

    public boolean isBallMoving()
    {
        return (speed > 0);
    }

    public void actionPerformed(ActionEvent e)
    {   
        int wait, max_wait = 15;

        if (speed > 0 && !ballinHole()) {            
            movement();
            pBall.setLocation(ballArray[1].getX(), ballArray[1].getY());
           

            if (speed <= 0)
                wait = max_wait;
            else
                wait = Math.min(max_wait, Math.round(max_speed/speed));
            try {                
                Thread.sleep(wait);
            } 
            catch (Exception ef) {
                System.out.println(ef);           
            }
        } 
        else {
            t.stop();
            addMouseListener(this);
            addMouseMotionListener(this);            
            stop_paint = false;        
        }                
    }    

    public void mouseClicked(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {
        
    }
}
