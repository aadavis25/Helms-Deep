import java.awt.Graphics;

public class Rectangle implements IShape {
	//Data members
	int x; int y;
	int width; int height;
	Graphics canvas;
	
	public Rectangle (int theX, int theY, int theWidth, int theHeight, Graphics theCanvas){
		super();
		width = theWidth;
		height = theHeight;
		canvas = theCanvas;
		x = theX;
		y = theY;
		
	}
	
	public int area(){
		return (height * width);
	}
	
	public void draw(){
		canvas.drawRect(x, y, width, height);
	}
	
	public void erase(){
		canvas.clearRect(x, y, x+width, y-height);
	}
}
