package Golf;

public class Hole5 extends HolePlan{
    public Hole5()
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
        obs = new Figure[5];
        obs[0] = new Figure(535, 40,10,290);
        obs[1] = new Figure(150,400,300,10);
        obs[2] = new Figure(550, 600, 50, 75);
        obs[3] = new Figure(650,600,80,55);
        obs[4] = new Figure(558,313,80,10);
        sand = new Figure[3];
        sand[0]= new Figure(485,365,100,175);
        sand[1]= new Figure(580,25,140,40);
        sand[2]= new Figure(390, 150, 70, 175);
        fillGolfArray();
    }
    
}
