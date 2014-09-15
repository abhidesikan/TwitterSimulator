/**
 * 
 */
package com.ub.twittersimulator.node;

import java.util.List;

/**
 * @author abhi
 *
 */
public class NodeStatistics {

	public void printNodeInformation(List<Node> nodeList, int nodeId){
		
		Node n = nodeList.get(nodeId);
		
		System.out.println("User name :"+n.getUserName());
		System.out.println("Followers :"+n.getFollowers());
		System.out.println("Following :"+n.getFollowing());
		System.out.println("Social links :"+n.getSocialCount());
		System.out.println("Informational links :"+n.getInfoCount());
	}
}
