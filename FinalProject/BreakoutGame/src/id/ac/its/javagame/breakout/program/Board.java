package id.ac.its.javagame.breakout.program;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sql.CommonDataSource;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean inGame = true;
    private int n_of_bricks;
    private int period;
	private int bricksDestroyed = 0;
	private int difficulty;
	
	private final String[] difficulties = {"Easy", "Medium", "Hard"};

	private final String HIGH_SCORE_EASY = "High Score " + difficulties[0] + ": " ;
	private final String HIGH_SCORE_MEDIUM = "High Score " + difficulties[1] + ": ";
	private final String HIGH_SCORE_HARD = "High Score " + difficulties[2] + ": ";
	private final String GAMES_PLAYED = "Games Played: ";
	private final String BRICKS_DESTROYED = "Bricks Destroyed: ";

	private final int TOTAL_GAMES_PLAYED_LOC = 3;
	private final int TOTAL_BRICKS_DESTROYED = 4;
	
	private final String dataID[] = {HIGH_SCORE_EASY, HIGH_SCORE_MEDIUM, 
			HIGH_SCORE_HARD, GAMES_PLAYED, BRICKS_DESTROYED};

	private final int[] data = new int[dataID.length];

	
	int score = 0; 
	Clip gameMusicSound;
	
	public Board(int n_of_bricks, int period, int difficulty) {
	loadData();
	this.n_of_bricks = n_of_bricks;
	this.period = period;
	this.difficulty = difficulty;
        initBoard();
        //initSounds();
        try {
        	//Clip gameMusicSound;
            URL url = this.getClass().getClassLoader().getResource("sounds/gameMusic.wav");
            AudioInputStream audioIn;
        	audioIn = AudioSystem.getAudioInputStream(url);
			gameMusicSound = AudioSystem.getClip();
			gameMusicSound.open(audioIn);
			gameMusicSound.start();
	        gameMusicSound.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        addMouseMotionListener(new MAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(BoardState.WIDTH, BoardState.HEIGHT));

        gameInit();
    }

    private void gameInit() {

        bricks = new Brick[n_of_bricks];

        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < (n_of_bricks/6); i++) {

            for (int j = 0; j < 6; j++) {

                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }
        //playMusic();
        timer = new Timer(period, new GameCycle());
        data[TOTAL_GAMES_PLAYED_LOC]++;
        timer.start();
        saveData();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.drawString("Score: "+ score, 10, 15);
        if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < n_of_bricks; i++) {

            if (!bricks[i].isDestroyed()) {

                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
    	
    	g2d.setColor(Color.red);
        Font font = new Font("Monospaced", Font.PLAIN, BoardState.WIDTH / 10);
        FontRenderContext frc = g2d.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, message);
        g2d.drawGlyphVector(gv,
                BoardState.WIDTH / 2 - ((int) gv.getVisualBounds().getWidth() / 2),
                BoardState.HEIGHT * 7 / 20 - ((int) gv.getVisualBounds().getHeight() / 2));
        
    	 g2d.setColor(Color.green);
         font = new Font("Verdana", Font.PLAIN, BoardState.WIDTH / 25);
         frc = g2d.getFontRenderContext();
         gv = font.createGlyphVector(frc, "Difficulty: " + difficulties[difficulty]);
         g2d.drawGlyphVector(gv,
        		 BoardState.WIDTH / 2 - ((int) gv.getVisualBounds().getWidth() / 2),
        		 BoardState.HEIGHT * 9 / 20 - ((int) gv.getVisualBounds().getHeight() / 2));
         
         font = new Font("Verdana", Font.PLAIN, BoardState.WIDTH / 25);
         frc = g2d.getFontRenderContext();
         gv = font.createGlyphVector(frc, "Score: " + score);
         g2d.drawGlyphVector(gv,
        		 BoardState.WIDTH / 2 - ((int) gv.getVisualBounds().getWidth() / 2),
        		 BoardState.HEIGHT * 11 / 20 - ((int) gv.getVisualBounds().getHeight() / 2)); 
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            paddle.keyPressed(e);
        }
    }
    
    private class MAdapter extends MouseAdapter {
    	@Override
    	public void mouseMoved(MouseEvent e) {
    		paddle.mouseMoved(e);
    	}
    	
    }

    
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
    	
    	
        inGame = false;
        timer.stop();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > BoardState.BOTTOM_EDGE) {
        	
        	
        	gameMusicSound.stop();
        	try {
            	Clip gameOverSound;
                URL url = this.getClass().getClassLoader().getResource("sounds/gameOver.wav");
                AudioInputStream audioIn;
            	audioIn = AudioSystem.getAudioInputStream(url);
            	gameOverSound = AudioSystem.getClip();
            	gameOverSound.open(audioIn);
            	gameOverSound.start();
            	//gameOverSound.loop(Clip.LOOP_CONTINUOUSLY);

    		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            saveData();
            //Foobar.stopMusic();
            //playGameOverSound();
            stopGame();
            
        }

        for (int i = 0, j = 0; i < n_of_bricks; i++) {

            if (bricks[i].isDestroyed()) {

                j++;
                

                //bricksDestroyed++;
                //playBrickCollisionSound();
                
            }

            if (j == n_of_bricks) {

                message = "Victory";
                gameMusicSound.stop();
                try {
                	Clip gameWinSound;
                    URL url = this.getClass().getClassLoader().getResource("sounds/gameWin.wav");
                    AudioInputStream audioIn;
                	audioIn = AudioSystem.getAudioInputStream(url);
        			gameWinSound = AudioSystem.getClip();
        			gameWinSound.open(audioIn);
        			gameWinSound.start();
        	        //gameWinSound.loop(Clip.LOOP_CONTINUOUSLY);

        		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                
                //data[difficulty] = Math.max(bricksDestroyed, data[difficulty]);
                saveData();
                //playGameWinSound();
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {
        	
        	//playPaddleCollisionSound();
            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
            
            try {
            	Clip paddleCollisionSound;
                URL url = this.getClass().getClassLoader().getResource("sounds/PaddleCollision.wav");
                AudioInputStream audioIn;
            	audioIn = AudioSystem.getAudioInputStream(url);
            	paddleCollisionSound = AudioSystem.getClip();
            	paddleCollisionSound.open(audioIn);
            	paddleCollisionSound.setMicrosecondPosition(0);
            	paddleCollisionSound.start();

    		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }

        for (int i = 0; i < n_of_bricks; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {
            	
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                    score += 5 * (difficulty+1);
                    ++bricksDestroyed;
                    try {
                    	Clip brickCollisionSound;
                        URL url = this.getClass().getClassLoader().getResource("sounds/BrickCollision.wav");
                        AudioInputStream audioIn;
                    	audioIn = AudioSystem.getAudioInputStream(url);
                    	brickCollisionSound = AudioSystem.getClip();
                    	brickCollisionSound.open(audioIn);
                    	brickCollisionSound.setMicrosecondPosition(0);
                    	brickCollisionSound.start();

            		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
            }
        }
    }
    
    
    
    public void loadData() {
        Path path = Paths.get("./BreakoutData.txt");
        String line;
        int dataIndex = 0;
        try (InputStream in = Files.newInputStream(path)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                data[dataIndex] = Integer.parseInt(line.replaceAll(dataID[dataIndex], ""));
                dataIndex++;
            }
            reader.close();
        } catch (Exception ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveData() {
    	data[difficulty] = Math.max(score, data[difficulty]);
        data[TOTAL_BRICKS_DESTROYED] += bricksDestroyed;
        for (int datum : data) {
            System.out.println(datum);
        }
        Path path = Paths.get("./BreakoutData.txt");
        try (OutputStream out = Files.newOutputStream(path)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (int i = 0; i < data.length; i++) {
                writer.write(dataID[i] + Integer.toString(data[i]));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
