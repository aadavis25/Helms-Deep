

import java.awt.Graphics;

public class Circle implements IShape {
	
	public double PI = 3.14159;
	
	// Data members
	int centerX, centerY;
	int radius;
	Graphics canvas;
	
	public Circle(int theX, int theY, int theRadius, Graphics theCanvas ) {
		super();
		centerX = theX;
		centerY = theY;
		radius = theRadius;
		canvas = theCanvas;
	}
	
	public int area() {
		return (int)(PI * radius * radius);
	}
	
	public void draw() {
		canvas.drawOval(centerX, centerY, radius, radius);
	}
	
	public void erase() {
		canvas.clearRect(centerX-radius, centerY-radius, 2*radius+1, 2*radius+1);
	}
}
