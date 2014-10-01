/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

/**
 * @author abhi
 *
 */

/*
 * Class contains method to compare map values.
 */
public class CompareMapValues {

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
}
