package id.ac.its.breaokutgame.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Button;

import java.awt.EventQueue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import id.ac.its.breakoutgame.program.Breakout;

public class ChooseLevel {

	protected Shell shell;


	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button btnEasy = new Button(shell, SWT.NONE);
		btnEasy.setTouchEnabled(true);
		
		btnEasy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(24, 12);
                	game.setVisible(true);
    				});
			}
		});
		
		btnEasy.setBounds(165, 34, 105, 32);
		btnEasy.setText("Easy");
		
		Button btnMedium = new Button(shell, SWT.NONE);
		btnMedium.setTouchEnabled(true);
		
		btnMedium.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(36, 9);
                	game.setVisible(true);
    				});
			}
		});
		
		btnMedium.setText("Medium");
		btnMedium.setBounds(165, 96, 105, 32);
		
		Button btnHard = new Button(shell, SWT.NONE);
		btnHard.setTouchEnabled(true);
		
		btnHard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EventQueue.invokeLater(() -> {
                	var game = new Breakout(48, 7);
                	game.setVisible(true);
    				});
			}
		});
		
		btnHard.setText("Hard");
		btnHard.setBounds(165, 158, 105, 32);

	}

}