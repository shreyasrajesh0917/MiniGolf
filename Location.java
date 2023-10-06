package Golf;
public class Location
{
    private int myX;
    private int myY;
    public Location(int x, int y)
    {
        myX = x;
        myY = y;
    }

    public void set(int x, int y)
    {
        myX = x;
        myX = y;
    }
    public boolean equals(Location a)
    {
        return (myX == a.myX && myY == a.myY);
    }
    public int getX()
    {
        return myX;
    }
    public int getY()
    {
        return myY;
    }
}
    
