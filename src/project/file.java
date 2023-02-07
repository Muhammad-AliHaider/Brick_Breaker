package project;

import java.io.*;
import java.util.ArrayList;

public class file implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private ArrayList<Object> objects = new ArrayList<Object>();

public void addObject(Object o)
        {
        objects.add(o);
        }


public void saveGame()
        {
        try
        {
        FileOutputStream saveFile=new FileOutputStream("GameSave.sav");

        // Create an ObjectOutputStream to put objects into save file.
        ObjectOutputStream save = new ObjectOutputStream(saveFile);

        // used to save the objects
        for(int i = 0; i < objects.size();i++)

        {
        save.writeObject(objects.get(i));
        }
        save.close(); //close the save file
        }
        catch(Exception e)
        {
        e.printStackTrace();//gives Step by step explanation of the error
        }
        }

        public GamePlay loadGame()
        {
        GamePlay stuff = new GamePlay();// game play object
        try
        {
        // Open file to read from the  file GameSave.sav.
        FileInputStream saveFile = new FileInputStream("GamePlay.sav");

        // for getting the objects from the saved file.
        ObjectInputStream save = new ObjectInputStream(saveFile);

        stuff = (GamePlay) save.readObject();

        save.close(); // This also closes saveFile.

        }
        catch(Exception e)
        {
        }

        return stuff;
        }
        }