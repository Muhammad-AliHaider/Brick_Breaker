package project;

import java.util.Random;

// this class has & predefined levels and then it just generates levels at random
public class Levels  {
	private MapGenerator map;
	
	public MapGenerator getlevel(int number)// return the level generated 
	{
		switch(number) {
		
		case 1:// indicate level 1
			
			map = new MapGenerator(2,3);
			break;
		case 2:// indicate level 2
			map = new MapGenerator(2,2);
			map.isPlus = true;
			break;
			
		case 3:// indicate level 3
			map = new MapGenerator(2,4);
			break;
			
		case 4:// indicate level 4
			map = new MapGenerator(2,3);
			map.isPlus = true;
			break;
			
		case 5:// indicate level 5
			map = new MapGenerator(3,3);
			map.isPlus = true;
			break;
			
		case 6:// indicate level 6
			map = new MapGenerator(4,4);
			break;
			
		case 7:// indicate level 7
			map = new MapGenerator(4,4);
			map.isPlus = true;
			break;
			
		default:// indicates random level generator
			
			Random x = new Random();
			int r = x.nextInt(4)+1;
			int c = x.nextInt(4)+1;
			int isD = x.nextInt(3);
			
			map = new MapGenerator(r,c);
			if(isD == 2 || isD== 1)
				map.isPlus = true;
			break;
	}
			
		return map;
		
	}

}
