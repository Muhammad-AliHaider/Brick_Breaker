package project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Power  {

	protected int positionX;
	protected int positionY;
	protected int Width;
	protected int Height;
	protected int dy = +5;// speed at which the powerUp falls down
	protected Image image;
	
	
	public Power(int x, int y, String path, int number)
	{
		positionX = x;
		positionY = y;
		
		ImageIcon img = new ImageIcon(path + number + "-Breakout-Tiles.png");// sets the image of the PowerUp
		image = img.getImage();
		
		
		Width = image.getWidth(null);
		Height = image.getHeight(null);
	}
	
	public void draw(Graphics2D g)// draw the image of the powerUp
	{
		g.drawImage(image, positionX - (image.getWidth(null)/2), positionY - (image.getHeight(null)/2), null);
	}
	
	public void moveDown()// cause the powerUp to fall down
	{
		positionY = positionY + dy;
	}
	
	public void setImage(Image img)// sets the image of the power up
	{
		image = img;
	}
	
	public Rectangle returnrect()// draws a rectangle around the powerUp 
	{
		  Rectangle rect = new Rectangle(positionX,positionY,Width,Height);
		  return rect;
	}
	
}
