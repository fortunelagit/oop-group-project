package id.ac.its.pbo.project1;

import javax.swing.JOptionPane;

public class Circle {
	private double radius;
	private double PI = 3.14;
	
	public Circle() {
		this.radius = 0.0;
	}
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public double getArea() {
		return PI * Math.pow(getRadius(),2);
	}
	
	public double getCircumference() {
		return 2 * PI * getRadius();
	}

	@Override
	public String toString() {
		return String.format("%s%n%s: %s%n%s: %s%n%s: %s", 
				"Circle", 
				"Radius", getRadius(),
				"Area", getArea(),
				"Circumference", getCircumference());
	}
	
	public static void Application() {
		int r = Integer.parseInt(JOptionPane.showInputDialog("Enter radius of circle:"));
		Circle c = new Circle(r);
		String print = c.toString();
		JOptionPane.showMessageDialog(null, print);
	}
	
}
