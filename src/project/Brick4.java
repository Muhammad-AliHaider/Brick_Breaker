package project;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Brick4 extends Brick1
{
    protected Image img;
    protected ImageIcon imgIcon;
    
    public Brick4(String path) //unbreakable brick, destroyed after all bricks are destroyed or using ballOnFire PowerUp
    {
        super(path);
        this.imgIcon = new ImageIcon(path);
        img = imgIcon.getImage();
    }
    public void setBrickImage() 
    {
        this.imgIcon = new ImageIcon("07-Breakout-Tiles.png");
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
