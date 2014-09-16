/**
 * 
 */
package com.ub.twittersimulator.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import com.ub.twittersimulator.utilities.CompareMapValues;

/**
 * @author abhi
 *
 */

/*
 * Class contains methods to create Twitter network based on input parameters.
 */
public class NetworkGenerator {

	/*
	 * Method accepts network size, initial network size, number of links to be formed by each node and 
	 * social ratio as input parameters. It calls createInitialNetwork method to create initial network
	 * and then adds each incoming node to the network, forming links based on preferential attachment
	 * model. Returns list of nodes populated with their respective node information.
	 */
	public List<Node> createNodes(Integer networkSize, Integer initialNetworkSize, int links, int ratio){
		
		List <Node> nodeList = createInitialNetwork(initialNetworkSize);

		int infoLinks = (2*links)/ratio; 
		int socialLinks = (links)/ratio;
		Random random = new Random();
		
		for(int i=initialNetworkSize; i<networkSize; i++){
			
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			SortedSet<Entry<Integer, Integer>> sortedMap = null;
			int [] nodeIdList = new int[links];
			Node node = new Node();
			int nodeId = 0, k=0;
			
			for(int j=0; j<links; j++){
				 nodeId = nodeList.get(random.nextInt(nodeList.size())).getNodeId();
				 if(map.containsKey(nodeId)){
					 j--;
					 continue;
				 }
				 map.put(nodeId, nodeList.get(nodeId).getInfoCount()+nodeList.get(nodeId).getSocialCount());
			}
			
			sortedMap = CompareMapValues.entriesSortedByValues(map);
			
			Iterator it = sortedMap.iterator();
			while(it.hasNext()){
				Map.Entry<Integer,Integer> pairs = (Map.Entry<Integer, Integer>)it.next();
				nodeIdList[k] = pairs.getKey();
				k++;
			}
			
			node.setNodeId(i);
			node.setUserName("User "+i);
			node.setHandle("@user"+i);
			nodeList.add(node);
			
			for(int m = 0; m<socialLinks; m++){
				node = nodeList.get(nodeIdList[m]);
				nodeList.get(i).getFollowing().add(node.getNodeId());
				nodeList.get(i).getFollowers().add(node.getNodeId());
				nodeList.get(i).setSocialCount(nodeList.get(i).getSocialCount()+1);

				node.getFollowers().add(i);
				node.getFollowing().add(i);
				node.setSocialCount(node.getSocialCount()+1);
			}
			
			for(int l=socialLinks; l<links; l++){
				node = nodeList.get(nodeIdList[l]);
				nodeList.get(i).getFollowing().add(node.getNodeId());
				nodeList.get(i).setInfoCount(nodeList.get(i).getInfoCount()+1);

				
				node.getFollowers().add(i);
				node.setInfoCount(node.getInfoCount()+1);
			}			
		}

		return nodeList;
		
	}
	
	/*
	 * Method accepts initial network size as input parameter. Creates a fully connected social network
	 * for the given input network size. Returns the list of nodes populated with respective node
	 * information.
	 */
	public List<Node> createInitialNetwork(Integer initialNetworkSize){
		
		List<Node> nodeList = new ArrayList<>();
		
		for(int i=0; i<initialNetworkSize; i++){
			Node node = new Node();
			node.setNodeId(i);
			node.setUserName("User "+i);
			node.setHandle("@user"+i);
			nodeList.add(node);
		}
		
		for(Node node: nodeList){
			for(int i=0; i<initialNetworkSize; i++){
				if(i!=node.getNodeId()){
					node.getFollowers().add(i);
					node.getFollowing().add(i);
					node.setSocialCount(node.getSocialCount()+1);
				}
			}
		}		

		return nodeList;
	}
}
