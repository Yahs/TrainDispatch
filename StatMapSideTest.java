import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class StatMapSideTest {
	
	private static Random rand = new Random();
		
	public static void main(String[] args) {
		
		
		List<Pair> sePairs = new ArrayList<Pair>();
		rand.setSeed(666777);
		StationMap map = new StationMap(40);
		List<Edge> connections = new ArrayList<Edge>();
		map.callStation(0).setPos(1, 1);
		map.callStation(1).setPos(1, 4);
		map.callStation(2).setPos(4, 1);
		map.callStation(3).setPos(5, 5);
		map.callStation(4).setPos(6, 3);
		map.callStation(5).setPos(2, 7);
		map.callStation(6).setPos(8, 6);
		map.callStation(7).setPos(3, 9);
		map.callStation(8).setPos(9, 1);
		map.callStation(9).setPos(7, 9);
		map.callStation(10).setPos(11, 1);
		map.callStation(11).setPos(11, 4);
		map.callStation(12).setPos(14, 1);
		map.callStation(13).setPos(15, 5);
		map.callStation(14).setPos(16, 3);
		map.callStation(15).setPos(12, 7);
		map.callStation(16).setPos(18, 6);
		map.callStation(17).setPos(13, 9);
		map.callStation(18).setPos(19, 1);
		map.callStation(19).setPos(17, 9);
		map.callStation(20).setPos(1, 11);
		map.callStation(21).setPos(1, 14);
		map.callStation(22).setPos(4, 11);
		map.callStation(23).setPos(5, 15);
		map.callStation(24).setPos(6, 13);
		map.callStation(25).setPos(2, 17);
		map.callStation(26).setPos(8, 16);
		map.callStation(27).setPos(3, 19);
		map.callStation(28).setPos(9, 11);
		map.callStation(29).setPos(7, 19);
		map.callStation(30).setPos(11, 11);
		map.callStation(31).setPos(11, 14);
		map.callStation(32).setPos(14, 11);
		map.callStation(33).setPos(15, 15);
		map.callStation(34).setPos(16, 13);
		map.callStation(35).setPos(12, 17);
		map.callStation(36).setPos(18, 16);
		map.callStation(37).setPos(13, 19);
		map.callStation(38).setPos(19, 11);
		map.callStation(39).setPos(17, 19);
		
		connections.add(map.callStation(8).getEdge(map.callStation(10), 0));
		connections.add(map.callStation(0).getEdge(map.callStation(2), 1));
		connections.add(map.callStation(1).getEdge(map.callStation(3), 2));
		connections.add(map.callStation(1).getEdge(map.callStation(5), 3));
		connections.add(map.callStation(2).getEdge(map.callStation(4), 4));
		connections.add(map.callStation(3).getEdge(map.callStation(5), 5));
		connections.add(map.callStation(3).getEdge(map.callStation(4), 6));
		connections.add(map.callStation(4).getEdge(map.callStation(6), 7));
		connections.add(map.callStation(5).getEdge(map.callStation(7), 8));
		connections.add(map.callStation(6).getEdge(map.callStation(8), 9));
		connections.add(map.callStation(7).getEdge(map.callStation(9), 10));
		connections.add(map.callStation(9).getEdge(map.callStation(3), 11));
		connections.add(map.callStation(10).getEdge(map.callStation(11), 12));
		connections.add(map.callStation(10).getEdge(map.callStation(12), 13));
		connections.add(map.callStation(11).getEdge(map.callStation(13), 14));
		connections.add(map.callStation(11).getEdge(map.callStation(15), 15));
		connections.add(map.callStation(12).getEdge(map.callStation(14), 16));
		connections.add(map.callStation(13).getEdge(map.callStation(15), 17));
		connections.add(map.callStation(13).getEdge(map.callStation(14), 18));
		connections.add(map.callStation(14).getEdge(map.callStation(16), 19));
		connections.add(map.callStation(15).getEdge(map.callStation(17), 20));
		connections.add(map.callStation(16).getEdge(map.callStation(18), 21));
		connections.add(map.callStation(17).getEdge(map.callStation(19), 22));
		connections.add(map.callStation(17).getEdge(map.callStation(30), 23));
		connections.add(map.callStation(19).getEdge(map.callStation(13), 24));
		connections.add(map.callStation(7).getEdge(map.callStation(22), 25));
		connections.add(map.callStation(20).getEdge(map.callStation(22), 26));
		connections.add(map.callStation(21).getEdge(map.callStation(23), 27));
		connections.add(map.callStation(21).getEdge(map.callStation(25), 28));
		connections.add(map.callStation(22).getEdge(map.callStation(24), 29));
		connections.add(map.callStation(23).getEdge(map.callStation(25), 30));
		connections.add(map.callStation(23).getEdge(map.callStation(24), 31));
		connections.add(map.callStation(24).getEdge(map.callStation(26), 32));
		connections.add(map.callStation(25).getEdge(map.callStation(27), 33));
		connections.add(map.callStation(26).getEdge(map.callStation(28), 34));
		connections.add(map.callStation(27).getEdge(map.callStation(29), 35));
		connections.add(map.callStation(29).getEdge(map.callStation(23), 36));
		connections.add(map.callStation(30).getEdge(map.callStation(9), 37));
		connections.add(map.callStation(30).getEdge(map.callStation(32), 38));
		connections.add(map.callStation(31).getEdge(map.callStation(33), 39));
		connections.add(map.callStation(31).getEdge(map.callStation(35), 40));
		connections.add(map.callStation(32).getEdge(map.callStation(34), 41));
		connections.add(map.callStation(33).getEdge(map.callStation(35), 42));
		connections.add(map.callStation(33).getEdge(map.callStation(34), 43));
		connections.add(map.callStation(34).getEdge(map.callStation(36), 44));
		connections.add(map.callStation(35).getEdge(map.callStation(37), 45));
		connections.add(map.callStation(36).getEdge(map.callStation(38), 46));
		connections.add(map.callStation(37).getEdge(map.callStation(39), 47));
		connections.add(map.callStation(39).getEdge(map.callStation(33), 48));
		connections.add(map.callStation(6).getEdge(map.callStation(30), 49));
		connections.add(map.callStation(19).getEdge(map.callStation(38), 50));
		connections.add(map.callStation(9).getEdge(map.callStation(28), 51));
		connections.add(map.callStation(26).getEdge(map.callStation(29), 52));
		connections.add(map.callStation(31).getEdge(map.callStation(28), 53));
		connections.add(map.callStation(0).getEdge(map.callStation(1), 54));
		connections.add(map.callStation(36).getEdge(map.callStation(39), 55));
		connections.add(map.callStation(5).getEdge(map.callStation(20), 56));
		connections.add(map.callStation(20).getEdge(map.callStation(21), 57));
		connections.add(map.callStation(26).getEdge(map.callStation(35), 58));
		connections.add(map.callStation(30).getEdge(map.callStation(33), 59));
		connections.add(map.callStation(16).getEdge(map.callStation(38), 60));
		connections.add(map.callStation(0).getEdge(map.callStation(3), 61));
		connections.add(map.callStation(12).getEdge(map.callStation(18), 62));
		connections.add(map.callStation(3).getEdge(map.callStation(30), 63));
		connections.add(map.callStation(30).getEdge(map.callStation(28), 64));
		connections.add(map.callStation(28).getEdge(map.callStation(24), 65));
		connections.add(map.callStation(29).getEdge(map.callStation(35), 66));
		
		int[] costBase = new int[500];
		int[] costOpt = new int[500];
		int avgBase = 0, avgOpt = 0;
		int[] edgesMovedB = new int[connections.size()];
		int[] edgesMoved = new int[connections.size()];
		StdDraw.setCanvasSize(1500, 1000);
		StdDraw.clear(new Color(220, 220, 220));
		StdDraw.setXscale(0, 1500);
		StdDraw.setYscale(0, 1000);
		sePairs = getStartEndList(map, connections);
		System.out.println(sePairs);
		edgesMovedB = base(sePairs, connections, map, costBase);
		edgesMoved = traverse(sePairs, connections, map, costOpt);
		drawHeatMap(edgesMoved, 500, connections, map);
		drawHeatMap2(edgesMovedB, 500, connections, map);
		drawMap(map);
		StdDraw.line(750, 0, 750, 750);
		StdDraw.line(0, 750, 1500, 750);
		for (int i = 0; i < 500; i++) {
			avgOpt += costOpt[i];
			avgBase += costBase[i];
		}
		avgOpt /= 500;
		avgBase /= 500;
		StdDraw.text(375, 800, "::Optimized Case::");
		StdDraw.text(375, 775, "Average Cost: " + avgOpt);
		StdDraw.text(1125, 800, "::Base Case::");
		StdDraw.text(1125, 775, "Average Cost: " + avgBase);
		System.out.println("Average optimized cost: " + avgOpt);
		System.out.println("Average base cost: " + avgBase);
		
	}
	
	public static void printList(List<Pair> cons) {
	    System.out.print("Start Station\t\tEnd Station\t\tCost\n");
		for (int i = 0; i < cons.size(); i++) {
	    	System.out.println(cons.get(i));
	    }
	  }
	
	public static int getNearAngleEdge(Station start, double angle) {
		List<Integer> edgeNum = new ArrayList<Integer>();
		double angleComp, angleDiff = 360.0;
		Edge edgeComp;
		int closest = -1;
		for (int i = 0; i < start.getEdges().size(); i++) {
			edgeComp = start.getEdge(i);
			angleComp = edgeComp.getStart(start).getAngle(edgeComp.getEnd(start));
			if (Math.abs(angleDiff - angle) > Math.abs(angleComp - angle)) {
				if (!(edgeComp.getEnd(start).ifVisit() || edgeComp.checkVisited())) {
					angleDiff = angleComp;
					closest = i;
				}
			}
		}
		if (closest != -1) {
			return closest;
		}
		return -1;
	}
	
	public static void drawMap(StationMap stations) {
		double x, y;
		for (int i = 0; i < 40; i++) {
			x = stations.callStation(i).getX() * 37.5;
			y = stations.callStation(i).getY() * 37.5;
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(x, y, 16.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(x, y, 17);
			String s = "" + i;
			StdDraw.text(x, y, s);
		}
		for (int i = 0; i < 40; i++) {
			x = (stations.callStation(i).getX() * 37.5) + 750;
			y = (stations.callStation(i).getY() * 37.5);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(x, y, 16.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(x, y, 17);
			String s = "" + i;
			StdDraw.text(x, y, s);
		}
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(25, 975, 12.5, 12.5);
		StdDraw.textLeft(40, 975, "High Traffic");
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle(25, 935, 12.5, 12.5);
		StdDraw.textLeft(40, 935, "Med-High Traffic");
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(25, 895, 12.5, 12.5);
		StdDraw.textLeft(40, 895, "Med Traffic");
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(185, 975, 12.5, 12.5);
		StdDraw.textLeft(200, 975, "Low-Med Traffic");
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(185, 935, 12.5, 12.5);
		StdDraw.textLeft(200, 935, "Low Traffic");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(185, 895, 12.5, 12.5);
		StdDraw.textLeft(200, 895, "No Traffic");
	}
	
	public static void drawHeatMap(int[] lines, int paths, List<Edge> e, StationMap s) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		double x, y, x1, y1, stPosx, stPosy;
		for (int i = 0; i < lines.length; i++) {
			x = s.callStation(e.get(i).getStart().getNum()).getX() * 37.5;
			y = s.callStation(e.get(i).getStart().getNum()).getY() * 37.5;
			x1 = s.callStation(e.get(i).getEnd().getNum()).getX() * 37.5;
			y1 = s.callStation(e.get(i).getEnd().getNum()).getY() * 37.5;
			stPosx = 0.5 * (x1 - x) + x + 15;
			stPosy = 0.5 * (y1 - y) + y - 15;
			if (lines[i] >= paths / 8) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 20) {
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 35) {
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 50) {
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] > 0) {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			}
		}
	}
	
	public static void drawHeatMap2(int[] lines, int paths, List<Edge> e, StationMap s) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		double x, y, x1, y1, stPosx, stPosy;
		for (int i = 0; i < lines.length; i++) {
			x = s.callStation(e.get(i).getStart().getNum()).getX() * 37.5 + 750;
			y = s.callStation(e.get(i).getStart().getNum()).getY() * 37.5;
			x1 = s.callStation(e.get(i).getEnd().getNum()).getX() * 37.5 + 750;
			y1 = s.callStation(e.get(i).getEnd().getNum()).getY() * 37.5;
			stPosx = 0.5 * (x1 - x) + x + 15;
			stPosy = 0.5 * (y1 - y) + y - 15;
			if (lines[i] >= paths / 8) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 20) {
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 35) {
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] >= paths / 50) {
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else if (lines[i] > 0) {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			} else {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.line(x, y, x1, y1);
				String st = "" + e.get(i).getCost();
				StdDraw.text(stPosx, stPosy, st);
			}
		}
	}
	
	
	public static List<Pair> getStartEndList(StationMap s, List<Edge> e) {
		int paths = 500;
		int start, end;
		List<Pair> out = new ArrayList<Pair>();
		for (int i = 0; i < paths; i++) {
			start = rand.nextInt(4);
			end = rand.nextInt(15) + 25;
			while (start == end) {
				end = rand.nextInt(40);
			}
			out.add(new Pair(s.callStation(start), s.callStation(end)));
		}
		return out;
	}
	
	public static int[] traverse(List<Pair> se, List<Edge> edges, StationMap s,
			int[] cost) {
		int[] edgeTaken = new int[edges.size()];
		int pathEdge = 0;
		double angle;
		Station current = new Station();
		Station end = new Station();
		for (int i = 0; i < se.size(); i++) {
			clearPath(edges);
			clearStations(s);
			for (int j = 0; j < s.getStations().size(); j++) {
				s.callStation(j).visit(false);
			}
			current = se.get(i).getStart();
			end = se.get(i).getEnd();
			angle = current.getAngle(end);
			while (current.getNum() != end.getNum()) {
				current.visit(true);
				if (pathEdge != getNearAngleEdge(current, angle)) {
					pathEdge = getNearAngleEdge(current, angle);
				} else {
					pathEdge--;
				}
				if (pathEdge != -1) {
					edgeTaken[current.getEdge(pathEdge).getNum()]++;
					cost[i] += current.getEdge(pathEdge).getCost();	
					current.getEdge(pathEdge).visit(true);
					current = current.getEdge(pathEdge).getEnd(current);
				} else {
					break;
				}
			}
			pathEdge = 0;
		}
		return edgeTaken;
	}
	
	public static void clearPath(List<Edge> e) {
		for (int i = 0; i < e.size(); i++) {
			e.get(i).visit(false);
		}
	}
	
	public static void clearStations(StationMap s) {
		for (int i = 0; i < s.getStations().size(); i++) {
			s.callStation(i).visit(false);
		}
	}
	
	public static int[] base(List<Pair> se, List<Edge> edges, StationMap sm,
			int[] cost) {
		int[] edgeTaken = new int[edges.size()];
		int next;
		for (int i = 0; i < 500; i++) {
			clearPath(edges);
			clearStations(sm);
			Station s = se.get(i).getStart();
			Station e = se.get(i).getEnd();
			while (s.getNum() != e.getNum()) {
				next = rand.nextInt(s.getEdges().size());
				s.visit(true);
				s.getEdge(next).visit(true);
				cost[i] += s.getEdge(next).getCost();
				edgeTaken[s.getEdge(next).getNum()]++;
				s = s.getEdge(next).getEnd(s);
				if (s.checkVisits()) {
					break;
				}
			}
		}
		return edgeTaken;
	}

}
