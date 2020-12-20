package id.ac.its.collideapp;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


public class SpaceShip extends Sprite implements MouseListener, MouseMotionListener{

    private int dx;
    private int dy;
    private List<Missile> missiles;

    public SpaceShip(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("src/resources/spaceship.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    
    	@Override
    	public void mouseClicked(MouseEvent event) {
    		fire();
    	}
    	
    	@Override
    	public void mousePressed(MouseEvent event) {
    		
    	}
    	
    	@Override
    	public void mouseDragged(MouseEvent event) {
	    	dx = event.getX();
	    	dy = event.getY();
	    }
    	
    	@Override
    	public void mouseMoved(MouseEvent event) {
    		x = 0;
	    	y = 0;
    	}

		@Override
		public void mouseReleased(MouseEvent event) {
			dx = 0;
	    	dy = 0;
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			dx = 0;
	    	dy = 0;
		}

		@Override
		public void mouseExited(MouseEvent event) {
			dx = 0;
	    	dy = 0;
		}
    
}