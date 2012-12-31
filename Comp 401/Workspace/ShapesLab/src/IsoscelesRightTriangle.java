import java.awt.Graphics;

public class IsoscelesRightTriangle implements IShape {
	//data members
	int width; int height;
	int x; int y;
	Graphics canvas;
	
	public IsoscelesRightTriangle(int theX, int theY, int theWidth, int theHeight, Graphics theCanvas){
		x = theX;
		y = theY;
		height = theHeight;
		width = theWidth;
		canvas = theCanvas;
	}
	
	public int area(){
		return (int) (.5 * width * height);
	}
	
	public void draw(){
		canvas.drawLine(x, y, x, y-height);
		canvas.drawLine(x, y, x+width, y-height);
		canvas.drawLine(x, y-height, x+width, y-height);
	}
	
	public void erase(){
		canvas.clearRect(x,y,x+width,y-height);
	}
		
	
}
