package id.ac.its.javagame.breakout;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Paddle extends Sprite  {

    private int dx;

    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }
    
    private void loadImage() {
        
        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
    }    

    void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        if (x >= BoardState.WIDTH - imageWidth) {

            x = BoardState.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    public void mouseMoved(MouseEvent e) {
    	x = e.getX();
    	y = BoardState.INIT_PADDLE_Y;
    	if (x > BoardState.WIDTH - imageWidth) {
            x = BoardState.WIDTH - imageWidth;
        }
    }
    
    private void resetState() {

        x = BoardState.INIT_PADDLE_X;
        y = BoardState.INIT_PADDLE_Y;
    }
}
