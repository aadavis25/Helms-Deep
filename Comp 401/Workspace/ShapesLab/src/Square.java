import java.awt.Graphics;

public class Square implements IShape {
	//Data members
	int x; int y;
	int side;
	Graphics canvas;
	
	public Square (int theX, int theY, int theSide, Graphics theCanvas){
		super();
		side = theSide;
		canvas = theCanvas;
		x = theX;
		y = theY;
		
	}
	
	public int area(){
		return (side * side);
	}
	
	public void draw(){
		canvas.drawRect(x, y, side, side);
	}
	
	public void erase(){
		canvas.clearRect(x, y, x+side, y-side);
	}
}
