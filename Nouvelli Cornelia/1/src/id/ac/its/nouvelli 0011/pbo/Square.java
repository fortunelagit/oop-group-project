package id.ac.its.nouvelli0011.pbo;

import javax.swing.JOptionPane;

public class Square {

	public static void main(String [] args) {
		
		// obtain user input from JOptionPane input dialog
			String side1 = 
			JOptionPane.showInputDialog("Enter side");
			
			//convert String inputs t int value for use in a calculation
			double side = Double.parseDouble(side1);
			
			double area = side * width;
			double circumference = 4 * side;
			
			//display result in a JOpionPane message dialog
			JOptionPane.showMessageDialog(null, "The area is " + area, 
					"Area of a Rectangle", JOptionPane.PLAIN_MESSAGE);
			
			JOptionPane.showMessageDialog(null,"the circumference is" + circumference, 
					"Circumference of a Rectangle", JOptionPane.PLAIN_MESSAGE);
	}	

}
