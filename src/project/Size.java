package project;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Size extends Power 
{
	public boolean checker = false;// is used to distinguish between increase and decrease size powerUp
	
	public int w;
	public int h;
	
	public Size(int x, int y, String path, int number, boolean o)
	{
		super(x,y,path,number);
		checker =o;
	}
	
	
	
	public Image[] getImages()
	{
		Image[] img = new Image[1];
		if(checker == true)// returns the image of the increased sized paddle, true indicates the Increase Size powerUp
		{
		ImageIcon image = new ImageIcon("56-Breakout-Tiles.png");
		img[0] = image.getImage();
		
		w = img[0].getWidth(null);
		h = img[0].getHeight(null);
		
		}
		else // returns the image of the decreased sized paddle, false indicates the Decrease Size powerUp
		{
			ImageIcon image = new ImageIcon("57-Breakout-Tiles.png");
			img[0] = image.getImage(); 
			w = img[0].getWidth(null);
			h = img[0].getHeight(null);
		}
		return img;
	}
			
				
}
	

