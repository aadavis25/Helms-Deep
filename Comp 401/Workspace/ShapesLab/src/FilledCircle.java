
import java.awt.Color;
import java.awt.Graphics;

public class FilledCircle extends Circle {
	
	// Data members
	Color fillColor;
	
	public FilledCircle(int theX, int theY, int theRadius, Color theColor, Graphics theCanvas ) {
		super(theX, theY, theRadius, theCanvas);
		fillColor = theColor;
	}
	
	public void draw() {
		Color before = canvas.getColor();
		canvas.setColor(fillColor);
		canvas.fillOval(centerX, centerY, radius, radius);
		canvas.setColor(before);
	}
}
