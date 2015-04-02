package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.SudokuPanel;

import model.Game;

/**
 * This class controls all user actions like game level and no selection of Control Panel.
 *
 */
public class ControlPanelController implements ActionListener,KeyListener {
    private Game game;
    boolean textFlag = false;

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public ControlPanelController(Game game) {
        this.game = game;
    }

    /**
     * Performs action after user pressed button.
     * Sets the game level and loads new Game based on user selection
     * @param e ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {     
    	SudokuPanel sudokuPanel = new SudokuPanel(game);    	
    	if(e.getActionCommand().equals("comboBoxChanged"))
    	{
    		JComboBox jCombo = (JComboBox) e.getSource();
    	    String level = (String) jCombo.getSelectedItem();    		
    		game.setGameLevel(level);
    	}    	
    	
        if (e.getActionCommand().equalsIgnoreCase(GO_TO_PUZZLE))
        {
        	int puzzleNo = game.getUserEnteredVal();        	
        	if(puzzleNo > 0 && textFlag)
        	{        			
	        	if(game.getGameLevel()!=null && !game.getGameLevel().equalsIgnoreCase(""))
	        	{        		
	        		game.newGame();
	        	}	        	
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "Please enter game number", "Error", JOptionPane.WARNING_MESSAGE );
        	}
        }            
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* Sets the Game No entered by user
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub				
		JTextField text = (JTextField)e.getSource();		
		if(text.getText()!=null && !text.getText().equalsIgnoreCase(""))
		{
		   game.setUserEnteredVal(Integer.parseInt(text.getText()));
		   textFlag = true;
		}
		else
			textFlag = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
		
	}

	final static String GO_TO_PUZZLE = "Go to this puzzle";
	
}