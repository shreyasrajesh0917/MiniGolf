package Golf;


public class Hole1 extends HolePlan {
    public Hole1()
    {   
        /* BORDERS */
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);

        /* OBSTRUCTION */
        obs = new Figure[1];
        obs[0] = new Figure(350, 325, 75, 75);
        fillGolfArray();
    } 
       
}
