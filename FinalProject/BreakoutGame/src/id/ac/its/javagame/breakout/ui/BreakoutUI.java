package id.ac.its.breaokutgame.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BreakoutUI {

	protected Shell shlBreakoutGame;

	public static void main(String[] args) {
		try {
			BreakoutUI window = new BreakoutUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBreakoutGame.open();
		shlBreakoutGame.layout();
		while (!shlBreakoutGame.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlBreakoutGame = new Shell();
		shlBreakoutGame.setSize(450, 300);
		shlBreakoutGame.setText("Breakout Game");
		
		Button btnPlay = new Button(shlBreakoutGame, SWT.NONE);
		btnPlay.setTouchEnabled(true);
		
		btnPlay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					ChooseLevel window = new ChooseLevel();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnPlay.setBounds(153, 25, 106, 37);
		btnPlay.setText("Play");
		
		
		Button btnHighScores = new Button(shlBreakoutGame, SWT.NONE);
		btnHighScores.setTouchEnabled(true);

		btnHighScores.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		btnHighScores.setText("High Scores");
		btnHighScores.setBounds(153, 82, 106, 37);
		
		
		Button btnOptions = new Button(shlBreakoutGame, SWT.NONE);
		btnOptions.setTouchEnabled(true);
		
		btnOptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		btnOptions.setText("Options");
		btnOptions.setBounds(153, 141, 106, 37);
		
		
		Button btnQuit = new Button(shlBreakoutGame, SWT.NONE);
		btnQuit.setTouchEnabled(true);
		
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		
		btnQuit.setText("Quit");
		btnQuit.setBounds(153, 203, 106, 37);

	}
}
