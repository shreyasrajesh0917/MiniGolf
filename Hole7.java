package Golf;

public class Hole7 extends HolePlan{
    public Hole7()
    {
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);
        obs = new Figure[4];
        obs[0] = new Figure(500, 400, 50, 125);
        obs[1] = new Figure(550, 300, 150, 50);
        obs[2] = new Figure (75,500,200,50);
        obs[3] = new Figure(250,250,50,150);
        sand = new Figure[2];
        sand[0] = new Figure(300,500,170,75);
        sand[1] = new Figure(400,150,150,100);
        bumper = new Figure[1];
        bumper[0] = new Figure(100,100,75,75);
        fillGolfArray();
    }
    
}