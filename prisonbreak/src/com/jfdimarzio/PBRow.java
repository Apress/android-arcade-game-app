package com.jfdimarzio;

import java.util.Random;

public class PBRow {
	public PBBrick[] bricks;
	private Random brickType = new Random();
	private boolean isRowOdd = false;
	private int numberOfBricks = 0;
	
	public PBRow(int rowNumber){
		
		if(rowNumber % 2 > 0)
		{
			numberOfBricks = 4;
			isRowOdd = true;
		}
		else
		{
			numberOfBricks = 5;
			isRowOdd = false;
		}
		
		bricks = new PBBrick[numberOfBricks];
		
		for(int x = 0; x < numberOfBricks ; x++)
		{
			bricks[x] = new PBBrick((int) (brickType.nextFloat() * 7));
			if(isRowOdd)
			{
				bricks[x].posX = x - 2f ;
				bricks[x].posY = (rowNumber * .25f) + 1 ;
			}
			else
			{
				bricks[x].posX = x - 2.5f;
				bricks[x].posY = (rowNumber * .25f) + 1 ;
			}
			
		}
		
		
		
	}
}
