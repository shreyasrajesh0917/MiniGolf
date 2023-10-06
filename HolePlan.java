package Golf;
import static Golf.GolfConstants.*;

public class HolePlan {
    protected int [] [] golfArray;
    protected Location ballLocation;
    protected Location holeLocation;

    protected Figure[] borders;
    protected Figure[] sand;
    protected Figure[] obs;
    protected Figure[] empty;
    protected Figure[] bumper;

    public HolePlan()
    {
        golfArray = new int [750][750];
        ballLocation = new Location(210,560); 
        holeLocation = new Location(580,230);
        empty = new Figure[0];
        sand = new Figure[0];
        borders = new Figure[0];
        obs = new Figure[0];
        bumper = new Figure[0];
    }
    
    public void fillGolfArray()
    {
        for (int r = 0; r < golfArray.length -1; r++) {
            for (int c = 0; c < golfArray[r].length; c++) {
                golfArray[r][c] = GRASS;
            }
        }

        fillSand();
        fillBump();
        fillBorders();
        fillEmpty();
        fillObs();
    }

    public void fillBorders()
    {
        
        for (int n = 0; n < borders.length; n++) {
            Figure b = borders[n];
 
            for(int r = b.getX(); r < (b.getX() + b.getWidth()); r++) {
                for(int c = b.getY(); c < (b.getY() + b.getHeight()); c++) {
                    golfArray[r][c] = WALL;
                }
            }
        }
    }
    
    public void fillObs()
    {

        for (int n = 0; n < obs.length; n++) {
            Figure ob = obs[n];
            for (int c = ob.getY(); c <= (ob.getY() + ob.getHeight()); c++) {    
                for (int r = ob.getX(); r <= (ob.getX() + ob.getWidth()); r++) {
                    golfArray[r][c] = OBSTRUCTION;    
                }
            }
        }
    }

    public void fillSand()
    {

        for (int n = 0; n < sand.length; n ++) {
            Figure s = sand[n];
            int w = s.getWidth()/2;
            int h = s.getHeight()/2;
            int cx = s.getX() + w;
            int cy = s.getY() + h;
        
            for (int r = -w-1; r <= w+1; r++) {
                for (int c = -h-1; c <= h+1; c++) {                    
                    double ellip = ((r*r*1.0)/(w*w)) +  ((c*c*1.0)/(h*h));
                    if (ellip <= 1) {
                        int xx = r + cx;
                        int yy = c + cy;
                        if ((xx > 0) && (yy > 0))
                            golfArray[xx][yy] = SAND;                        
                    }
                }
            }
        }
    }

    public void fillEmpty()
    {

        for(int loop = 0; loop < empty.length; loop++)
        {
            Figure emt = empty[loop];
            for(int r = emt.getX(); r<= emt.getX() + emt.getWidth(); r++ )
            {
                for(int c = emt.getY(); c<= emt.getY() + emt.getHeight(); c++)
                    golfArray[r][c] = NULL;
            }
        }
    }
    public void fillBump()
    {
        for(int loop = 0; loop < bumper.length; loop++)
        {
            Figure bump = bumper[loop];
            for(int r = bump.getX(); r<= bump.getX() + bump.getWidth(); r++)
            {
                for(int c = bump.getY(); c <= bump.getY() + bump.getHeight(); c++)
                    golfArray[r][c] = BUMPER;
            }
        }
    }

    public int [] [] getGolfArray()
    {
        return golfArray;
    }
    public Location getBallLocation()
    {
        return ballLocation;
    }
    public Location getHoleLocation()
    {
        return holeLocation;
    }

    public Figure[] getBorder()
    {
        return borders;
    }
    public Figure[] getObs()
    {
        return obs;
    }
    public Figure[] getEmt()
    {
        return empty;
    }
    public Figure[] getSand()
    {
        return sand;
    }
    public Figure[] getBump()
    {
        return bumper;
    }
}
