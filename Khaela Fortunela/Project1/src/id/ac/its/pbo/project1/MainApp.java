package id.ac.its.pbo.project1;

import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String[] options = {"Circle", "Triangle", "Rectangle", "Square"};
		int choice = JOptionPane.showOptionDialog(null, "Choose any shape to calculate.",
	                "Click a button",
	                JOptionPane.DEFAULT_OPTION, 
	                JOptionPane.INFORMATION_MESSAGE, null, 
	                options, options[0]);
		
		switch(choice) {
			case 0:	Circle.Application();
					break;
			
			
		}

	}

}
