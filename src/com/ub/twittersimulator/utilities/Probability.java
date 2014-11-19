/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ub.twittersimulator.node.Node;

/**
 * @author abhi
 *
 */

/*
 * Class containing methods to determine probabilites of nodes.
 */
public class Probability {

	private static double totalInfoSum = 0;
	private static double totalSocialSum = 0;
	private static double infoRange = 0.0;
	private static double socRange = 0.0;
	private static double totalOutInfoSum = 0;
	private static double totalOutSocialSum = 0;
	
	/*
	 * Method to update the social and information probability of every node in the network. 
	 * Accepts and returns the updated node list.
	 */

	public HashMap<Integer, Node> updateProbability(HashMap<Integer, Node> nodeMap) {

		for (int i = 0; i < nodeMap.size(); i++) {
			totalInfoSum = totalInfoSum + nodeMap.get(i).getInfoCount()
					+ nodeMap.get(i).getSocialCount();
			totalSocialSum = totalSocialSum + nodeMap.get(i).getSocialCount();
		}

		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Node> pairs = (Map.Entry<Integer, Node>)it.next();
			Node node = pairs.getValue();
			node.setInfoProb((node.getInfoCount() + node.getSocialCount())
					/ totalInfoSum);
			node.setSocProb(node.getSocialCount() / totalSocialSum);
			pairs.setValue(node);
		}
		totalInfoSum = 0;
		totalSocialSum = 0;
		return nodeMap;
	}
	
	/*
	 * Method to update social and information probability of every node in the network for nodes already
	 * in the network. Accepts and returns updated node list. 
	 * 
	 */
	public HashMap<Integer, Node> updateOutProbability(HashMap<Integer, Node> nodeMap) {

		for (int i = 0; i < nodeMap.size(); i++) {
			totalInfoSum = totalInfoSum + nodeMap.get(i).getInfoOutCount()
					+ nodeMap.get(i).getSocialCount();
		}

		
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Node> pairs = (Map.Entry<Integer, Node>)it.next();
			Node node = pairs.getValue();
			node.setInfoOutProb((node.getInfoCount() + node.getSocialCount())/ totalInfoSum);
			pairs.setValue(node);
		}
		totalInfoSum = 0;
		totalSocialSum = 0;
		
		return nodeMap;
	}

	/*
	 * Method accepts the node list as input and returns a TreeMap with information probability range as key 
	 * and node id as value.
	 */
	public TreeMap<Double, Integer> updateInfoTreeMap(HashMap<Integer, Node> nodeMap) {

		TreeMap<Double, Integer> infoTreeMap = new TreeMap<Double, Integer>();
		
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Node> pairs = (Map.Entry<Integer, Node>)it.next();
			Node node = pairs.getValue();
			infoTreeMap.put(infoRange, node.getNodeId());
			infoRange = infoRange + node.getInfoProb();
		}
		infoRange = 0.0;
		return infoTreeMap;
	}

	/*
	 * Method accepts the node list as input and returns a TreeMap with social probability range as key 
	 * and node id as value.
	 */
	public TreeMap<Double, Integer> updateSocTreeMap(HashMap<Integer, Node> nodeMap) {

		TreeMap<Double, Integer> socTreeMap = new TreeMap<Double, Integer>();
		
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Node> pairs = (Map.Entry<Integer, Node>)it.next();
			Node node = pairs.getValue();
			socTreeMap.put(socRange, node.getNodeId());
			socRange = socRange + node.getSocProb();
		}
		socRange = 0.0;
		
		return socTreeMap;
	}
	
	
	/*
	 * Method accepts the node list as input and returns a TreeMap with information probability range as key 
	 * and node id as value, for dynamic following of nodes.
	 */
	public TreeMap<Double, Integer> updateInfoOutTreeMap(HashMap<Integer, Node> nodeMap) {

		TreeMap<Double, Integer> infoOutTreeMap = new TreeMap<Double, Integer>();
		
		Iterator it = nodeMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Node> pairs = (Map.Entry<Integer, Node>)it.next();
			Node node = pairs.getValue();
			infoOutTreeMap.put(infoRange, node.getNodeId());
			infoRange = infoRange + node.getInfoOutProb();
		}
		
		infoRange = 0.0;
		return infoOutTreeMap;
	}


	
}
