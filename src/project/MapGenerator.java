package project;


import java.awt.Graphics2D;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MapGenerator  
{
    protected int map[][];
    protected int brickWidth;
    protected int brickHeight;
    protected boolean isPlus;
    protected boolean purpleleft;
    protected Brick1 bricks[][];
    private BrickFactory bf =new BrickFactory();
    private Random random=new Random();
    protected int count=1,purplecount=0,greencount=0,redcount=0,bluecount=0;
    private int Dposx;
    private int DposY;
    private int counterPos = 0;
    private int positioncod = 1;
    private int counter = 0;
    private int Fquadcounter = 1;
    private int Squadcounter = 1;
    private int Tquadcounter = 1;
    private int Forthquadcounter = 1;
    protected int totalBricks;
    protected boolean ballOnFire = false;
    
    
    public MapGenerator(int row,int col)
    {
        map=new int[row][col];
        bricks = new Brick1[row][col];
        counter = 0;
        
        // adds a random number to map[i][j] which then adds the brick to the bricks[i][j]
    	 for(int i=0;i<map.length;i++)
         {
             for(int j=0;j<map[0].length;j++)
             {
                 //map[i][j]=1;
                 map[i][j]=random.nextInt(4)+1;                 
                 bricks[i][j]=bf.getBrick(map[i][j]);
                                 
                 if(map[i][j]==4)
                     purplecount+=1;
                 if(map[i][j]==1)                    
                     greencount+=1;
                 if(map[i][j]==2)
                     bluecount+=2;
                 if(map[i][j]==3)                    
                     redcount+=3;
                 totalBricks = bf.totalbricks;// sets the total bricks
                 
                     
             }
         }
    	 brickWidth=540/col;
         brickHeight=150/row;
     }
    
    public void draw(Graphics2D g)
    {
    	positioncod = 1;// it increases once the the 4 bricks around the center brick is drawn and it is used to increase the distance form the center brick
    	counterPos = 0; // it increases when a single brick is drawn and is used to draw the 4 bricks around the center brick
    	
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                	if(counter > bricks.length*bricks[0].length)// after the bricks are drawn for the first time then for re drawing this function is called 
                	{
                		drawbricks(g,i,j);
                	}
                	else if(isPlus == false) // for simple row and column wise alignment
                	{
                		bricks[i][j].x = j*brickWidth+80;
            			bricks[i][j].y = i*brickHeight+50;
            			bricks[i][j].paint(g,j*brickWidth+80,i*brickHeight+50);
                	}
                	
                	else if(isPlus == true)// for a + sign alignment
                	{
                	callingPlus(g,i,j);
                	}
                	counter++;
                }
            }
        }
    }
    
    private void drawbricks(Graphics2D g,int i, int j) //for redrawing the bricks
    {
    	bricks[i][j].paint(g, bricks[i][j].x,bricks[i][j].y );
    }
    
    private void callingPlus(Graphics2D g,int i, int j)
    {
    	if(i == 0 && j == 0)
		{
		Dposx = 275;// X-coordinate of the center brick
		DposY = 150;// Y-coordinate of the center brick
		bricks[i][j].x = Dposx;//storing x of the brick
		bricks[i][j].y = DposY;// storing y of the brick
		bricks[i][j].paint(g, Dposx, DposY); // for drawing the bricks
		}
    	else if(counterPos%4 == 1 )//  for right side of the center brick
		{
			Dposx= Dposx + (100*1*positioncod);
			DposY = 150;
			System.out.println("1 " +Dposx + " "+DposY);
			if((Dposx > 0 && Dposx < 570) && DposY> 20 && DposY< 250 )
			{
				bricks[i][j].paint(g, Dposx, DposY);
			}
			else
			{
				 // for placing the brick on the right-down side of the center brick
				
				Dposx = Dposx - (100*1*positioncod) +(100*Fquadcounter);
				DposY = DposY + (40*Fquadcounter);
				System.out.println("1 " +Dposx + " "+DposY);
				bricks[i][j].paint(g, Dposx, DposY);
				Fquadcounter++;
			}
			bricks[i][j].x = Dposx;//storing x of the brick
			bricks[i][j].y = DposY;// storing y of the brick
		}
    	else if(counterPos%4 == 2)// left
		{
			Dposx= Dposx - (100*positioncod);
			DposY =  150;
			System.out.println("2 " +Dposx + " "+DposY);
			if((Dposx > 0 && Dposx < 570) && DposY> 20 && DposY< 250 )
			bricks[i][j].paint(g, Dposx, DposY);
			
			else
			{
				 // for placing the brick on the left-up side of the center brick
				Dposx = Dposx + (100*positioncod)- (100*Squadcounter);
				DposY = DposY - (40*Squadcounter);
				System.out.println("2 " +Dposx + " "+DposY);
				bricks[i][j].paint(g, Dposx, DposY);
				Squadcounter++;
			}
			bricks[i][j].x = Dposx;//storing x of the brick
			bricks[i][j].y = DposY;// storing y of the brick
		}
		
    	else if(counterPos%4 == 3)// down
		{
			Dposx = 275;
			DposY= DposY +(40*1*positioncod);
			if((Dposx > 0 && Dposx < 570) && (DposY> 10 && DposY< 250 ))
			{
			bricks[i][j].paint(g, Dposx, DposY);
			}
			else
			{
				 // for placing the brick on the left-down side of the center brick
				Dposx = Dposx - (100*Tquadcounter) ;
				DposY = DposY - (40*1*positioncod) + (40*Tquadcounter);
				bricks[i][j].paint(g, Dposx, DposY);
				Tquadcounter++;
			}
			bricks[i][j].x = Dposx;//storing x of the brick
			bricks[i][j].y = DposY;// storing y of the brick
		}
    	else if(counterPos%4 == 0)// up
		{	positioncod--;// Since position cod increases when counterPos%4 is 0 therefore to get the same position cod i subtracted 1 from it 
			Dposx = 275;
			DposY = DposY - (40*positioncod);
			if((Dposx > 0 && Dposx < 570) && (DposY> 30 && DposY< 250 ))
			{
				bricks[i][j].paint(g, Dposx, DposY);
			}
			else
			{	 // for placing the brick on the right-up side of the center brick
				Dposx = Dposx + (100*Forthquadcounter);
				DposY = DposY  + (40*positioncod)- (40*Forthquadcounter);
				bricks[i][j].paint(g, Dposx, DposY);
				Forthquadcounter++;
			}
			bricks[i][j].x = Dposx;//storing x of the brick
			bricks[i][j].y = DposY;// storing y of the brick
			positioncod++;// here 1 is again added to position  cod to bring it to its current state
			
		}
    	counterPos++;
		if(counterPos%4 == 0)
		{
			positioncod++;
		}
		Dposx = 275;//setting x coordinate again as 275
		DposY = 150;//setting y coordinate again as 150
		
    	}
    public void setBrickValue(int value,int row,int col)// for destroying and damaging bricks 
    {
    	if(ballOnFire == false)
    	{
    	 if (!(bricks[row][col] instanceof Brick4))
         {
             map[row][col]--;
             bricks[row][col].setBrickImage();
             if(!(bricks[row][col] instanceof Brick2) && !(bricks[row][col] instanceof Brick3))
             {
                 greencount--;
                 bricks[row][col].hitTaken++; 
                 
             }
             if(bricks[row][col] instanceof Brick2)
             {
                 bluecount--;
                 bricks[row][col].hitTaken++;//----------------------------------------------- hit taken is set to know how many hits has the brick taken
             }
             if(bricks[row][col] instanceof Brick3)
             {
                 redcount--;
                 bricks[row][col].hitTaken++;
             }
             
         } 
    	}
	  if(ballOnFire == true)
	  {
            if(!(bricks[row][col] instanceof Brick2) && !(bricks[row][col] instanceof Brick3)&& !(bricks[row][col] instanceof Brick4))
            {
                greencount--;
                map[row][col] = map[row][col]-1;
            }
            if(bricks[row][col] instanceof Brick2)
            {
                bluecount = bluecount - 2 + bricks[row][col].hitTaken;
                map[row][col] = map[row][col]- 2 + bricks[row][col].hitTaken;
            }
            if(bricks[row][col] instanceof Brick3)
            {
                redcount = redcount - 3 + bricks[row][col].hitTaken;
                map[row][col] = map[row][col]- 3 + bricks[row][col].hitTaken;
            } 
            if(bricks[row][col] instanceof Brick4)
            {
            	purplecount--;
            	map[row][col] = map[row][col]-4;
            }
      }
         totalBricks = purplecount+greencount+bluecount+redcount;
         System.out.println(value+" \n purplecount"+ purplecount + "\n green: "+greencount+"\n blue: "+bluecount+"\n red: "+redcount);
     }
}
