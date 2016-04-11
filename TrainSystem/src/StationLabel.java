import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class StationLabel {

	private int x;
	private int y;
	private String text = null;
	private Rectangle bounds;
	private int station;
	
	public StationLabel (String text, int x, int y, int s, Graphics g) {
		this.text = text;
		this.x = x;
		this.y = y;
		station = s;
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		bounds = metrics.getStringBounds(text, g).getBounds();
		bounds.setLocation(new Point(x, y-bounds.height/2-6));
	}
	
	public void setLocation (Point p) { 
		x = p.x-bounds.width/2;
		y = p.y+bounds.height/2;
		bounds.setLocation(x, y-bounds.height/2-6);
	}


	public String getText () { return text; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getStation() { return station; } 
	public Rectangle getBounds () { return bounds; }
	public boolean contains (Point p) { return bounds.contains(p); }
}
