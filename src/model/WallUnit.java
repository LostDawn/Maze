package model;

public class WallUnit
{
	public int x,y;
	public WallPositionEnum wallPosition;
	
	public WallUnit()
	{
		x = y = 0;
		wallPosition = WallPositionEnum.Left;
	}
	public WallUnit(int x,int y,WallPositionEnum wallPosition)
	{
		this.x = x;
		this.y = y;
		this.wallPosition = wallPosition;
	}
}
