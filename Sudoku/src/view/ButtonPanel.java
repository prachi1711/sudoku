package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

import controller.ButtonController;


/**
 * This class creates the Button Panel with undo,redo,hint and Validate button to verify the Sudoku
 * solution entered by user.
 * @author Prachi shah
 */
public class ButtonPanel extends JPanel {
    JButton btnValidate;   // Used for validation.
    JButton btnRedo;   // Used for Redo .    
    JButton btnUndo;   // Used for Undo.    
    JButton btnHelp;   // Used for help.    
        
    public ButtonPanel() {
        super(new BorderLayout());
        
        
        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));  
        add(pnlOptions, BorderLayout.CENTER);

        btnUndo = new JButton("Undo");
        btnUndo.setFocusable(false);
        btnUndo.setAlignmentX(CENTER_ALIGNMENT);
        btnUndo.setAlignmentY(CENTER_ALIGNMENT);
        pnlOptions.add(btnUndo);
        
        btnRedo = new JButton("Redo");
        btnRedo.setFocusable(false);   
        btnRedo.setAlignmentX(CENTER_ALIGNMENT);
        btnRedo.setAlignmentY(CENTER_ALIGNMENT);
        pnlOptions.add(btnRedo);
        
        btnHelp = new JButton("Hint");
        btnHelp.setFocusable(false);  
        btnHelp.setAlignmentX(CENTER_ALIGNMENT);
        btnHelp.setAlignmentY(CENTER_ALIGNMENT);
        pnlOptions.add(btnHelp);
        
        btnValidate = new JButton("Validate Game");
        btnValidate.setFocusable(false);
        btnValidate.setAlignmentX(CENTER_ALIGNMENT);
        btnValidate.setAlignmentY(CENTER_ALIGNMENT);
        pnlOptions.add(btnValidate);                
    } 

    /**
     * Adds controller to all components.
     *
     * @param buttonController  Controller which controls all user actions in Button Panel.
     */
    public void setController(ButtonController buttonController) {        	
    	btnValidate.addActionListener(buttonController);
    	btnUndo.addActionListener(buttonController);
    	btnRedo.addActionListener(buttonController);
    	btnHelp.addActionListener(buttonController);
       }

}
