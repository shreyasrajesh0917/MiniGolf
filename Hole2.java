package Golf;


public class Hole2 extends HolePlan {
    public Hole2()
    {
        obs = new Figure[3];
        obs[0] = new Figure(150, 100, 75, 75);
        obs[1] = new Figure(250, 450, 75, 75);
        obs[2] = new Figure(590, 400, 50, 100);
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);

        fillGolfArray();
    } 

    
}
