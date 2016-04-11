/*
 * Modified class from the Algorithms book by Robert Sedgewick and Kevin wayne.
 * Found on page 655.
 * 
 */


import java.util.LinkedList;

public class DijkstraSP {
	private IndexMinPQ<Double> pq;
	private Track [] edgeTo;
	private double[] distTo;
	
	private Map map;
	
	public DijkstraSP (Map m, int s) {
		map = m;
		
		edgeTo = new Track[map.getNodes().size()];
		distTo = new double[map.getNodes().size()];
		pq = new IndexMinPQ<Double>(map.getNodes().size());
		
		for (int i = 0; i < distTo.length; i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		
		distTo[s] = 0.0f;
		
		pq.insert(s, 0.0);
		while (!pq.isEmpty())
			relax(pq.delMin());
		
	}
	
	public void relax (int fromStation) {
		Station u = map.getNode(fromStation);
		for (Track t: u.getAdj())
		{
			if (!t.isLocked()) {
				int toStation = map.getNodeIndex(t.getNeighbor(u));
				if (distTo[toStation] > distTo[fromStation] + t.getLength()) {
					distTo[toStation] = distTo[fromStation] + t.getLength();
					edgeTo[toStation] = t;
					if (pq.contains(toStation))
						pq.changeKey(toStation, distTo[toStation]);
					else
						pq.insert(toStation, distTo[toStation]);
				}
			}
		}
	}
	
	public double distTo (int node) {
		return distTo[node];
	}
	public boolean hasPathTo (int node) {
		return distTo[node] < Double.POSITIVE_INFINITY;
	}
	public LinkedList<Track> pathTo (int node) {
		if (!hasPathTo(node)) return null;
		LinkedList<Track> path = new LinkedList <Track>();
		
		Track t = edgeTo[node];
		Station current = map.getNode(node);
		for (; t != null; t = edgeTo[map.getNodeIndex(t.getNeighbor(current))])
		{
			if (!path.contains(t))
				path.addFirst(t);
			current = t.getNeighbor(current);
		}
		return path;
	}
	
}
