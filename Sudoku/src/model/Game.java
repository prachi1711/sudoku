package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;


/**
 * This class represents a Sudoku game. It generates the game based on user game selection
 *  and methods to check the validation of the user input.
 *
 */
public class Game  extends Observable{    
    private int[][] game;           // Generated game with user input.
    private int[][] solution;           // Generated game with solution.    
    private int userEnteredVal;     // Entered value by user.   
    private String strGameLevel; // Game Level
    GameList gL = new GameList(); //Object of Class containing List of Games
    public int lastEditedX = 0;  // last edited position X of cell
    public int lastEditedY = 0; // last edited position Y of cell
    public int prevVal = 0; // last edited value used for performing undo operation
    public int curVal = 0;// current value used for performing undo operation
        
    /**
     * Constructor
     */
    public Game() {
    	this.setUserEnteredVal(1);
        this.setGameLevel(EASY_LEVEL);
    	newGame();  
        
    }

    /**
     * Generates a new Sudoku game.    
     */
    public void newGame() {
    	
    	game = generateGame(new int[9][9], this.getUserEnteredVal(),this.getGameLevel());    	
    	setChanged();
        notifyObservers("NEW_GAME");
    }
   
    public void undo()
    {    	
    	setNumber(this.lastEditedX, this.lastEditedY,this.prevVal);    	
    	setChanged();
        notifyObservers("UNDO");
    }
    
    public void redo()
    {    	
    	setNumber(this.lastEditedX, this.lastEditedY,this.curVal);    	
    	setChanged();
        notifyObservers("REDO");
    }
    public void provideHint()
    {
    	int correctAns = 0;
    	prevVal =  getNumber(lastEditedX, lastEditedY);
    	correctAns = solution[this.lastEditedY][this.lastEditedX];
    	setNumber(this.lastEditedX, this.lastEditedY,correctAns);    	
    	curVal = correctAns;
    	setChanged();
        notifyObservers("HINT");
    }
    
    /**
     * Sets the Game No entered by user
     * @param userEnteredVal  Game No entered by user
     */
    public void setUserEnteredVal(int userEnteredVal) {
        this.userEnteredVal = userEnteredVal;        
    }
   
    /**
     * Gets the Game No entered by user
     * @return Game No entered by user
     */
    public int getUserEnteredVal() {
        return userEnteredVal;
    }
    

    /**
     * Sets the level of Game selected by user
     * @param strGameLevel
     */
    public void setGameLevel(String strGameLevel)
    {
    	this.strGameLevel = strGameLevel;
    }
    
    /**
     * Gets the level of Game set by user
     * @return    Game Level
     */
    public String getGameLevel()
    {
    	return strGameLevel;
    }
    
    
   
    /**
     * Sets given number on given position in the game.
     *
     * @param x         The x position in the game.
     * @param y         The y position in the game.
     * @param number    The number to be set.
     */
    public void setNumber(int x, int y, int number) {
        game[y][x] = number;
    }

    /**
     * Returns number at given position.
     *
     * @param x     X position in game.
     * @param y     Y position in game.
     * @return      Number at given position.
     */
    public int getNumber(int x, int y) {
        return game[y][x];
    }
        

