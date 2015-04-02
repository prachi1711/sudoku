package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * This class represents list of Sudoku games. It reads the games from a text
 * file and adds them to a hashmap with the game numbers.
 *
 */

public class GameList {	
	Scanner sc = null;	
	
	/** Method : getFileData() reads the sudoku games from a text file
	 * @return a hashmap with list of games mapped to their game numbers
	 */
	public Map<Integer,int[][]> getFileData() 
	{						
		try
		{
			sc = new Scanner(new File("C:\\Users\\prachi\\workspace\\GenerateGame.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Exception::::"+e);
		}
		HashMap<Integer, int[][]> lGameMap = new HashMap<Integer,int[][]>();
		int gameCounter = 0;
		int lineCounter = 0;
		int k = 0;
		int row = 0;	
		int[][] game = null;
			while (sc.hasNextLine())    
			{											
				k = lineCounter % 10;
				lineCounter ++;
				String lLine = sc.nextLine();
				if(k == 0)
				{
					game = new int[9][9];
					continue;
				}				
				char[] source = lLine.toCharArray(); 						
				for(int j=0;j<9;j++)
				{
					game[j][row] = Integer.parseInt(Character.toString(source[j])); 
				}						
				
				row++;
				if(row==9)
				{
					gameCounter++;
					lGameMap.put(gameCounter, game);
					row = 0;					
				}
			}	
			if(sc!=null)
			sc.close();
			return lGameMap;
		
	}
	
}
