package id.ac.its.javagame.breakout.program;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Breakout extends JFrame {

    public Breakout(int n_of_bricks, int period) {
        
        initUI(n_of_bricks, period);
    }
    
    private void initUI(int n_of_bricks, int period) {

        add(new Board(n_of_bricks, period));
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }
    
}
