package Golf;


public class Hole3 extends HolePlan {
    public Hole3()
    {   obs = new Figure[5];
        obs[0] = new Figure(70, 600, 125, 10);
        obs[1] = new Figure(700, 150, 30, 90);
        obs[2] = new Figure(200,400, 50,50);
        obs[3] = new Figure(250, 400, 90, 120);
        obs[4] = new Figure(400, 130, 100, 270);
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);
        fillGolfArray();
    } 
}
