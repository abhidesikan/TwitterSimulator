/**
 * 
 */
package com.ub.twittersimulator.node;

import java.util.List;

/**
 * @author abhi
 *
 */

/*
 * Class contains method to print node information.
 */
public class NodeStatistics {

	/*
	 * Method accepts the node list and node id as input parameter and displays the node information
	 * for that particular node id.
	 */
	public void printNodeInformation(List<Node> nodeList, int nodeId){
		
		Node n = nodeList.get(nodeId);
		
		System.out.println("User name :"+n.getUserName());
		System.out.println("Followers :"+n.getFollowers());
		System.out.println("Following :"+n.getFollowing());
		System.out.println("Social links :"+n.getSocialCount());
		System.out.println("Informational links :"+n.getInfoCount());
		System.out.println("Info Probability :"+n.getInfoProb());
		System.out.println("Social Probability :"+n.getSocProb());
		System.out.println("Closure count "+n.getClosureCount());
	}
}
