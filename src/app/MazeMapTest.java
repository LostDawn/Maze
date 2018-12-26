package app;

import model.*;

public class MazeMapTest
{
	public static void main(String[] args)
	{
		MazeMap mazeMap = new MazeMap(4,4);
		int i,j,t0,t1,t2,t3;
		for (i=0;i<4;++i)
		{
			for (j=0;j<4;++j)
			{
				t1=t2=t3=t0=0;
				if (mazeMap.map[i][j].Up)
					t3=1;
				if (mazeMap.map[i][j].Right)
					t2=1;
				if (mazeMap.map[i][j].Down)
					t1=1;
				if (mazeMap.map[i][j].Left)
					t0=1;
				System.out.print("" + t3 + t2 + t1 + t0 + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println(mazeMap.Xstart + " " + mazeMap.Ystart);
		System.out.println(mazeMap.Xend + " " + mazeMap.Yend);
	}
}
