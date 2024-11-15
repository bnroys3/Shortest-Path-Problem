package assignment5_f20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Node implements NodeInterface {
private String label;
private long idNum;
private HashMap<String, Node> destNodes;
private HashMap<String, Edge> edges;
//private ArrayList<String> destKeys;
//private ArrayList<String> inEdgeKeys;
private HashMap<String, Edge> inEdges;


	public Node(String label, long idNum) {
		this.label=label;
		this.idNum=idNum;
		destNodes = new HashMap<String, Node>();
		edges = new HashMap<String, Edge>();
		inEdges = new HashMap<String, Edge>();
//		destKeys = new ArrayList<String>();
//		inEdgeKeys = new ArrayList<String>();
	}
	
	public void setIdNum(long l) {
		idNum=l;
	}
	
	public void setLabel(String l) {
		label=l;
	}
	
	public long getIdNum() {
		return idNum;
	}
	public void destEdge(Node s) {
		inEdges.put(s.getLabel(), edges.get(s.getLabel()));
//		inEdgeKeys.add(s.getLabel());
	}
	public void remDestEdge(Node s) {
		inEdges.remove(s.getLabel());
//		inEdgeKeys.remove(s.getLabel());
	}
	
	public String getLabel() {
		return label;
	}
	
	public void addEdge(Node d,Edge e) {
		destNodes.put(d.getLabel(),d);
		d.destEdge(this);
//		destKeys.add(d.getLabel());
		edges.put(d.getLabel(),e);
	}
	
	public void removeEdge(Node d) {
		d.remDestEdge(this);
		destNodes.remove(d.getLabel(),d);
		edges.remove(d.getLabel());
//		destKeys.remove(d.getLabel());
	}
	
	public boolean containsEdge(Node d) {
		return destNodes.containsKey(d.getLabel());
	}
	
	public long weight(Node d) {
		return edges.get(d.getLabel()).getWeight();
	}
	public Edge getEdge(String dest) {
		return edges.get(dest);
	}
	
	public Set<String> getEdges() {
		return destNodes.keySet();
	}
	public Set<String> getInEdges() {
		return inEdges.keySet();
	}

	@Override
	public void addEdge(Node d) {
		destNodes.put(d.getLabel(),d);

		edges.put(d.getLabel(),new Edge(this,d,d.getIdNum(),1,d.getLabel()));
	}
	
}
