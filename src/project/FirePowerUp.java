package project;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FirePowerUp extends Power 
{

	public int w;
	public int h;
	public FirePowerUp(int x, int y, String path, int number)// is used to make the object of FirePowerUp 
	{
		super(x, y, path, number);
		
	}
	
	public Image[] getImages()// is used to set the imgs of the bullet
	{
		Image[] img = new Image[3];
	
		int number = 53;
		for(int i = 0;i<img.length ; i++)
		{
		ImageIcon image = new ImageIcon(""+number++ +"-Breakout-Tiles.png");
		img[i] = image.getImage();
		
		w = img[i].getWidth(null);
		h = img[i].getHeight(null);
		}
		
			
		return img;
	}
			
	

}
