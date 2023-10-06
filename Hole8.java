package Golf;

public class Hole8 extends HolePlan{
    public Hole8()
    {
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);
        sand = new Figure[3];
        sand[0]= new Figure(500,400,170,200);
        sand[1]= new Figure(100,400,100,75);
        sand[2]= new Figure(345,175,150,100);
        bumper = new Figure[2];
        bumper[0]= new Figure(150,100,75,75);
        bumper[1]= new Figure(360,350,75,75);
        obs = new Figure[5];
        obs[0]= new Figure(400, 600, 50, 125);
        obs[1]= new Figure(550, 100, 150,50);
        obs[2]= new Figure(550, 300, 150, 50);
        obs[3]= new Figure(75,500,200,50);
        obs[4]= new Figure(250,250,50,150);
        fillGolfArray();

    }
    
}