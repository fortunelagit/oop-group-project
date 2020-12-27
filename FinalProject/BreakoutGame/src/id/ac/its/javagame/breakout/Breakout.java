package id.ac.its.javagame.breakout;

import javax.swing.JFrame;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Breakout extends JFrame {

    public Breakout() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        
        String[] options = {"Play", "High Scores", "Quit"};
    	int choice = JOptionPane.showOptionDialog(null, "Smash everything in your sight!", 
    				"Breakout Game",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, null, 
                    options, options[0]);
    	
    	switch(choice) {
    		case 0:	EventQueue.invokeLater(() -> {
                	var game = new Breakout();
                	game.setVisible(true);
    				});
    				break;
    				//1 for high score
    		case 1: 
    	}
        
    }
}
