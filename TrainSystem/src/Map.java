import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class Map {
	
//	private final Color colors[] = {
//	        Color.orange, Color.DARK_GRAY, Color.pink,
//	        Color.cyan, Color.magenta, Color.yellow};
	
	
	private DijkstraSP[] shortestPaths;
	private ArrayList<Station> nodes = new ArrayList<Station>();	
	private ArrayList<Track> edges = new ArrayList<Track>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<StationLabel> labels = new ArrayList<StationLabel>();
	
	private String path = "";
	
	private int width = Integer.MIN_VALUE;
	private int height = Integer.MIN_VALUE;
	MapPanel panel;
	
	private File nodeData = null;
	private File edgeData = null;
	private File trainData = null;
	
	public Map (MapPanel mp) {
		panel = mp;
	}
	
	public void print (Station s) {
		panel.setInfo(s.toString());
	}
	public void print (Track t) {
		panel.setInfo(t.toString());
	}
	public void print (Train t) {
		panel.setInfo(t.toString());
	}
	
	/*** search algorithms go here ***/	
	
	public void createMap () {
		shortestPaths = new DijkstraSP[nodes.size()];
		for (int i = 0; i < nodes.size(); i++)
			shortestPaths[i] = new DijkstraSP(this, i);
	}
	
	public void removeNode (Station s) {
		for (Track t: s.getAdj())
			edges.remove(t);
		nodes.remove(s);
	}
	
	public void removeEdge(Track t) {
		edges.remove(t);
	}
	
	public void removeLabel(int station) {
		labels.remove(station);
	}
	
	public void clear() {
		trains.clear();
		edges.clear();
		nodes.clear();
		labels.clear();
	}
	
	/***** Setters *****/
	public void addEdge (Track track) { edges.add(track); }
	public void addNode (Station station) { nodes.add(station); }
	public void addTrain (Train train) { trains.add(train); }
	
	public void addLabel(Station station) {
		String n = station.getName();
		int x = station.x;
		int y = station.y+20;
		int s = getNodeIndex(station);
		labels.add(new StationLabel(n, x, y, s, panel.getCanvas().getGraphics()));
	}
	
	public void setScale() {
		
	}
	
	/***** Getters ****/
	
	public ArrayList<Track> getEdges() { return edges; }
	public ArrayList<Station> getNodes () { return nodes; }
	public ArrayList<Train> getTrains () { return trains; }
	public ArrayList<StationLabel> getLabels () { return labels; }
	public LinkedList<Track> getPath(int startStation, int endStation) { 
		return shortestPaths[startStation].pathTo(endStation);
	}

	public Station getNode (int i) { return nodes.get(i); }
	public int getNodeIndex (Station s) { return nodes.indexOf(s); }
	public int getEdgeIndex (Track t) { return edges.indexOf(t); }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void save () {
		JFileChooser nodeDialog = new JFileChooser();
		JFileChooser edgeDialog = new JFileChooser();
		
		Path dir = Paths.get("./");
		
		nodeDialog.setCurrentDirectory(dir.toFile());
		nodeDialog.setSelectedFile(new File("NodeData.txt"));
		edgeDialog.setSelectedFile(new File("EdgeData.txt"));
		
		int feedback = nodeDialog.showSaveDialog(null);
		if (feedback != JFileChooser.CANCEL_OPTION) {
			nodeData = nodeDialog.getSelectedFile();
			saveNodes(nodeData);
			
			path = nodeData.getAbsolutePath();
			path = path.substring(0, path.lastIndexOf('/')) + "/";
			
			edgeDialog.setCurrentDirectory(nodeData);
			
			feedback = edgeDialog.showSaveDialog(null);
			if (feedback != JFileChooser.CANCEL_OPTION) {
				edgeData = edgeDialog.getSelectedFile();
				
				saveTracks(edgeData);
				saveLabels();
			}
		}
	}
	
	public void saveNodes (File file)
	{
		FileWriter out = null;
		try {
			out = new FileWriter(file);
			for (Station s: nodes) {
				out.write(s.x + " " + s.y + " " + s.getCapacity() 
						  + " " + s.getName().trim() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Failed saving node data file");
		}
	}
	
	public void saveTracks (File file) {
		FileWriter out = null;
		try {
			out = new FileWriter(file);
			for (Track t: edges) {
				out.write(getNodeIndex(t.from()) + ", ");
				out.write(getNodeIndex(t.to())+"");
				for (Point p: t.getVertices())
					out.write(", " + p.x + ", " + p.y);
				out.write("\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Failed saving node data file");
		}
	}
	
	public void saveLabels () {
		String file = nodeData.getName();
		file = file.substring(0, file.lastIndexOf('.')) + "_Labels.txt";
		FileWriter out = null;
		try {
			out = new FileWriter(new File(path + file));
			for (StationLabel label: labels) {
				out.write(label.getX()+ ",");
				out.write(label.getY()+",");
				out.write(label.getStation()+",");
				out.write(label.getText());
				out.write("\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Failed saving node data file");
		}
	}
	
	public void loadMap () {
		Path dir = Paths.get("./");
		
		JFileChooser nodeDialog = new JFileChooser();
		JFileChooser edgeDialog = new JFileChooser();
		JFileChooser trainDialog = new JFileChooser();
		
		nodeDialog.setCurrentDirectory(dir.toFile());
		nodeDialog.setSelectedFile(new File("NodeData.txt"));
		int button1 = nodeDialog.showOpenDialog(null);
		
		if (button1 != JFileChooser.CANCEL_OPTION) {
			nodeData = nodeDialog.getSelectedFile();
			
			edgeDialog.setCurrentDirectory(nodeData);
			edgeDialog.setSelectedFile(new File("EdgeData.txt"));
			int button2 = edgeDialog.showOpenDialog(null);
			
			if (button2 != JFileChooser.CANCEL_OPTION)
				edgeData = edgeDialog.getSelectedFile();
			
//			trainDialog.setCurrentDirectory(nodeData);
//			trainDialog.setSelectedFile(new File("TrainData.txt"));
//			int button3 = trainDialog.showOpenDialog(null);
//			if (button3 != JFileChooser.CANCEL_OPTION)
//				trainData  = trainDialog.getSelectedFile();
			
			path = nodeData.getAbsolutePath();
			path = path.substring(0, path.lastIndexOf('/')) + "/";
		}
		
		reset();
	}
	
	public void reset () {
		edges.clear();
		nodes.clear();
		trains.clear();
		labels.clear();
		
		if (nodeData != null && edgeData != null) {
			loadNodes(nodeData);
			loadEdges(edgeData);
		}
		
//		createMap();
//		if (trainData != null) {
//			loadTrains(trainData);
//		}
	}
	
	public void loadNodes(File file) {
		
		try {
			Scanner in = new Scanner (file);
			while (in.hasNextLine()) {
				int x = in.nextInt();
				int y = in.nextInt();
				int cap = in.nextInt();
				String name = in.nextLine().trim();
				nodes.add(new Station (name, cap, new Point(x, y)));
				if (width < x)
					width = x;
				if (height < y)
					height = y;
			}
			in.close();
			defaultLabel();
		} catch (Exception e) {
			System.out.println("Failed to load node data file.");
		}
	}
	
	public void defaultLabel() {
		String fileName = nodeData.getName();
		fileName = fileName.substring(0, fileName.lastIndexOf('.')) + "_Labels.txt";
		
		File file = new File (path + fileName);
		if (file.isFile()) {
			loadLabels(file);
		} else {
			for (Station s: nodes) {
				addLabel(s);
			}
		}
	}
	
	public void loadLabels (File file) {
		try {
			Scanner in = new Scanner (file);
			while (in.hasNextLine()) {
				String nextLine[] = in.nextLine().split(",");
				int x = Integer.parseInt(nextLine[0]);
				int y = Integer.parseInt(nextLine[1]);
				int station = Integer.parseInt(nextLine[2]);
				String text = nextLine[3];
				Graphics g = panel.getCanvas().getGraphics();
				StationLabel label = new StationLabel(text, x, y, station, g);
				labels.add(label);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Failed to load the labels" + e);
		}
	}
	
	public void loadEdges (File file) {
		try {
			Scanner in = new Scanner (file);
			while (in.hasNextLine()) {
				String data[] = in.nextLine().split(", ");
				int start = Integer.parseInt(data[0]);
				int end = Integer.parseInt(data[1]);
				ArrayList<Point> points = new ArrayList<Point>(); 
				
				for (int i = 2; i < data.length; i+=2) {
					int x = Integer.parseInt(data[i]);
					int y = Integer.parseInt(data[i+1]);
				 	points.add(new Point(x, y));
				 	if (width < x)
						width = x;
					if (height < y)
						height = y;
				}
				edges.add(new Track(getNode(start), getNode(end), points));
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Failed to load edge data file." + e);
		}
	}
	
//	public void loadTrains (File file) {
//		try {
//			Scanner in = new Scanner (file);
//			int cnt = 0;
//			while (in.hasNextLine()) {
//				String nextLine[] = in.nextLine().split(",");
//				double startTime = Double.parseDouble(nextLine[0]);
//				int startStation = Integer.parseInt(nextLine[1]);
//				int endStation = Integer.parseInt(nextLine[2]);
//				
//				//LinkedList<Track> path = shortestPaths[startStation].pathTo(endStation);
//				Train t = new Train(100, startTime, getNode(startStation), getNode(endStation));
//				t.color = colors[cnt];
//				cnt = (cnt >= colors.length-1) ? 0 : cnt+1;
//				trains.add(t);
//			}
//			in.close();
//		} catch (Exception e) {
//			System.out.println("Failed to load trains.");
//			System.out.println(e);
//		}
//	}
}
