package project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogueBox {
	  
		JFrame f;  
		DialogueBox(){  
		    f=new JFrame();  
		    JOptionPane.showMessageDialog(f,"1) paddle will move with the arrow key \n2) green brick  will take one hit to break \n3) blue brick will take 2 hits to break \n4) red brick will take 3 hits to break \n5) purple bricks are unbreakable unless red ball hits them \n6) powerups will fall from above \n7) after entering press space bar to start the game \n8) Space bar is used to shoot bullets when power up is gained \n9) first 7 levels are pre-defined then the random level gerators starts working \n10) press ESC to save and open the menu");
		}

}
