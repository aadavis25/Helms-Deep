import java.awt.Color;
import java.awt.Graphics;

public class FilledSquare extends Square {
	
	
	Color fillColor;
	
	public FilledSquare(int theX, int theY, int theSide, Color theColor, Graphics theCanvas ) {
		super(theX, theY, theSide, theCanvas);
		fillColor = theColor;
	}
	
	public void draw() {
		Color before = canvas.getColor();
		canvas.setColor(fillColor);
		canvas.fillRect(x, y, side, side);
		canvas.setColor(before);
	}
	
}
