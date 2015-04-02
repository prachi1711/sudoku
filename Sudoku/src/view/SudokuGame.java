package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import controller.ButtonController;
import controller.ControlPanelController;
import controller.SudokuController;
import model.Game;

/**
 * Main class of program.
 * Creates Control Panel,Sudoku Panel and Button Panel
 */
public class SudokuGame extends JFrame {
    public SudokuGame() {
        super("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Game game = new Game();   
        //Creation of Control Panel for Game Selection
        ControlPanelController cntrlPanelController = new ControlPanelController(game);
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setController(cntrlPanelController);
        add(controlPanel, BorderLayout.NORTH);
        
        //Creation of Sudoku Panel for Grid Creation
        SudokuPanel sudokuPanel = new SudokuPanel(game);
        SudokuController sudokuController = new SudokuController(sudokuPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setController(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);        
        
        //Creation of Button Panel for Game Validation
        ButtonController buttonController = new ButtonController(game);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setController(buttonController);
        add(buttonPanel, BorderLayout.SOUTH);
        
        game.addObserver(sudokuPanel);        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Main entry point of program.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new SudokuGame();
    }

}
