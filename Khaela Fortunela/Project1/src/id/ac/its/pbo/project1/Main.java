package id.ac.its.pbo.project1;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		//String[] options = {"Circle", "Triangle", "Rectangle", "Square"};
		//int choice = JOptionPane.showOptionDialog(null, "Choose any shape to calculate.",
	        //        "Click a button",
	        //        JOptionPane.DEFAULT_OPTION, 
	        //        JOptionPane.INFORMATION_MESSAGE, null, 
	        //        options, options[0]);
		//
		//switch(choice) {
		//	case 0:	Circle.Application();
		//			break;
		//}
		
		JOptionPane.showMessageDialog(null, "Welcome to Circle Calculator!!");
		int radius = Integer.parseInt(JOptionPane.showInputDialog("Enter radius of circle:"));
		Circle c = new Circle(radius);
		String print = c.toString();
		JOptionPane.showMessageDialog(null, print);
	}

}
