package project;

public class PowerUPFactory  {
	
	public Power getIncreasedSize(int x , int y) //  returns the PowerUp for increasing the size of the paddle
	{
		Power p = new Size(x,y,"" ,47, true);
		return p;
	}
	
	public Power getDecreasedSize(int x , int y)// returns the PowerUp for decreasing the size of the paddle
	{
		Power p = new Size(x,y,"" ,46, false);
		return p;
	}
	
	public Power getLife(int x , int y)// returns the  life powerUp  
	{
		Power p = new life(x,y,"" ,60);
		return p;
	}
	
	public Power getIncreasedSpeed(int x, int y) //  returns The powerUp for increasing the Speed of the paddle
	{
		Power p = new Speed(x,y,"",42, true);
		return p;
	}
	
	public Power getDecreasedSpeed(int x, int y) // returns The powerUp for Decreasing the Speed of the paddle
	{
		Power p = new Speed(x,y,"",41, false);
		return p;
	}
	
	public Power getIncreasedBalls(int x, int y)// returns the powerUp for increasing the balls on the screen
	{
		Power p = new increaseBalls(x,y,"",43);
		return p;
	}
	
	public Power getFirePowerUp(int x,int y)// returns the powerUp for Firing bullets
	{
		Power p = new FirePowerUp(x,y,"",48);
		return p;
	}
	
	public Power getBallOnFire(int x,int y)// return the powerUp for ball on fire power
	{
		Power p = new BallOnFire(x,y,"",44);
		return p;
	}
	
}
