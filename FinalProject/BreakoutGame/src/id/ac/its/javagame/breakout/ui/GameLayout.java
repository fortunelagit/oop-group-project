package id.ac.its.breakoutgame.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import id.ac.its.breakoutgame.program.Breakout;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLayout {
	JFrame frame;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameLayout();
			}
		});
	}
	
	public GameLayout() {
		frame = new JFrame("Breakout Game");
		frame.setSize(450, 300);
		
		JPanel panelCont = new JPanel();
		JPanel panelFirst = new JPanel();
		JPanel panelSecond = new JPanel();
		
		JButton buttonPlay = new JButton("Play");
		JButton buttonHScore = new JButton("High Score");
		JButton buttonOptions = new JButton("Options");
		JButton buttonExit = new JButton("Exit");
		
		JButton buttonMMenu = new JButton("Main Menu");
		JButton buttonEasy = new JButton("Easy");
		JButton buttonMedium = new JButton("Medium");
		JButton buttonHard = new JButton("Hard");
		
		panelFirst.add(buttonPlay);
		panelFirst.add(buttonHScore);
		panelFirst.add(buttonOptions);
		panelFirst.add(buttonExit);
		
		panelFirst.setSize(450, 300);
		
		panelSecond.add(buttonMMenu);
		panelSecond.add(buttonEasy);
		panelSecond.add(buttonMedium);
		panelSecond.add(buttonHard);
		
		panelSecond.setSize(450, 300);
		
		panelFirst.setBackground(Color.DARK_GRAY);
		panelSecond.setBackground(Color.GRAY);

		CardLayout cl = new CardLayout();
		panelCont.setLayout(cl);
		
		panelCont.add(panelFirst, "1");
		panelCont.add(panelSecond, "2");
		cl.show(panelCont, "1");
		
		buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
			}
		});
		
		buttonHScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//nazhwa disini tampilin high scorenya
			}
		});
		
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		buttonMMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "1");
			}
		});
		
		buttonEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(30, 12);
                	game.setVisible(true);
    				});
				frame.setVisible(false);
			}
		});
		
		buttonMedium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(42, 9);
                	game.setVisible(true);
    				});
				frame.setVisible(false);
			}
		});
		
		buttonHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(60, 7);
                	game.setVisible(true);
    				});
				frame.setVisible(false);
			}
		});
		
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

	

}
