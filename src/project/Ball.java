package project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Ball {
	
	/**
	 * 
	 */
	protected int positionX;
	protected int positionY;
	protected int dx = -1;
	protected int dy = -2;
	protected int w;
	protected int h;
	protected transient Image ballImage;
	protected boolean ballOnFire = false;
	protected Image orgImage;

	public Ball(int x,int y)// creates the ball object
	{
		positionX = x;
		positionY = y;
		
		ImageIcon img = new ImageIcon("58-Breakout-Tiles.png");
		ballImage = img.getImage();
		
		w = ballImage.getWidth(null);
		h = ballImage.getHeight(null);
		
		orgImage = ballImage; 
	}
	
	public void draw(Graphics2D g) // is used to draw the ball when the function is called
	{
		g.drawImage(ballImage, positionX, positionY, null);
	}
	
	public void moveX() // causes the ball to move along the x-axis
	{
		positionX = positionX+dx;
	}
	
	public void moveY()// cause the ball to move along the y-axis
	{
		positionY = positionY+dy;
	}
	
	public void changeDX()// cause the ball to change direction along x-axis
	{
		dx = -dx;
	}
	
	public void changeDY()// cause the ball to change direction along y-axis
	{
		dy = -dy;
	}
	
	public void setBallImage(Image img)// getter for the image of the ball
	{
		ballImage = img;
	}
	
	public void reset() // causes the fire ball img to revert back  to the original ball img
	{
		ballImage = orgImage;
	}
	
	public Rectangle getRect()//is used to draw the rectangle
	{
		Rectangle ballrect = new Rectangle(positionX,positionY,w,h);
		return ballrect;
	}

}
