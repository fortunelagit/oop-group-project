package id.ac.its.collideapp;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class SpaceShip extends Sprite {

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

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void mouseDragged(MouseEvent e) {
	x = e.getX();
	y = e.getY();
	    
	//Handling apabila mouse keluar frame. Angka didapat dari ukuran lebar frame dikurangi ukuran pesawat
    	if(x > 370)
    	{
    		x = 370;
    	}
    	
    	//Handling apabila mouse keluar frame. Angka didapat dari ukuran tinggi frame dikurangi ukuran pesawat
    	if(y > 270)
    	{
    		y = 270;
    	}
    }
	    
    public void mouseReleased(MouseEvent e) {

        dx = 0;
        dy = 0;
    }
    	
    	
    	
