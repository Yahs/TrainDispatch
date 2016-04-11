import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class MapCanvas extends JLabel implements MouseListener, MouseMotionListener {
	public static enum operations {PAN, ZOOM_IN, ZOOM_OUT, DRAW_EDGE, DRAW_NODE, SCALE};
	private static final int NODE_RADIUS = 10;
	
	private operations selectedOperation = operations.DRAW_NODE;
	private Map map;
	
	private ArrayList<Point> vertices = new ArrayList<Point>();
	
	private Point mouseLocation = null;
	private boolean isDrawingEdge = false;
	
	private Track selectedTrack;
	private int selectedVerticeIndex = -1;
	private Station selectedStation;
	private Station nearestStation;
	private StationLabel selectedLabel = null;
		
	private Color edgeColor = Color.GRAY;
	private Color nodeColor = Color.LIGHT_GRAY;
	private final Color colors[] = {
		        Color.gray, Color.LIGHT_GRAY, Color.darkGray, Color.orange,
		        Color.cyan, Color.magenta, Color.green, Color.yellow};
	
	
	public MapCanvas (Map map) {
		this.setHorizontalAlignment(LEFT);
		this.setVerticalAlignment(NORTH);
		this.map = map;
		setAutoscrolls(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		RenderingHints r = new RenderingHints (RenderingHints.KEY_ANTIALIASING,
											   RenderingHints.VALUE_ANTIALIAS_ON);
		
		r.add(new RenderingHints(RenderingHints.KEY_RENDERING, 
							     RenderingHints.VALUE_RENDER_QUALITY));
				
		BasicStroke edgeStroke = new BasicStroke(6.0f, 
								 BasicStroke.CAP_ROUND,
		                         BasicStroke.JOIN_ROUND,
		                         10.0f);
		
		BasicStroke connectorStroke = new BasicStroke(1.0f, 
				 					  BasicStroke.CAP_ROUND,
				 					  BasicStroke.JOIN_ROUND,
				 					  1.0f);
		
		g2.setRenderingHints(r);
		g2.setStroke(edgeStroke);
		
		//Draws color square patches to change the edge and node color. 
		for (int i = 0; i < colors.length; i++) {
			g.setColor(colors[i]);
			g.fillRect(20, i*20+20, 10, 10);
		}
		
		//Highlights the nearest station or the vertex that was selected.
		if (nearestStation != null) {
			g2.setColor(Color.cyan);
			int xPos = nearestStation.x - NODE_RADIUS/2 - 3;
			int yPos = nearestStation.y - NODE_RADIUS/2 - 3;
			g2.fillOval(xPos, yPos, NODE_RADIUS+6, NODE_RADIUS + 6);
		} else if (selectedVerticeIndex != -1 && selectedTrack != null) {
			g2.setColor(Color.cyan);
			Point p = selectedTrack.getVertex(selectedVerticeIndex);
			int xPos =  p.x - NODE_RADIUS/2 - 3;
			int yPos = p.y - NODE_RADIUS/2 - 3;
			g2.fillOval(xPos, yPos, NODE_RADIUS+6, NODE_RADIUS + 6);
		}
		
		//Draws the line between the last vertices and the mouser cursor.
		g2.setColor(Color.gray);
		if (isDrawingEdge) {
			Point p1 = vertices.get(vertices.size() - 1);
			g2.drawLine(p1.x, p1.y, mouseLocation.x, mouseLocation.y);
		}
		
		//Draws the edge that is currently being made by the user.
		if (vertices != null)
			drawEdge (vertices, g2);
		
		//Draws the edges within the map and changes the color to red if it is locked.
		for (Track t: map.getEdges()) {
			if (t.color != null)
				g2.setColor(t.color);
			else
				g2.setColor(edgeColor);
			
			drawEdge (t.getVertices(), g2);
		}
		
		//Draws the nodes within the map.
		g2.setStroke(connectorStroke);
		for (Station s: map.getNodes()) {
			int xPos = s.x-NODE_RADIUS/2;
			int yPos = s.y-NODE_RADIUS/2;
			g2.fillOval(xPos, yPos, NODE_RADIUS, NODE_RADIUS);
		}
		
		//Draw the labels
		int cnt = 0;
		
		ArrayList<Station> stations = map.getNodes();
		for (StationLabel label: map.getLabels()) {
			g2.setColor(nodeColor.darker().darker());
			g2.fill(label.getBounds());
			g2.drawLine(stations.get(cnt).x, stations.get(cnt).y, label.getX(), label.getY());
			g2.setColor(nodeColor);
			g2.drawString(label.getText(), label.getX(), label.getY());
			cnt++;
		}		
		
		
		//Draws the trains within the map.
		g2.setStroke(edgeStroke);
		for (Train t: map.getTrains()) {
			g2.setColor(t.color);
			int xPos = t.x-NODE_RADIUS/2;
			int yPos = t.y-NODE_RADIUS/2;
			g2.fillRect(xPos, yPos, NODE_RADIUS, NODE_RADIUS);
		}
		
		g2.dispose();
	}
	
	public void drawEdge (ArrayList<Point> v, Graphics2D g2) {
		for (int i = 0; i < v.size() - 1; i++) {
			Point p1 = v.get(i);
			Point p2 = v.get(i + 1);
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
		Color temp = g2.getColor();
		g2.setColor(Color.black);
		for (int i = 1; i < v.size() - 1; i++) {
			int xPos = v.get(i).x-NODE_RADIUS/2;
			int yPos = v.get(i).y-NODE_RADIUS/2;
			g2.fillOval(xPos, yPos, NODE_RADIUS, NODE_RADIUS);
		}
		g2.setColor(temp);

	}
	
	public void updateSize (int w, int h) {
		if (getHeight() < h+100 || getHeight() < h+100)
			this.setPreferredSize(new Dimension(w+100, h+100));
		this.revalidate();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (selectedOperation == operations.DRAW_EDGE) {
			if (e.getPoint().x < 30 && e.getPoint().y  < colors.length*20+20) {
				edgeColor = colors[(e.getPoint().y - 20)/20];
			} else if (SwingUtilities.isLeftMouseButton(e)) {
				addVerticy(e.getPoint());
			} else if (SwingUtilities.isRightMouseButton(e)) {
				vertices.clear();
				isDrawingEdge = false;
			}
		} else if (selectedOperation == operations.DRAW_NODE) {
			if (e.getPoint().x < 30 && e.getPoint().y < colors.length*20 + 20) {
				nodeColor = colors[(e.getPoint().y - 20)/20];
			} else if (nearestStation == null) {
				String txtMSG = "Station " + map.getNodes().size();
				CreateStationDialog dialog = new CreateStationDialog(e.getPoint(), txtMSG, "10");
				int result = JOptionPane.showConfirmDialog(this, dialog, "Create Station", JOptionPane.OK_CANCEL_OPTION);
				if (result != JOptionPane.CANCEL_OPTION) {
					Station s = dialog.getStation();
					map.addNode(s);
					map.addLabel(s);
				}
			}
		} else if (selectedOperation == operations.SCALE) {
			vertices.add(e.getPoint());
			isDrawingEdge = true;
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
				
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		nearestStation = getNearestStation(e.getPoint());
		selectedTrack = getNearestTrack(e.getPoint());
		
		
		if (selectedTrack != null) {
			int i = selectedTrack.getNearestVertice(e.getPoint(), 10);
			if (i != 0 || i != selectedTrack.getVertices().size() - 1)
				selectedVerticeIndex = i;
		}
		
		mouseLocation = e.getPoint();
		if (nearestStation != null)
			map.print(nearestStation);
		else if (selectedTrack != null)
			map.print(selectedTrack);
		
		for (StationLabel label: map.getLabels()) {
			if (label.contains(e.getPoint())) {
				selectedLabel = label;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		selectedLabel = null;
		if (selectedOperation == operations.SCALE) {
			JOptionPane input = new JOptionPane();
			String feedback = input.showInputDialog("Number of Trains: ");
			double scale = Double.parseDouble(feedback);
			//set scale -- alot harder than it looked
			//remove edges
			isDrawingEdge = false;
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Rectangle r = getVisibleRect();
		
		//Pans if the buttons was selected or the middle mouse button was pressed.
		if (selectedOperation == operations.PAN || 
			SwingUtilities.isMiddleMouseButton(e)) {
			r.translate(mouseLocation.x - e.getX(), mouseLocation.y - e.getY());
			scrollRectToVisible(r);
		}
		
		//Allows the user to drag the nearest station
		if (nearestStation != null)
			nearestStation.setLocation(e.getPoint());
		
		//Allows the user to drag the vertex of the track.
		if (selectedVerticeIndex != -1 && selectedTrack != null)
			selectedTrack.setVertice(selectedVerticeIndex, e.getPoint());
		
		//Allows the user to drag labels
		if (selectedLabel != null) {
			selectedLabel.setLocation(e.getPoint());
		}
		
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (isDrawingEdge)
			mouseLocation = e.getPoint();
		repaint();
	}

	public Station getNearestStation (Point p) {
		double smallestDistance = NODE_RADIUS;
		Station nearestStation = null;
		for (Station s: map.getNodes()) {
			if (s.distance(p) < smallestDistance) {
				nearestStation = s;
				smallestDistance = s.distance(p);
			}
		}
		return nearestStation;
	}
	
	public Track getNearestTrack (Point p) {
		Track nearestTrack = null;
		for (Track t: map.getEdges()) {
			int i = t.getNearestVertice(p, 10);
			if (i != -1) {
				nearestTrack = t;
				break;
			}
		}
		return nearestTrack;
	}
	
	public Station getSelectedStation() { return selectedStation; }
	
	
	public void addVerticy (Point p) {		
		if (vertices.isEmpty()) {
			if (nearestStation != null) {
				vertices.add((Point)nearestStation);
				selectedStation = nearestStation;
				isDrawingEdge = true;
			} else {
				JOptionPane.showMessageDialog(this, "Edge must start at a station");
			}
		} else {
			if (nearestStation != null) {
					vertices.add((Point)nearestStation);
					map.addEdge(new Track(selectedStation, nearestStation, vertices));
					vertices.clear();
					vertices.add((Point)nearestStation);
					selectedStation = nearestStation;
			} else {
				vertices.add(p);
			}
		}	
	}
	
	
	public void deleteSelected() {
		if (selectedOperation == operations.DRAW_EDGE) {
			map.removeEdge(selectedTrack);
			selectedVerticeIndex = -1;		
		} else if (selectedOperation == operations.DRAW_NODE) {
			map.removeLabel(map.getNodeIndex(nearestStation));
			map.removeNode(nearestStation);
			nearestStation = null;
		}
		repaint();
	}
	
	public void setSelectedStation(Station s) { selectedStation = s; }
	public void setOperation (operations o) {
		selectedOperation = o;
		switch (selectedOperation) {
		case PAN:
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			break;
		default:
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
