package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Game;
import view.Cell;
import view.SudokuPanel;

/**
 * This class controls all actions performed on SudokuPanel by user.
 * 
 */
public class SudokuController implements KeyListener,FocusListener {
    private SudokuPanel sudokuPanel;    // Panel to control
    private Game game ;         // Current Sudoku game

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public SudokuController(SudokuPanel sudokuPanel, Game game) {
        this.sudokuPanel = sudokuPanel;
        this.game = game;
    }
  
   
	@Override
	public void keyPressed(KeyEvent e) 
	{
			
	}

	/* Sets the value entered by user in the cell in Sudoku Panel and stores the last edited value
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
		int x,y = 0;
		int userVal = 0;
		Cell c = (Cell)e.getSource();				
		x = c.getFieldX();
		y = c.getFieldY();
		
		if(c.getText()!=null && !c.getText().equalsIgnoreCase(""))
		{
		   
		   game.prevVal =  game.getNumber(x, y);
		   game.lastEditedX = x;
		   game.lastEditedY = y;
		   
		   userVal = Integer.parseInt(c.getText());
		   game.setNumber(x, y, userVal);
		   c.setNumber(userVal,true);		
		   game.curVal = userVal;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
			
	}


	@Override
	/**
	 * set the x and y values of the cell on which user has clicked.
	 */
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub		
		int x,y = 0;	
		int userVal = 0;
		Cell c = (Cell)e.getSource();				
		x = c.getFieldX();
		y = c.getFieldY();		
		game.lastEditedX = x;
		game.lastEditedY = y;	
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub					
		Cell c = (Cell)e.getSource();						
		c.setBackground(Color.WHITE);
		
	}


	
}
