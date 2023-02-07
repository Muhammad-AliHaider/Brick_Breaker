package project;

public class Speed extends Power {

	public boolean checker = false;// is used to distinguish between fast and slow powerUp
	public int changex = 0;// the speed of the paddle
	public Speed(int x, int y, String path, int number, boolean o)
	{
		super(x,y,path,number);
		checker =o;
	}
	
	public void SpeedVaries()
	{
		if(checker == true)// indicates increase in speed
		{
			changex = 4;
		}
		else// indicate decrease in speed
		{
			changex = -2;
		}
	}
	
	
	
}
