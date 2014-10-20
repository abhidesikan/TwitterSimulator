/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.util.List;
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

	public List<Node> updateProbability(List<Node> nodeList) {

		for (int i = 0; i < nodeList.size(); i++) {
			totalInfoSum = totalInfoSum + nodeList.get(i).getInfoCount()
					+ nodeList.get(i).getSocialCount();
			totalSocialSum = totalSocialSum + nodeList.get(i).getSocialCount();
		}
		for (Node node : nodeList) {
			node.setInfoProb((node.getInfoCount() + node.getSocialCount())
					/ totalInfoSum);
			node.setSocProb(node.getSocialCount() / totalSocialSum);
		}
		totalInfoSum = 0;
		totalSocialSum = 0;
		return nodeList;
	}
	
	/*
	 * Method to update social and information probability of every node in the network for nodes already
	 * in the network. Accepts and returns updated node list. 
	 * 
	 */
	public List<Node> updateOutProbability(List<Node> nodeList) {

		for (int i = 0; i < nodeList.size(); i++) {
			totalInfoSum = totalInfoSum + nodeList.get(i).getInfoOutCount()
					+ nodeList.get(i).getSocialCount();
		}
		for (Node node : nodeList) {
			node.setInfoOutProb((node.getInfoOutCount() + node.getSocialCount())
					/ totalInfoSum);
		}
		totalInfoSum = 0;
		totalSocialSum = 0;
		
		return nodeList;
	}

	/*
	 * Method accepts the node list as input and returns a TreeMap with information probability range as key 
	 * and node id as value.
	 */
	public TreeMap<Double, Integer> updateInfoTreeMap(List<Node> nodeList) {

		TreeMap<Double, Integer> infoTreeMap = new TreeMap<Double, Integer>();

		for (Node n : nodeList) {
			infoTreeMap.put(infoRange, n.getNodeId());
			infoRange = infoRange + n.getInfoProb();
		}
		infoRange = 0.0;
		return infoTreeMap;
	}

	/*
	 * Method accepts the node list as input and returns a TreeMap with social probability range as key 
	 * and node id as value.
	 */
	public TreeMap<Double, Integer> updateSocTreeMap(List<Node> nodeList) {

		TreeMap<Double, Integer> socTreeMap = new TreeMap<Double, Integer>();

		for (Node n : nodeList) {
			socTreeMap.put(socRange, n.getNodeId());
			socRange = socRange + n.getSocProb();
		}
		socRange = 0.0;
		
		return socTreeMap;
	}
	
	
	/*
	 * Method accepts the node list as input and returns a TreeMap with information probability range as key 
	 * and node id as value, for dynamic following of nodes.
	 */
	public TreeMap<Double, Integer> updateInfoOutTreeMap(List<Node> nodeList) {

		TreeMap<Double, Integer> infoOutTreeMap = new TreeMap<Double, Integer>();

		for (Node n : nodeList) {
			infoOutTreeMap.put(infoRange, n.getNodeId());
			infoRange = infoRange + n.getInfoOutProb();
		}
		infoRange = 0.0;
		return infoOutTreeMap;
	}


	
}
