
package assignment5_f20;
import java.util.*;


public class PathComparator implements Comparator<ShortestPathInfo>{

	@Override
	public int compare(ShortestPathInfo o1, ShortestPathInfo o2) {
		if(o1.getTotalWeight()>o2.getTotalWeight()) {
			return 1;
		}
		if(o1.getTotalWeight()<o2.getTotalWeight()) {
			return -1;
		}
		
		return 0;
	}

}