    /**
     * Returns game(selected by user) with predefined numbers.
     * @param game         Empty array of game
     * @param gameNo       Game no selected by user
     * @param gamelevel    Game level selected by user
     * @return             Game selected by user
     */
    public int[][] generateGame(int[][] game,int gameNo,String gamelevel )
    {
    	HashMap<Integer, int[][]> lGameMap = new HashMap<Integer,int[][]>();
    	lGameMap=(HashMap<Integer, int[][]>) gL.getFileData();	
    	
    	if(gameNo == 1 && gamelevel.equalsIgnoreCase(EASY_LEVEL))
    	{
    		solution = lGameMap.get(1);
    		game = generateGameForDisplay(EASY_BLOCKS,copy(solution));
    	}
    	else if(gameNo == 2 && gamelevel.equalsIgnoreCase(EASY_LEVEL))
    	{
    		solution = lGameMap.get(2);
    		game = generateGameForDisplay(EASY_BLOCKS,copy(solution));
    	}
    	else if(gameNo == 3 && gamelevel.equalsIgnoreCase(EASY_LEVEL))
    	{
    		solution = lGameMap.get(3);
    		game = generateGameForDisplay(EASY_BLOCKS,copy(solution));	
    	}
    	else if(gameNo == 1 && gamelevel.equalsIgnoreCase(MED_LEVEL))
    	{
    		solution = lGameMap.get(4);
    		game = generateGameForDisplay(MED_BLOCKS,copy(solution));
    	}
    	else if(gameNo == 2 && gamelevel.equalsIgnoreCase(MED_LEVEL))
    	{
    		solution = lGameMap.get(5);
    		game = generateGameForDisplay(MED_BLOCKS,copy(solution));	 		
    	}
    	else if(gameNo == 3 && gamelevel.equalsIgnoreCase(MED_LEVEL))
    	{
    		solution = lGameMap.get(6);
    		game = generateGameForDisplay(MED_BLOCKS,copy(solution));	 	
    	}    	
    	else if(gameNo == 1 && gamelevel.equalsIgnoreCase(HARD_LEVEL))
    	{
    		solution = lGameMap.get(7);
    		game = generateGameForDisplay(HARD_BLOCKS,copy(solution));			    		
    	}
    	else if(gameNo == 2 && gamelevel.equalsIgnoreCase(HARD_LEVEL))
    	{
    		solution = lGameMap.get(8);
    		game = generateGameForDisplay(HARD_BLOCKS,copy(solution));    		
    	}
    	else if(gameNo == 1 && gamelevel.equalsIgnoreCase(EVIL_LEVEL))
    	{
    		solution = lGameMap.get(9);
    		game = generateGameForDisplay(EVIL_BLOCKS,copy(solution));    		     		     		    
    	}
    	else if(gameNo == 2 && gamelevel.equalsIgnoreCase(EVIL_LEVEL))
    	{
    		solution = lGameMap.get(10);
    		game = generateGameForDisplay(EVIL_BLOCKS,copy(solution));		     		     		    
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "No such Game exists.Please select another game.", "Message", JOptionPane.PLAIN_MESSAGE );
    	}
    	 return game;    
    }
    
    
    /**
     * Checks if user has entered all the values in the cells
     * @param game  Existing Game Values
     * @return  true or false according to the values entered by user
     */
    public boolean checkAllValuesEntered(Game game)
    {
    	for(int i=0;i<9;i++)
    	{
    		for(int j=0;j<9;j++)
    		{
    			if(game.getNumber(i, j) <= 0)
    			 return false;    				    		
    		}
    	}
    	return true;
    }
    
    /**
     * Checks if the solution entered by user is correct or not
     * @param i        x position of the number(cell)
     * @param j        y position of the number(cell)
     * @param game   current game object
     * @return         true or false based on whether the solution is correct or not
     */
    public boolean isValid(int i,int j,Game game)
    {
    	for(int col=0;col<9;col++)
    	{
    	   if(col!=j && game.getNumber(i, col) == game.getNumber(i, j))
    	   return false;	
    	}
    	
        for (int row = 0; row < 9; row++)
        {
        	 if (row != i && game.getNumber(row,j) == game.getNumber(i,j))
        	 return false;
        }
   
    	for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
    	{
    		for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
    		{
    		      if (row != i && col != j && game.getNumber(row,col) == game.getNumber(i,j))
    		        return false;
    		}
    	}
    
    	return true;
    }
    
    /**
     * It takes the game and removes certain numbers based on the selected 
     * difficulty level
     * @param checkVal    difficulty level selected
     * @param game        game with entire solution
     * @return            game with predefined numbers
     */
    public int[][] generateGameForDisplay(int checkVal,int[][] game)
    {
    	ArrayList<Integer> lRandomNo = new ArrayList<Integer>();
		int randomNo = 0;
		int ctr=0;
		while(true)
		{
			randomNo = 1 + (int)(Math.random() * ((80 - 1) + 1));
			if(!lRandomNo.contains(randomNo))
			{
				lRandomNo.add(randomNo);
				ctr++;    				
			}    		
			if(ctr==checkVal)
				break;
		}		
		for(int i =0;i<lRandomNo.size();i++)
		{
			int temp = Integer.parseInt(lRandomNo.get(i).toString());
			int x = temp % 9;
			int y = temp/9;
			game[y][x] = 0;
		}
		return game;
    }
    
    /**
     * Copies a game.
     *
     * @param game      Game to be copied.
     * @return          Copy of given game.
     */
    private int[][] copy(int[][] game) {
        int[][] copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                copy[y][x] = game[y][x];
        }
        return copy;
    }
    
    public final static String EASY_LEVEL = "Easy";
    final static String MED_LEVEL = "Medium";
    final static String HARD_LEVEL = "Hard";
    final static String EVIL_LEVEL = "Evil";
    final static int EASY_BLOCKS = 47; //no of blank cells in game
    final static int MED_BLOCKS = 49;  //no of blank cells in game
    final static int HARD_BLOCKS = 55; //no of blank cells in game
    final static int EVIL_BLOCKS = 57; //no of blank cells in game
    
}

