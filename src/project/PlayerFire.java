package project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class PlayerFire  {
	
	private int dy = -4;// speed at which the bullet moves
	private int x;
	protected int y;
	private int Width;
	private int Height;
	private Image[] img;
	private int count;
	
	public PlayerFire(int x,int y)// constructor
	{
		this.x = x;
		this.y = y;
		img = new Image[3];
		for(int i = 0; i< img.length;i++)
		{
			ImageIcon image = new ImageIcon((i+1) + ".png");
			img[i] = image.getImage();
			
			Width = img[i].getWidth(null);
			Height = img[i].getHeight(null);
		}
	}
	
	public void draw(Graphics2D g)// draws and iterates the bullet images
	{
		count = count %3;
		g.drawImage(img[count], x,y,null);
	}
	
	public Rectangle getRect()// draws a rectangle around the bullet
	{
		Rectangle bulletrect = new Rectangle(x,y,Width,Height);
		return bulletrect;
	}

	public void move()// causes the bullet to move 
	{
		
	 this.y = y + dy;	
	}

	
	
	
	
}
