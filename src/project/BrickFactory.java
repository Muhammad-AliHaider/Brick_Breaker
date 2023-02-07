package project;
public class BrickFactory  
{
    protected int totalbricks=0;
    //int option=0;
    public Brick1 getBrick(int choice)//returns type of brick according to choice
    {
        switch(choice)
        {
            case 1: totalbricks+=1; 
                    return new Brick1("03-Breakout-Tiles.png");                    
            case 2: totalbricks+=2;
                    return new Brick2("01-Breakout-Tiles.png");
            case 3: totalbricks+=3;
                    return new Brick3("07-Breakout-Tiles.png");
            case 4: totalbricks +=1;
            		return new Brick4("05-Breakout-Tiles.png");    
        }
        return new Brick1("");        
    }
}
