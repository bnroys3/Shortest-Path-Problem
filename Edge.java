package assignment5_f20;

public class Edge {
	private Node source;
	private Node destination;
	private long idNum;
	private String label;
	private long weight;

		public Edge(Node source, Node destination, long idNum, long weight, String eLabel) {
			this.source = source;
			this.destination = destination;
			this.idNum=idNum;
			this.weight=weight;
			label = eLabel;
		}
		
		public long getWeight() {
			return weight;
		}
		
		public String getLabel() {
			return label;
		}
		
		public void setIdNum(long l) {
			idNum=l;
		}
		
		public void setSource(Node s) {
			source=s;
		}
		
		public void setDestination(Node d) {
			destination=d;
		}
		
		public long getIdNum() {
			return idNum;
		}
		
		public Node getSource() {
			return source;
		}
		
		public Node getDestination() {
			return destination;
		}
		
}
