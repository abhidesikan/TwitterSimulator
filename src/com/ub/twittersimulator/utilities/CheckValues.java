/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import com.ub.twittersimulator.node.Node;

/**
 * @author abhi
 *
 */

/*
 * Class contains method to compare map values.
 */
public class CheckValues {

	/*
	 * Method accepts a TreeMap and key as input and returns the node id which falls under the range in the key.
	 * 
	 */
	public static <K, V> V mappedValue(TreeMap<K, V> map, K key) {
	    Entry<K, V> e = map.floorEntry(key);
	    if (e != null && e.getValue() == null) {
	        e = map.lowerEntry(key);
	    }
	    return e == null ? null : e.getValue();
	}
	
	public static void checkClosure(Node node1, int nodeId, List nodeList){
		
		List followers = node1.getFollowing();
		Iterator it = followers.iterator();
		Node node2 = (Node)nodeList.get(nodeId);
		
		while(it.hasNext()){
			Node node = (Node) nodeList.get((Integer)it.next());
			if(node.getFollowing().contains(nodeId)){
				node2.setClosureCount(node2.getClosureCount()+1);
				break;
			}
		}
		
	}
}
