package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nawal
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener,Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean play=false;
    private int score=0;//----------------------------------- indicate the score of the player 
    private int totalBricks;//------------------------------- indicates the total bricks present on the screen
    private Timer timer;
    private int delay=8;
    private Ball ball;
    private ArrayList<Ball> ballList = new ArrayList<>();//--- stores all the balls currently present on the screen
    private int ballCount = 1;//------------------------------ keeps count of the total balls present on the screen
    private paddle padd; //----------------------------------- is the reference of paddle
    private PowerUPFactory Pfactory = new PowerUPFactory();//- object of powerUp Factory
    private int lifecount = 5;//------------------------------ keeps the count of the lives
    private Image lifeImage;
    private int lifeWidth;
    private Power power;//------------------------------------ reference of power
    private long count = 0;
    private boolean ballstart = true; //----------------------- boolean when ball needs to start
    private boolean gamereset= false; //----------------------- boolean used for reseting the game
    private file SaveState = new file();
    private MapGenerator map; //------------------------------ reference of MapGenerator
    private int levelNumber = 1; //--------------------------- level count
    private Levels level = new Levels(); //------------------- object of levels class
    private boolean increaseLevel = false;//------------------ boolean used to increase level
    private boolean lifeLost = false; //---------------------- boolean used to decrease live count
    private boolean ballsOnFire = false; //------------------- boolean used to set the ball on fire
    private boolean gameStart = false; //--------------------- is used to stick the ball to the paddle
    protected static boolean loadGame = false;//-------------- is true once the game has been saved
    private boolean lost = false;//--------------------------- checks if the player lost the game or not
    protected static boolean SaveGame = false;//-------------- is set true once game has been saved
    protected static boolean newGame = false;//--------------- resets the game when new game is clicked
    protected static boolean Help = false; //----------------- is used to pop up the help dialogue box
    protected static enum STATE { //-------------------------- is used for switching between game and menu
    	GAME,MENU
    }
    protected static STATE state = STATE.MENU;
    protected Menu MENU = new Menu();
    
    private ArrayList<PlayerFire> bullets = new ArrayList<>();// array of bullets
    
    
    public GamePlay() // creates the object of the game play
    { 
        map=level.getlevel(levelNumber);// sets the level of the map
        totalBricks = map.totalBricks; // sets the totalBricks
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(new TAdapter());
    	
        
     // image getter for life 
        ImageIcon lifeimageicon = new ImageIcon(""+60+"-Breakout-Tiles.png"); 
        lifeImage = lifeimageicon.getImage();
        
        lifeWidth = lifeImage.getWidth(null); 
        
        timer=new Timer(delay,this);
        timer.start();
    	
        
        
    }
    public void paint(Graphics g)
    {
    	
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        
        
        if(state ==STATE.GAME)
        {
	        //the paddle
	      
	        padd = paddle.getInstance();
	        padd.draw((Graphics2D)g); 
	      
	        // adding the ball in the BallList
	       if(ballstart == true)
	        {
	    	   for(int i = 0 ; i < ballList.size() ; i++)
	           {
	           	ballList.remove(i);
	           }
	    	   if(padd!= null)
	        	ball = new Ball(padd.getpositionX()+45,530-10) ;
	        	ballList.add(ball);
	        	ballstart = false;
	        }
	       
	       
	   
	       	
	        //drawing map 
	        map.draw((Graphics2D)g);	
	       	
	       	
	   	       
	        // draws all the bullets present in the list
	        if(padd!= null)
	        {
		        if(padd.bulletsisActive == true)
		        {
		        	for(int i = 0 ; i< bullets.size();i++)
		        	{
		        		bullets.get(i).draw((Graphics2D)g);
		        	}
		        }
	        }
	        //draws the powerUp and the moves it down
	        if(power != null)
	        {
	        	power.draw((Graphics2D)g);
	        	power.moveDown();
	        }
	        
	        // draws all the lives the the players has
	        int position = lifeWidth+10;
	        for(int i = 0 ; i<lifecount ; i++)
	        {
	        	if(i == 0)
	        	g.drawImage(lifeImage,10, 20,null);
	        	else
	        	{
	        		g.drawImage(lifeImage,position, 20,null);
	        		position = position+lifeWidth;
	        	} 
	        		
	        }
	        
	        //borders
	        g.setColor(Color.yellow);
	        g.fillRect(0,0,3,592);
	        g.fillRect(0,0,692,3);
	        g.fillRect(691,0,3,592);
	        
	        // displays the scores
	        g.setColor(Color.white);
	        g.setFont(new Font("serif",Font.BOLD,25));
	        g.drawString(" "+score,590,30);
	        
	        // draws all the balls present on the Screen
	        
	        for(int i = 0 ; i < ballList.size() ; i++)
	        {
	        ballList.get(i).draw((Graphics2D) g);
	        }
	        
	        for(int i = 0 ; i < ballList.size() ; i++)
	        {
	        	if(ballList.get(i).positionY >570)
	        	{
	        		ballList.remove(i);
	        		ballCount--;
	        	}
	        }
	        
	        // level number printing
	        g.setColor(Color.white);
	        g.setFont(new Font("serif",Font.BOLD,25));
	        g.drawString("Level : " + levelNumber,350,40);
	        
	        // winning condition
	        if(totalBricks<=0)
	        {
	            play=false;
	            
	            increaseLevel = true;
	            g.setColor(Color.red);
	            g.setFont(new Font("serif",Font.BOLD,35));
	            g.drawString("You Won!!",260,300);
	            
	            g.setFont(new Font("serif",Font.BOLD,20));
	            g.drawString("Press Enter to restart!",230,350);
	            ballstart = true;
	            gamereset = true;
	
	        }
	        
	        
	        // losing condition of a level
	        if( ballCount <=0 && lost == false)
	        {
	            play=false;
	            if(lifecount <= 0)
	            {
	            	lost = true;
	            }
	          
	            lifeLost = true;
	            
	            
	            g.setColor(Color.red);
	            g.setFont(new Font("serif",Font.BOLD,35));
	            
	            g.drawString("You Lost A life \n Scores: "+score,190,300);
	            g.setFont(new Font("serif",Font.BOLD,20));
	            g.drawString("Press Enter to restart!",230,350);
	            ballstart = true;
	        }
	     // condition if you loose
	        if(lost == true)
	        {
	        	g.setColor(Color.red);
	            g.setFont(new Font("serif",Font.BOLD,35));
	            g.drawString("You LOST",190,300);
	            g.drawString("Press Enter to restart!",230,350);
	            lifecount = 5;
	            levelNumber=1;
	            score=0;
	            gamereset = true;
	            lifeLost = false;
	            
	        }
	        
	        
	        
	        // newGame
	        if(newGame == true)
	        {
	        	
	        	lifecount = 5;
	            levelNumber=1;
	            score=0;
	            lifeLost = false;
	            newGame = false;
	            map=level.getlevel(levelNumber);
	            ballCount = 1;
            	totalBricks = map.totalBricks;
            	ballsOnFire = false;
            	map.purpleleft = false;
            	gameStart = false;
            	ballstart = true;
            	
            	lost = false;
	        }
	        
	        g.dispose();
        }
        else if(state == STATE.MENU)
        {
        	MENU.draw(g);
        	// help pop up
	        
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
       
        if(e.getKeyCode()==KeyEvent.VK_SPACE)// shots the ball when space is pressed
        {
        	SpacePressed();
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
            	gameStart = false;
            	
                if(lifeLost == true)// decreasing the life count
                {
	                lifecount--;
	                lifeLost = false;
                }
                
                if(increaseLevel == true)// increasing the level Number
	            {
	                levelNumber++;
	                increaseLevel = false;
                }
              
                if(gamereset == true)//resets the game and creates a new level
                {
                	map=level.getlevel(levelNumber); 
                	totalBricks = map.totalBricks;
                	ballsOnFire = false;
                	map.purpleleft = false;	
                	gamereset = false;
                	lost = false;
                }
                ballCount = 1; // return the ball count to 1
                
                ballstart = true;//causes the ball to stick to the paddle 
                repaint();
                
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
        	 GamePlay.this.SaveState.saveGame();
        	 play = false;
        	 state = STATE.MENU;
        	 SaveGame = true;
        }
        if(loadGame == true)
        {
        	SaveState.loadGame();
        	play = true;
        	loadGame = false;
        }
    }
      
    public void SpacePressed()// causes the ball to leave the paddle
    {
    	play = true;
    	gameStart = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) 
    {
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        timer.start();
        if(state == STATE.MENU)
        {
        this.addMouseListener(new MouseManager());
        }
        totalBricks = map.totalBricks;

        if ( map.greencount==0 && map.bluecount==0 && map.redcount==0) // checks if only purple bricks are left
        {
        	map.purpleleft = true;
        }
        
        if(map.purpleleft == true) // changes the ball to fire ball if only purple bricks are left
        {	
        	ballsOnFire = true;
        }
        
        if(ballsOnFire == true)// sets the ball image to the fire ball image
        {
        	ImageIcon img = new ImageIcon("62-Breakout-Tiles.png");
        	Image Img = img.getImage();
        	for(int index = 0 ; index < ballList.size() ; index++)
        	{
        		ballList.get(index).setBallImage(Img);
        	}
        }
        
        if(padd != null)
        {
        	if(padd.bulletsisActive == true)// checks if bullets are present on the screen
        	{
        		for(int i = 0 ; i < bullets.size();i++)
        		{
        			if(bullets.get(i).y <= 0)// removes those bullets from the bullet List which crosses the frame of the game
        			{
        				bullets.remove(i);
        			}
        		}
        	}
	        if(padd.bulletadding == true)// adding of bullets
	        {
	        	bullets.add(padd.bullet1);
	        	bullets.add(padd.bullet2);
	        	padd.bulletadding = false;
	        }
        }
        
        if(bullets.isEmpty() == false && padd.bulletsisActive == true)// moving of bullets
        {
        	for(int i = 0 ; i < bullets.size();i++)
        	{
        		bullets.get(i).move();
        	}        	
        }
        
        if(padd!= null)
        {
	        if(padd.isOnPower == true) // checks if the paddle has some power and if so it starts a timer
	        {
	        	count++;
	        }
        }
       
        
        if(play)
        {
        	// ball movement
        	for(int index = 0 ; index < ballList.size() ; index++)
        	{
            if(new Rectangle( ballList.get(index).positionX,ballList.get(index).positionY,20,20).intersects(new Rectangle(padd.getpositionX(),540,padd.getWidth(),padd.getHeight())))      // checks if ball intersects the brick
            {
                ballList.get(index).changeDY(); // changes the dy of the ball upon intersection 
            }
            
            A: for(int i=0;i<map.map.length;i++)        
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int brickX = map.bricks[i][j].x;
                        int brickY = map.bricks[i][j].y;
                        int brickWidth = 95;
                        int brickHeight=30;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect1 = ballList.get(index).getRect();
                        Rectangle brickRect=rect;
                        
                        if( ballRect1.intersects(brickRect))// checks if ball intersects the brick
                        {
                           // removes the brick
                        	if(ballsOnFire == true)// checks if the ball is a fire ball
                            {
                            	map.ballOnFire = true;
                            }
                        	map.setBrickValue(totalBricks,i,j);// removes or damages the brick on the screen
                            totalBricks = map.totalBricks;
                            if(map.map[i][j] <= 0)
                            {
                            score+=5;
                            }
                            
                            if((ballList.get(index).positionX+19<=brickRect.x||ballList.get(index).positionX+1>=brickRect.x+brickRect.width))// condition for changing the dx of the ball 
                            {
                                ballList.get(index).changeDX(); //changes the dx of the ball
                            }
                            else
                            {
                                
                                ballList.get(index).changeDY();// changes the dy of the ball
                            }
                            
                            Random rand = new Random();
                            int probnumber = (rand.nextInt(8)+1);// a random number for generating random powerup's
                            probnumber = 7;
                            
                            if((totalBricks%4 == 0)&& (padd.isOnPower == false))// generates power up if the ball hits any of the bricks 4 times and if the paddle is already not on a powerup
                            {
                            	// gets the power up 
                            	switch(probnumber)
                            	{
                            	case 1:
                            		power = Pfactory.getIncreasedSize(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            		
                            	case 2:
                            		power = Pfactory.getDecreasedSize(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            		
                            	case 3:
                            		power = Pfactory.getIncreasedSpeed(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            		
                            	case 4:
                            		power = Pfactory.getDecreasedSpeed(j*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            		
                            	case 5:
                            		power = Pfactory.getLife(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            		
                            	case 6:
                            		if(ballCount <= 1)// if there are already 3 or 2 balls then it wont generate this power up 
                            		power = Pfactory.getIncreasedBalls(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            	case 7:
                            		power = Pfactory.getBallOnFire(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            	case 8:
                            		power = Pfactory.getFirePowerUp(i*map.brickWidth+80, j*map.brickHeight+50);
                            		break;
                            	}
                            	
                            }
                            break A;
                        }
                    }
                }
            }
            
            
            ballList.get(index).moveX();

            ballList.get(index).moveY();
          // restricts the ball to stay within the frame  
            if(ballList.get(index).positionX<0 )
            {
                ballList.get(index).changeDX();
            }
            if(ballList.get(index).positionY<0)
            {
                ballList.get(index).changeDY();
            }
            if(ballList.get(index).positionX>670)
            {
                ballList.get(index).changeDX();
            }
        }
        }
        
        // condition when bullet intersects with the brick
        if(padd != null)
        {
	        if(padd.bulletsisActive == true)
	        {
		        for(int o = 0 ; o < bullets.size();o++)
		        {
		        	Rectangle bulletrect = bullets.get(o).getRect();
			        for(int i=0;i<map.map.length;i++)        
			        {
			            for(int j=0;j<map.map[0].length;j++)
			            {
			                if(map.map[i][j]>0)
			                {
		                        int brickX = map.bricks[i][j].x;
		                        int brickY = map.bricks[i][j].y;
			                    int brickWidth =95;
			                    int brickHeight=30;
			                    
			                    Rectangle Brickrect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
			                   
			                    if(bulletrect.intersects(Brickrect))
			                    {
			                    	//totalBricks=map.setBrickValue(totalBricks,i,j);
			                    	map.setBrickValue(totalBricks,i,j);
			                    	totalBricks = map.totalBricks;
			                    	if(map.map[i][j] <=0)
		                            score+=5;
		                            
		                            bullets.remove(o);
			                    }
			                }
			            }
			        }
		        }
	        }
        }
                    
                    
        //sets power as null when powers falls through the ball
        if(power != null)
        {
	        if(power.positionY >= 700)
	        {
	        	power = null;
	        }
        }
        
        // gives power to the paddle upon intersection with the powerUp
        if((padd!= null) && (power!= null))
        {
        	Rectangle powerRect = power.returnrect();
        	Rectangle paddRect = padd.getRect();
        	if(powerRect.intersects(paddRect))
        	{
        		padd.isOnPower = true;
        		if(power instanceof Size)// changes the size of the paddle
        		{
        			Image[] img = ((Size) power).getImages();
        			padd.SetImage(img);
        			padd.Width = ((Size) power).w;		
        		}
        		if(power instanceof Speed)// changes the speed of the paddle
        		{
        			((Speed) power).SpeedVaries();
        			padd.changex = padd.changex + ((Speed) power).changex;
        		}
        		
        		if(power instanceof life)
        		{
        			lifecount++;
        		}
        		
        		if(power instanceof BallOnFire)// causes the ball to be on fire
        		{
        			ballsOnFire = true;
        		}
        		
        		if(power instanceof FirePowerUp)// adds the bullet shooting functionality
        		{
        			Image[] img = ((FirePowerUp) power).getImages();
        			padd.SetImage(img);
        			
        			padd.bulletsisActive = true;
        			
        		}
        		
        		if(power instanceof increaseBalls)// adds 2 balls in the ball list
        		{
        			ballCount = ((increaseBalls) power).ballcount;
        			for(int i = 0 ; i<=1 ; i++)
        			{
        				ball = new Ball(ballList.get(0).positionX,ballList.get(0).positionY);
        				if(i == 0)
        				{
        					ball.dx = -2;
        					ball.dy = -1;
        				}
        				if(i == 1)
        				{
        					ball.dx = -3;
        					ball.dy = -2;
        				}
        				ballList.add(ball);
        			}
        		}
        		
        		power = null;
        	}
        }
        if(gameStart == false)// causes the ball to stick to the paddle
        {
        	for(int i = 0 ; i< ballList.size();i++)
        	{
        		ballList.get(i).positionX = padd.getpositionX()+45;
        	}
        }
        if(padd != null)// the timer after which the paddle loses its powerUp and reverts to its original condition 
        {
	        if(count > 1000 && padd.isOnPower == true)
	        {
	        	padd.isOnPower = false;
	        	padd.reset();
	        	ballsOnFire = false;
	        	map.ballOnFire = false;
	        	for(int i = 0 ; i< ballList.size();i++)
	        	{
	        		ballList.get(i).reset();
	        	}
	        	count = 0;
	        }
        }
        if(padd != null)
        padd.move();// causes the paddle to move
        repaint();
        }
        
    

    
    private class TAdapter extends KeyAdapter 
    {

        public void keyReleased(KeyEvent e) 
        {
        	if(padd != null)
            padd.keyReleased(e);// listens to the keyListener used in the paddle class
        }

        public void keyPressed(KeyEvent e) 
        {
        	if(padd != null)
            padd.keyPressed(e);// listens to the keyListener used in the paddle class
        }
    }
    
}

