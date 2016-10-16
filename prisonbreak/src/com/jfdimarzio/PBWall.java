package com.jfdimarzio;

public class PBWall {
	public PBRow[] rows;

	public PBWall(int numberOfRows){
		
		rows = new PBRow[numberOfRows];
		
		for(int x = 0; x <= numberOfRows - 1; x ++)
		{
			rows[x] = new PBRow(x);
		}
		
	}
}
