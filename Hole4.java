package Golf;


public class Hole4 extends HolePlan {
    public Hole4()
    {
        borders = new Figure[6];
        borders[0] = new Figure(375, 0, 375,10);
        borders[1] = new Figure(740, 0, 10,750);
        borders[2] = new Figure(0, 740, 750, 10);
        borders[3] = new Figure(0,375,10,375);
        borders[4] = new Figure(0,375,385,10);
        borders[5]= new Figure(375,0,10,385);

        empty = new Figure[1];
        empty[0] = new Figure(0,0,375,375);
        obs = new Figure[2];
        obs[0] = new Figure(500,60, 10, 300);
        obs[1] = new Figure(400, 600, 50 , 75);

        sand = new Figure[1];
        sand[0] = new Figure(515,355,165,200);

        fillGolfArray();
    } 

}
