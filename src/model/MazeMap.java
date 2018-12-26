package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

public class MazeMap
{
	public int Xsize,Ysize,Xstart,Ystart,Xend,Yend;
	public MazeUnit[][] map;
	
	private Random random;
	
	public MazeMap(int Xsize,int Ysize)
	{
		this.Xsize = Xsize;
		this.Ysize = Ysize;
		random = new Random();
		this.createMaze();
	}
	
	private void createMaze()
	{
		int i,j,x,y;
		WallUnit temp;
		
		map = new MazeUnit[Xsize][Ysize];
		boolean[][] mazeUnitVisited = new boolean[Xsize][Ysize];
		for (i=0;i<Xsize;++i)
		{
			for (j=0;j<Ysize;++j)
				map[i][j] = new MazeUnit();
			Arrays.fill(mazeUnitVisited[i],false);
		}
		
		ArrayList<WallUnit> walls;
		
		//选取起点和终点，并将起始点周围的墙加入walls中
		temp = getRandomBoundaryWall();
		Xstart = temp.x;
		Ystart = temp.y;
		switch (temp.wallPosition)
		{
			case Left:
				map[Xstart][Ystart].Left = false;
				break;
			case Right:
				map[Xstart][Ystart].Right = false;
				break;
			case Up:
				map[Xstart][Ystart].Up = false;
				break;
			case Down:
				map[Xstart][Ystart].Down = false;
				break;
		}
		do
		{
			temp = getRandomBoundaryWall();
			Xend = temp.x;
			Yend = temp.y;
		}while (Math.abs(Xend-Xstart)<Xsize/2 || Math.abs(Yend-Ystart)<Ysize/2);
		switch (temp.wallPosition)
		{
			case Left:
				map[Xend][Yend].Left = false;
				break;
			case Right:
				map[Xend][Yend].Right = false;
				break;
			case Up:
				map[Xend][Yend].Up = false;
				break;
			case Down:
				map[Xend][Yend].Down = false;
				break;
		}
		walls = getAroundWalls(Xstart,Ystart);
		mazeUnitVisited[Xstart][Ystart] = true;
		
		while(!walls.isEmpty())
		{
			temp = walls.remove(random.nextInt(walls.size()));
			switch (temp.wallPosition)
			{
				case Left:
					x = temp.x;
					y = temp.y-1;
					if (x>=0 && x<Xsize && y>=0 && y<Ysize && !mazeUnitVisited[x][y])
					{
						mazeUnitVisited[x][y] = true;
						map[temp.x][temp.y].Left = false;
						map[x][y].Right = false;
						walls.addAll(getAroundWalls(x,y));
					}
					break;
				case Right:
					x = temp.x;
					y = temp.y+1;
					if (x>=0 && x<Xsize && y>=0 && y<Ysize && !mazeUnitVisited[x][y])
					{
						mazeUnitVisited[x][y] = true;
						map[temp.x][temp.y].Right = false;
						map[x][y].Left = false;
						walls.addAll(getAroundWalls(x,y));
					}
					break;
				case Up:
					x = temp.x-1;
					y = temp.y;
					if (x>=0 && x<Xsize && y>=0 && y<Ysize && !mazeUnitVisited[x][y])
					{
						mazeUnitVisited[x][y] = true;
						map[temp.x][temp.y].Up = false;
						map[x][y].Down = false;
						walls.addAll(getAroundWalls(x,y));
					}
					break;
				case Down:
					x = temp.x+1;
					y = temp.y;
					if (x>=0 && x<Xsize && y>=0 && y<Ysize && !mazeUnitVisited[x][y])
					{
						mazeUnitVisited[x][y] = true;
						map[temp.x][temp.y].Down = false;
						map[x][y].Up = false;
						walls.addAll(getAroundWalls(x,y));
					}
					break;
			}
		}
	}
	
	private WallUnit getRandomBoundaryWall()
	{
		int x,y;
		WallPositionEnum wallPosition;
		switch (random.nextInt(4))
		{
			case 0:
				x = random.nextInt(Xsize-1);
				y = 0;
				wallPosition = WallPositionEnum.Left;
				break;
			case 1:
				x = 0;
				y = 1+random.nextInt(Ysize-1);
				wallPosition = WallPositionEnum.Up;
				break;
			case 2:
				x = Xsize-1;
				y = random.nextInt(Ysize-1);
				wallPosition = WallPositionEnum.Down;
				break;
			case 3:
				x = 1+random.nextInt(Xsize-1);
				y = Ysize-1;
				wallPosition = WallPositionEnum.Right;
				break;
			default:
				x = y = 0;
				wallPosition = WallPositionEnum.Left;
		}
		return new WallUnit(x,y,wallPosition);
	}
	private boolean isLegalWall(WallUnit wall)
	{
		return !(	(wall.x==0 && wall.wallPosition==WallPositionEnum.Up)
				 || (wall.y==0 && wall.wallPosition==WallPositionEnum.Left)
				 || (wall.x==Xsize-1 && wall.wallPosition==WallPositionEnum.Down)
				 || (wall.y==Ysize-1 && wall.wallPosition==WallPositionEnum.Right)
				);
	}
	private ArrayList<WallUnit> getAroundWalls(int x,int y)
	{
		ArrayList<WallUnit> walls = new ArrayList<WallUnit>();
		WallUnit temp;
		if (map[x][y].Up && isLegalWall(temp=new WallUnit(x,y,WallPositionEnum.Up)))
			walls.add(temp);
		if (map[x][y].Down && isLegalWall(temp=new WallUnit(x,y,WallPositionEnum.Down)))
			walls.add(temp);
		if (map[x][y].Left && isLegalWall(temp=new WallUnit(x,y,WallPositionEnum.Left)))
			walls.add(temp);
		if (map[x][y].Right && isLegalWall(temp=new WallUnit(x,y,WallPositionEnum.Right)))
			walls.add(temp);
		return walls;
	}
}
