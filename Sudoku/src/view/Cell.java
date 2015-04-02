package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


/**
 * This class creates a cell on the SudokuPanel.
 */
public class Cell extends JTextField {
	 
	public Cell() {}  
	protected Document createDefaultModel() {  
	        return new CellDocument();  
	    }  
    private int x;      // X position of cell in Sudoku Panel.
    private int y;      // Y position of cell in Sudoku Panel.

    /**
     * Constructs the text fields and sets x and y positions in game.
     *
     * @param x     X position in game.
     * @param y     Y position in game.
     */
    public Cell(int x, int y) {
        super("", CENTER);        
        this.x = x;
        this.y = y;                
        setColumns(1);
        setPreferredSize(new Dimension(40, 40));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 18));        
        setOpaque(true);
    }

    /**
     * Sets number and foreground color according to userInput.
     *
     * @param number        Number to be set.
     * @param userInput     Boolean indicating number is user input or not.
     */
    public void setNumber(int number, boolean userInput) {
    	setForeground(userInput ? Color.BLUE : Color.BLACK);
        setText(number > 0 ? number + "" : "");        
    }

    /**
     * Returns x position in game.
     *
     * @return  X position in game.
     */
    public int getFieldX() {
        return x;
    }

    /**
     * Return y position in game.
     *
     * @return  Y position in game.
     */
    public int getFieldY() {
        return y;
    }
    
    /**
     * This class creates a document on the SudokuPanel.
     * It restricts the user from entering values other than numbers and
     * from entering more than one number
     */
    static class CellDocument extends PlainDocument 
    {  
        public CellDocument() {}  
        
        public void insertString(int offs, String str, AttributeSet attr)  
                                              throws BadLocationException 
         {        	        	       
            char[] source = str.toCharArray();             
            for (int i = 0; i < source.length; i++) 
            {              	
                if (! Character.isDigit(source[i])) 
                {  
                    Toolkit.getDefaultToolkit().beep();  
                    return;  
                }  
             } 
            if ((getLength() + str.length()) <= 1) {
            	 super.insertString(offs, str, attr);
    	    }
             
           }  
     }  
}
