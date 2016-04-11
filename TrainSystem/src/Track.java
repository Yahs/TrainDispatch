import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class Track implements Comparable <Track>
{
	private Station m_a;
	private Station m_b;
	private double length = 0;
	
	//vertices that make up the path
	private ArrayList<Point> vertices = new ArrayList<Point>();
	private ArrayList<Train> trainlocks = new ArrayList<Train>();
	private double timeLocked = 0;
	public Color color = null;
	
	private Track (Station a, Station b)
	{
		m_a = a;
		m_b = b;
		m_a.setAdj(this);
		m_b.setAdj(this);
	}
	
	public Track (Station a, Station b, ArrayList<Point> points)
	{
		this (a, b);
		for (Point p: points)
			vertices.add(p);
		calcLength();
	}
	
	public void setLength (double l) { 
		length = l; 
	}
	
	public void setVertice (int index, Point p) { 
		vertices.set(index, p); 
		calcLength(); 
	}
	
	public void addVertice (Point p) { 
		vertices.add(p); 
	}
	
	public void addLock (Train lock) {
		trainlocks.add(lock);		
		for (Train train: trainlocks) {
			if (train.getTimeToUnlock(this) < lock.getTimeToUnlock(this))
				timeLocked = lock.getTimeToUnlock(this);
		}
	}
	
	public void removeLock (Train lock) {
		trainlocks.remove(lock);
		timeLocked = 0;
	}
	public void removeVertex (int index) { 
		vertices.remove(index); 
	}
	
	//Map canvas calls if the Location of the Station moves;
	public void updateLocation () {
		vertices.set(0, (Point)m_a);
		vertices.set(vertices.size() - 1, (Point)m_b);
		calcLength();
	}
	
	public void calcLength() {
		for (int i =0; i < vertices.size() - 1; i++)
			length += vertices.get(i).distance(vertices.get(i+1));
	}
	
	/*
	 * Calculates the distance to the point on the track given its
	 * starting location (station)
	 */
	public double getDistanceOnTrack (Station station, Point p) {
		double totalDistance = 0, min = Integer.MAX_VALUE;
		int leftIndex = 0; //left vertex to the point of interest on the track
		for (int i = 0; i < vertices.size()-1; i++) {
			Line2D line = new Line2D.Double(vertices.get(i), vertices.get(i+1));
			if (line.ptLineDist(p) < min) {
				leftIndex = i;
				min = line.ptLineDist(p);
			}
		}
		
		if (m_a == station) {
			for (int i = 0;  i < leftIndex; i++) {
				totalDistance += vertices.get(i).distance(vertices.get(i+1));
			}
			totalDistance += vertices.get(leftIndex).distance(p);
		} else {
			for (int i = vertices.size() - 1; i > leftIndex+1; i--) {
				totalDistance += vertices.get(i).distance(vertices.get(i-1));
			}
			totalDistance += vertices.get(leftIndex+1).distance(p);
		}
		
		return totalDistance;
	}
	
	public Point getPos (Station station, double distance) {
		if (station == m_a) {
			for (int i = 0; i < vertices.size() - 1; i++) {
				Point a =  vertices.get(i);
				Point b = vertices.get(i + 1);
				double lineLength = a.distance(b);
				if (distance < lineLength + 10) {
					double newX = (a.x + (distance / lineLength)*(b.x - a.x));
					double newY = (a.y + (distance / lineLength)*(b.y - a.y));
					return new Point((int)newX, (int)newY);
				} else {
					distance -= lineLength;
				}
			}
		} else {
			for (int i = vertices.size()-1; i > 0; i--) {
				Point a =  vertices.get(i);
				Point b = vertices.get(i - 1);
				double lineLength = a.distance(b);
				if (distance < lineLength + 10) {
					double newX = (a.x + (distance / lineLength)*(b.x - a.x));
					double newY = (a.y + (distance / lineLength)*(b.y - a.y));
					return new Point((int)newX, (int)newY);
				} else {
					distance -= lineLength;
				}
			}
		}
		return null;
	}
	
	
	//
	public int getNearestVertice (Point p, int errorMargin) {
		int verticeIndex = -1;
		double distance = errorMargin;
		
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).distance(p) < distance) {
				verticeIndex = i;
				distance = vertices.get(i).distance(p);
			}
		}
		
		return verticeIndex;
	}
	
	//public double getTimeLocked () { return timeLocked; }
	

	
	/***********************************************************************/
	public ArrayList<Point> getVertices () { return vertices; }
	public ArrayList<Train> getLocks() { return trainlocks; };
	public Point getVertex (int i) { return vertices.get(i); } 
	public double getLength () { return length; }
	public Station getNeighbor (Station s) { return (s == m_a) ? m_b : m_a; }
	public Station to () { return m_b; }
	public Station from () { return m_a; }
	
	public boolean isBlocked (Train train) {
		if (trainlocks.isEmpty() || trainlocks.contains(train))
			return false;
		
		return true; 
	}
	
	public boolean isLocked () { 
		return !trainlocks.isEmpty(); 
	}

	
	@Override
	public int compareTo(Track e) {
		if (length < e.getLength())
			return -1;
		else if (length > e.getLength())
			return 1;
		return 0;
	}

	@Override
	public String toString () {
		String output = "From Station: " + m_a.getName() +
				        "\nTo Station: " + m_b.getName() +
				        "\nTotal Length: " + length +
				        "\nLocked for: " + trainlocks;	
		return output;
	}

}