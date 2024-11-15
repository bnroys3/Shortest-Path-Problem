package assignment5_f20;

import java.util.Set;

public interface NodeInterface {

	public void setIdNum(long l);
	
	public void setLabel(String l);
	
	public long getIdNum();
	
	public String getLabel();
	
	public void addEdge(Node d);
	
	public void removeEdge(Node d);
	
	public boolean containsEdge(Node d);
	
	
}
