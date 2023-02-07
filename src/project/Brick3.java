package project;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Brick3 extends Brick1
{
    protected Image img;
    protected ImageIcon imgIcon;
    
    public Brick3(String path) //brick type 3 destroyed by 3 hits
    {
        super(path);
        this.imgIcon = new ImageIcon(path);
        img = imgIcon.getImage();
    }
    public void setBrickImage() //image set to cracked brick
    {
        this.imgIcon = new ImageIcon("08-Breakout-Tiles.png");
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
