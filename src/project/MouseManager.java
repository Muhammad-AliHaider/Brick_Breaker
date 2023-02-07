package project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	// adds the functionality of the button
	public void mousePressed(MouseEvent e) {
		
		if(GamePlay.state == GamePlay.STATE.MENU)
		{
			int mx = e.getX();
			int my = e.getY();
	
			// play Button
			
			if(mx >= 275 && mx<=425)
			{
				if(my>= 200 && my<= 250)
				{
					GamePlay.newGame = true;
					GamePlay.state = GamePlay.STATE.GAME;
					GamePlay.Help = false;
				}
			}
			if(GamePlay.SaveGame == true)
			{
			// LoadButton
				if(mx >= 275 && mx<=425)
				{
					if(my>= 300 && my<= 350)
					{
						GamePlay.loadGame = true;
						GamePlay.state = GamePlay.STATE.GAME; 
					}
				}
			}
			if(GamePlay.SaveGame == false && GamePlay.Help == false)
			{
			
				if(mx >= 275 && mx<=425)
				{
					if(my>= 300 && my<= 350)
					{
						new DialogueBox();
						GamePlay.Help = true;
					}
				}
			}
			
			
			// QuitButton
			
			if(mx >= 275 && mx<=425)
			{
				if(my>= 400 && my<= 450)
				{
					System.exit(1);
				}
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
