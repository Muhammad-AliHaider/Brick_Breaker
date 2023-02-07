package project;


import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Brick1 
{
    protected Image img;
    protected ImageIcon imgIcon;
    protected int x;
    protected int y;
    protected boolean isMoveable;
    protected int hitTaken;
    
    public Brick1(String path) //brick type 1 destroyed by single hit
    {        
        this.imgIcon = new ImageIcon(path);
        img = imgIcon.getImage();         
    }
    public void setBrickImage() //path set to cracked brick
    {
        this.imgIcon = new ImageIcon("04-Breakout-Tiles.png");
        this.img = imgIcon.getImage();
    }
    public Image getImage()
    {
        return img;
    }
    
    public void paint(Graphics2D g,int x,int y)
    {
    
        g.drawImage(this.img, x, y, null);
    }

    
}
