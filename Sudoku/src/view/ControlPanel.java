package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import controller.ControlPanelController;

/**
 * This class draws the control panel which has the game selection options
 *
 */
public class ControlPanel extends JPanel {
    JButton btnSelect;   // Used buttons.
    JComboBox cmbLevel;               // Used check box.    
    JTextField txtPuzzleNo;
    JLabel cmbLabel,textLabel;
    
    /**
     * It creates combo-box, text-box and game selection button
     *
     */
       
    public ControlPanel() {
        super(new BorderLayout());
                
        cmbLabel = new JLabel();   
        textLabel = new JLabel();
        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Select Game "));
        pnlAlign.add(pnlOptions);
        
        cmbLabel.setText("Level "); 
        String levels[] = {"Easy","Medium","Hard","Evil"};        
        cmbLevel = new JComboBox(levels);        
        cmbLevel.setBackground(Color.gray);
        cmbLevel.setForeground(Color.red);
        pnlOptions.add(cmbLabel);
        pnlOptions.add(cmbLevel);

        JPanel pnlNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlNumbers);
        
        txtPuzzleNo = new JTextField(10);
        textLabel.setText("Number ");
        pnlNumbers.add(textLabel);
        pnlNumbers.add(txtPuzzleNo);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlButton);
        btnSelect = new JButton("Go to this puzzle");
        btnSelect.setFocusable(false);
        btnSelect.setHorizontalAlignment(JTextField.CENTER);
        pnlButton.add(btnSelect);
                 
    }

 

    /**
     * Adds controller to all components.
     *
     * @param buttonController  Controller which controls all user actions of Control Panel.
     */
    public void setController(ControlPanelController cntrlPnlController) {    
    	cmbLevel.addActionListener(cntrlPnlController);
    	txtPuzzleNo.addKeyListener(cntrlPnlController);
    	btnSelect.addActionListener(cntrlPnlController);
       }

}
