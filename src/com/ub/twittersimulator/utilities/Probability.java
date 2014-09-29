/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.util.List;

import com.ub.twittersimulator.node.Node;

/**
 * @author abhi
 *
 */
public class Probability {
	
	private static double totalInfoSum = 0;
	private static double totalSocialSum = 0;
	
	public List<Node> updateProbability(List<Node> nodeList){
		
		for(int i=0; i<nodeList.size(); i++){
			totalInfoSum = totalInfoSum + nodeList.get(i).getInfoCount()+nodeList.get(i).getSocialCount();
			totalSocialSum = totalSocialSum + nodeList.get(i).getSocialCount();
		}
		for(Node node: nodeList){
			node.setInfoProb((node.getInfoCount()+node.getSocialCount())/totalInfoSum);
			node.setSocProb(node.getSocialCount()/totalSocialSum);

		}
		
		return nodeList;
	}
}
