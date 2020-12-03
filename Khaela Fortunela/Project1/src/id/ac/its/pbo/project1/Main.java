package id.ac.its.pbo.project1;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Welcome to Circle Calculator!!");
		int radius = Integer.parseInt(JOptionPane.showInputDialog("Enter radius of circle:"));
		Circle c = new Circle(radius);
		String print = c.toString();
		JOptionPane.showMessageDialog(null, print);
	}

}
