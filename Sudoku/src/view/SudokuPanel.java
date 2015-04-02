package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SudokuController;
import model.Game;


/**
 * This class draws the sudoku in the Sudoku Panel 
 *
 */
public class SudokuPanel extends JPanel implements Observer  {
    
    private Cell[][] cells;       // Array of cells.
    private JPanel[][] panels;    // Panels holding the cells.
    private Game game;    // current game object

    
    /**
     * Constructs the panel, adds sub panels and adds cells to these sub panels.
     */
    public SudokuPanel(Game game) {
        super(new GridLayout(3, 3));
        this.game = game;
        SudokuController sudokuController = new SudokuController(this,game);
        panels = new JPanel[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panels[y][x] = new JPanel(new GridLayout(3, 3));
                panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[y][x]);
            }
        }

        cells = new Cell[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cells[y][x] = new Cell(x, y);
                cells[y][x].addKeyListener(sudokuController);
                cells[y][x].addFocusListener(sudokuController);//for undo ,redo and hint functions
                panels[y / 3][x / 3].add(cells[y][x]);
            }
        }
    }

   
	/**
     * Sets the fields corresponding to given game.
     *
     * @param game  Game to be set.
     */
    public void setGame(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cells[y][x].setBackground(Color.WHITE);
                cells[y][x].setNumber(game.getNumber(x, y), false);
                cells[y][x].setHorizontalAlignment(JTextField.CENTER);
                if(game.getNumber(x, y) > 0)
                {
                	cells[y][x].setEditable(false);                   	             
                }
                else
                {
                	cells[y][x].setEditable(true);                	
                }
            }
        }
    }

    /**
     * sets game after performing undo
     */
    public void setGameWithUndoValues(Game game) 
    {
         cells[game.lastEditedY][game.lastEditedX].setNumber(game.getNumber(game.lastEditedX, game.lastEditedY),true);    	                        
    }
    
    /**
     * sets game with hint value generated using solution
     */
    public void setGameWithCorrectValues(Game game)
    {
    	cells[game.lastEditedY][game.lastEditedX].setNumber(game.getNumber(game.lastEditedX, game.lastEditedY),true);	
    	cells[game.lastEditedY][game.lastEditedX].setBackground(Color.PINK);
    }
    /**
     * Adds controller to all sub panels.
     *
     * @param sudokuController  Controller which controls all user actions of Sudoku Panel.
     */
    public void setController(SudokuController sudokuController) {     	
    }


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * updation of Sudoku Panel for new Game generated based on user selections
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		if(arg == "NEW_GAME")
		{
			setGame((Game)o);
		}	
		else if(arg == "UNDO")
		{
			setGameWithUndoValues((Game)o);
		}
		else if(arg == "REDO")
		{
			setGameWithUndoValues((Game)o);
		}
		else if(arg == "HINT")
		{
			setGameWithCorrectValues((Game)o);
		}
	}

}