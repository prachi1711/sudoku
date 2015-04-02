package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import view.Cell;

import model.Game;
/**
 * This class controls all user actions from Button Panel i.e Validate Button Action.
 *
 * @author Prachi Shah
 */
public class ButtonController implements ActionListener {
    private Game game;

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public ButtonController(Game game) {
        this.game = game;
    }

    /**
     * Performs action after user pressed button.
     * It first checks if the user has entered all the values 
     * and then validates the solution
     * @param e ActionEvent.
     */
    public void actionPerformed(ActionEvent e) 
    {    	    	      
    	if (e.getActionCommand().equalsIgnoreCase("Validate Game"))
    	{
              if(game.checkAllValuesEntered(game))
              {            	  
            	  boolean status = true;
            	  for (int x = 0; x < 9; x++) 
              	  {
                      for (int y = 0; y < 9; y++) 
                      {
		            	  if (!game.isValid(x, y,game))
		            	  {
		            		  status = false;
		            		  break;
		            	  }		            	  
                      }
              	  }
            	  if(status)
            	  {
            		  JOptionPane.showMessageDialog(null, "Congratulations!You have solved the game!", "Message", JOptionPane.PLAIN_MESSAGE );  
            	  }
            	  else
            	  {
            		  JOptionPane.showMessageDialog(null, "Solution is not correct.Please try again.", "Message", JOptionPane.PLAIN_MESSAGE );
            	  }
              }
              else
              {
            	  JOptionPane.showMessageDialog(null, "Please enter all the numbers", "Error", JOptionPane.WARNING_MESSAGE );
              } 
    	}
    	else if(e.getActionCommand().equalsIgnoreCase("Undo"))
    	{
    		game.undo(); 		
    	}
    	else if(e.getActionCommand().equalsIgnoreCase("Redo"))
    	{
    		game.redo();
    	}
    	else if(e.getActionCommand().equalsIgnoreCase("Hint"))
    	{
    	    game.provideHint();
    	}
      }
    
    }

	