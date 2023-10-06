package Golf;

public class Hole9 extends HolePlan{
    public Hole9()
    {
        borders = new Figure[4];
        borders[0] = new Figure(0, 0, 749, 10);
        borders[1] = new Figure(0, 739, 749, 10);
        borders[2] = new Figure(0, 0, 10, 749);
        borders[3] = new Figure(739, 0, 10, 749);
        obs = new Figure[6];
        obs[0]= new Figure(400, 600, 50, 125);
        obs[1]= new Figure(550, 100, 150,50);
        obs[2]= new Figure(550, 300, 150, 50);
        obs[3]= new Figure(75,500,200,50);
        obs[4]= new Figure(250,300,50,150);
        obs[5] = new Figure(50,200,200,30);
        sand = new Figure[3];
        sand[0]= new Figure(500,400,170,200);
        sand[1]= new Figure(100,400,100,75);
        sand[2]= new Figure(300,160,150,100);
        bumper = new Figure[3];
        bumper[0]= new Figure(150,100,75,75);
        bumper[1]= new Figure(360,350,75,75);
        bumper[2] = new Figure(475,200,75,75);
        fillGolfArray();
    }
    
}