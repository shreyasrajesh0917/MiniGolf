package Golf;

public class Hole6 extends HolePlan{
    public Hole6()
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
        obs = new Figure[6];
        obs[0]= new Figure(515, 60,20,290);
        obs[1]= new Figure(150,450,300,20);
        obs[2]= new Figure(625,450,20,150);
        obs[3]= new Figure(500,650,70,55);
        obs[4]= new Figure(650,323,80,20);
        obs[5]= new Figure(575,195,55,20);
        sand = new Figure[5];
        sand[0]= new Figure(485,380,100,120);
        sand[1]= new Figure(580,25,140,60);
        sand[2]= new Figure(410, 150, 70, 175);
        sand[3]= new Figure(300,500,170,75);
        sand[4]= new Figure(550,270,85,85);
        fillGolfArray();

    }
    
}