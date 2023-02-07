package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	Rectangle PlayButton = new Rectangle(275,200,150,50);// play
	Rectangle LoadButton = new Rectangle(275,300,150,50);// load
	Rectangle HelpButton = new Rectangle(275,300,150,50);// help
	Rectangle QuitButton = new Rectangle(275,400,150,50);//quit
	public void draw(Graphics g)
	{
		//for drawing the buttons and their borders
		g.setColor(Color.WHITE);
    	g.setFont(new Font("Serif",Font.BOLD, 40));
    	g.drawString("Brick Breaker", 225, 150);

    	Graphics2D g2d = (Graphics2D) g;
    	g.setFont(new Font("Serif",Font.BOLD, 25));
    	g.setColor(Color.yellow);
    	g2d.draw(PlayButton);
    	if(GamePlay.SaveGame == false)// writes Play the first time
    	{
    	g.drawString("Play", PlayButton.x+50, PlayButton.y+35);
    	}
    	if(GamePlay.SaveGame == true) // write new game after than
    	{
    		g.drawString("New Game", PlayButton.x+20, PlayButton.y+35);
    	}
    	if(GamePlay.SaveGame == false) // draws the load button when game has been saved
    	{	
    		g2d.draw(HelpButton);
    		g.drawString("Help", HelpButton.x+50, HelpButton.y+35);
    	}
    	if(GamePlay.SaveGame == true) // draws the load button when game has been saved
    	{	
    		g2d.draw(LoadButton);
    		g.drawString("Load", LoadButton.x+50, LoadButton.y+35);
    	}
    	
    	g2d.draw(QuitButton);// draws the quit button
    	g.drawString("Quit", QuitButton.x+50, QuitButton.y+35);
    	
    	
	}

}
