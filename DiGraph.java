package assignment5_f20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {
private HashMap<String, Node> nodeHM;
//private HashMap<Long, Node> nodeIDs;
//private HashMap<Long, Edge> edgeIDs;
//private ArrayList<String> nodeKeys;


private int numNodes;
private int numEdges;
  // in here go all your data and methods for the graph

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  numNodes=0;
	  numEdges=0;
	  nodeHM=new HashMap<String, Node>();
//	  nodeIDs=new HashMap<Long,Node>();
//	  edgeIDs=new HashMap<Long, Edge>();
//	  nodeKeys=new ArrayList<String>();
  }

@Override
public boolean addNode(long idNum, String label) {
	if(idNum<0) {
		return false;
	}
	if(label==null) {
		return false;
	}
	if(nodeHM.containsKey(label)) {
		return false;
	}
//	if(nodeIDs.containsKey(idNum)) {
//		return false;
//	}
	
	Node n = new Node(label, idNum);
	numNodes++;
	nodeHM.put(label, n);
//	nodeIDs.put(idNum, n);
//	nodeKeys.add(label);
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if(idNum<0) {
		return false;
	}
	if(!nodeHM.containsKey(sLabel)) {
		return false;
	}
	if(!nodeHM.containsKey(dLabel)) {
		return false;
	}
//	if(edgeIDs.containsKey(idNum)) {
//		return false;
//	}
	Node src = nodeHM.get(sLabel);
	Node dest = nodeHM.get(dLabel);
	if(src.containsEdge(dest)) {
		return false;
	}
	
	Edge e = new Edge(src, dest, idNum,weight,dLabel);
	numEdges++;
//	edgeIDs.put(idNum, e);
	src.addEdge(dest,e);
	return true;
}

@Override
public boolean delNode(String label) {
	if(!nodeHM.containsKey(label)) {
		return false;
	}
	Node n = nodeHM.get(label);
	String[] edgesArray = n.getEdges().toArray(new String[0]);
	for(String dest: edgesArray) {
		delEdge(label, dest);
	}
	String[] inEdgesArray = n.getInEdges().toArray(new String[0]);
	for(String src: inEdgesArray) {
		delEdge(src, label);
	}
//	nodeIDs.remove(nodeHM.get(label).getIdNum());
	nodeHM.remove(label);
//	nodeKeys.remo
	numNodes--;
	return true;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	if(!nodeHM.containsKey(sLabel)) {
		return false;
	}
	if(!nodeHM.containsKey(dLabel)) {
		return false;
	}
	

	Node src = nodeHM.get(sLabel);
	Node dest = nodeHM.get(dLabel);
	
	if(!src.containsEdge(dest)) {
		return false;
	}

//	edgeIDs.remove(nodeHM.get(sLabel).getEdge(dLabel).getIdNum());
	dest.remDestEdge(src);
	src.removeEdge(dest);
	numEdges--;
	return true;
}

@Override
public long numNodes() {
	return numNodes;
}

@Override
public long numEdges() {
	return numEdges;
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	if(numNodes==0||numEdges==0) {
		return new ShortestPathInfo[0];
	}
	ShortestPathInfo[] paths = new ShortestPathInfo[numNodes];
	HashMap<String,Node> untouched = new HashMap<String,Node>();
	String[] nodes = nodeHM.keySet().toArray(new String[0]);
	//System.out.println("arrsize is " + nodes.length);
	//System.out.println("hmsize is " + nodeHM.);
	for(String node:nodes) {
		untouched.put(node, nodeHM.get(node));
	}
	Node ln = nodeHM.get(label);
	paths[0] = new ShortestPathInfo(label, 0);
	untouched.remove(label);
	int i = 1;
	PriorityQueue<ShortestPathInfo> pq = new PriorityQueue<ShortestPathInfo>(new PathComparator());
	for(String n: ln.getEdges()) {
		pq.add(new ShortestPathInfo(n,ln.weight(nodeHM.get(n))));
	}
	while(i<numNodes) {

//		System.out.println("\nNode: " + pq.peek().getDest());
//		System.out.println("C Untouched: " + untouched.containsKey("C") + "(i="+i+")");
		
		if(pq.peek()!=null&&untouched.containsKey(pq.peek().getDest())) {
			
			paths[i]=pq.peek();
			untouched.remove(pq.peek().getDest());
			Node currDest = nodeHM.get(pq.peek().getDest());
			for(String n: currDest.getEdges()) {
				if(untouched.containsKey(n)) {
					pq.add(new ShortestPathInfo(n,currDest.weight(nodeHM.get(n))+pq.peek().getTotalWeight()));
//					System.out.println(n+" added to pq.");
				}
			}
			pq.remove();
			i++;
		} else if(pq.peek()==null){
			
			String[] leftToAdd = untouched.keySet().toArray(new String[0]);
			
			for(String node:leftToAdd) {
				paths[i]=new ShortestPathInfo(node,-1);
				i++;
			}
		} else if(!untouched.containsKey(pq.peek().getDest())) {
			pq.remove();
		}
	}
	return paths;
}
  
  // rest of your code to implement the various operations
}
