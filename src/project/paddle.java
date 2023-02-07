package project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class paddle 
{

	private static int positionX = 310;
	private static int positionY = 550;
	protected int Width;
	private int Height;
	private int dx= 0;
	private int orgWidth;// is used to set the paddle to original width
	protected int changex = 4;
	private int orgChangex = changex;
	private Image[] image = new Image[3];
	private Image[] orgImage;
	private static paddle Paddle = new paddle(positionX,positionY,""); // indicates the presence of Singleton 
	private int number = 50;
	public boolean isOnPower = false;
	private long count = 0;
	protected PlayerFire bullet1;
	protected PlayerFire bullet2;
	protected boolean bulletsisActive;
	protected boolean bulletadding;
	
	private paddle(int x,int y,String path)
	{
		positionX = x;
		positionY = y;
		
		for(int i =0;i<image.length;i++)
		{
		ImageIcon img = new ImageIcon(path + number++ + "-Breakout-Tiles.png");
		image[i] = img.getImage();
		
		
		Width = image[i].getWidth(null);
		Height = image[i].getHeight(null);
		}
		orgWidth = Width;
		orgImage = image;
	}
	
	public void setX(int x)// setter for x position of the paddle
	{
		positionX = x;
	}
	
	public static paddle getInstance()// setter for y position of the paddle
	{
		return Paddle;
	}
	
	public void draw(Graphics2D g)// is used to draw the image of the paddle
	{
		int i = (int)(count%image.length);
			g.drawImage(image[i], positionX - (image[i].getWidth(null)/80), positionY - (image[i].getHeight(null)/2), null);
			count++;
		
	}
	
	public void SetImage(Image[] image) //  a setter for the image array
	{
		this.image = image;
	}
	public int getpositionX() // a getter for the position x of the paddle
	{
		return positionX;
	}
	public int getWidth() // a getter for the width of the paddle
	{
		return Width;
	}
	public int getHeight()// a getter for the height of the paddle
	{
		return Height;
	}
	
	
	 public void keyPressed(KeyEvent e) 
	    {
	        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	        {
	            dx = +changex;// is used to change the dx of the paddle to +
	        }
	        if(e.getKeyCode()==KeyEvent.VK_LEFT)
	        {
	             dx = -changex; //  is used to change the dx of the paddle to -
	        }
	        if(e.getKeyCode()==KeyEvent.VK_SPACE) // is used to shot bullets when Spacebar is pressed 
	        {
	        	if(bulletsisActive == true)
	        	{
	        	bullet1 = new PlayerFire(positionX+Width,positionY);
	        	bullet2 = new PlayerFire(positionX,positionY);
	        	bulletadding = true;
	        	}
	        }
	    }
	 
	 public void keyReleased(KeyEvent e) 
	    {
		 if(e.getKeyCode()==KeyEvent.VK_RIGHT) // changes dx to 0 again
	        {
	            dx = 0;
	        }
	        if(e.getKeyCode()==KeyEvent.VK_LEFT)// changes dx to 0 again
	        {
	             dx = 0;
	        }
	    }
	 
	 public void move()
	 {
		 if((dx>0))//it controls the right movement of the player and doesn't let it pass the bounds of the screen
			 if(positionX>=600)
	            {
	                positionX=600;
	            }
	            else
	            {
	                movement();
	            }
			
			if((dx<0))//it controls the left movement of the player and doesn't let it pass the bounds of the screen
			{
				if(positionX<=10)
				{
					positionX = 10;
				}
				else {
					movement();
				}
			}
	 }
	 
	 public void movement()// is used to move the paddle in left or right direction
	    {
	        positionX+=dx;
	        Paddle.setX(positionX);
	    }
	 
	 public void reset()// sets the paddle to its original state
	 {
		 Width = orgWidth;
		 changex = orgChangex;
		 image = orgImage;
		 bulletsisActive= false;
	 }
	    
	 public Rectangle getRect() //  is used to draw a rectangle around the paddle
	 {
		 Rectangle rect = new Rectangle(positionX,positionY,Width,Height);
		 return rect;
	 }
	

}
