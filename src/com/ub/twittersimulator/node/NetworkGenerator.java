/**
 * 
 */
package com.ub.twittersimulator.node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.ub.twittersimulator.utilities.CheckValues;
import com.ub.twittersimulator.utilities.Probability;

/**
 * @author abhi
 *
 */

/*
 * Class contains methods to create Twitter network based on input parameters.
 */
public class NetworkGenerator {

	/*
	 * Method accepts network size, initial network size, number of links to be
	 * formed by each node and social ratio as input parameters. It calls
	 * createInitialNetwork method to create initial network and then adds each
	 * incoming node to the network, forming links based on preferential
	 * attachment model. Returns list of nodes populated with their respective
	 * node information.
	 */
	public List<Node> createNodes(Integer networkSize,
			Integer initialNetworkSize, int links, double ratio, boolean flag) {

		List<Node> nodeList = createInitialNetwork(initialNetworkSize);
		TreeMap<Double, Integer> infoTreeMap = null;
		TreeMap<Double, Integer> socTreeMap = null;
		TreeMap<Double, Integer> infoOutTreeMap = null;
		
		int infoLinks = (int)(links*(1-ratio));
		int socialLinks = (int)(links*ratio);
		Probability prob = new Probability();

		for (int i = initialNetworkSize; i < networkSize; i++) {

			List<Integer> selected = new ArrayList<Integer>();

			nodeList = prob.updateProbability(nodeList);
			infoTreeMap = prob.updateInfoTreeMap((nodeList));
			socTreeMap = prob.updateSocTreeMap(nodeList);


			Node node = new Node();
			Node newNode = new Node();
			int j = 0, k = 0;

			Random rand = new Random();
			newNode.setNodeId(i);
			newNode.setUserName("User " + i);
			newNode.setHandle("@user" + i);

			for (j = 0; j < infoLinks; j++) {
				Double pickedNumber = rand.nextDouble();
				node = nodeList.get(CheckValues.mappedValue(infoTreeMap,
						pickedNumber));
				if (selected.contains(node.getNodeId())) {
					j--;
					continue;
				}
				selected.add(node.getNodeId());

				newNode.getFollowing().add(node.getNodeId());
				newNode.setInfoOutCount(newNode.getInfoOutCount()+1);
				node.getFollowers().add(i);
				node.setInfoCount(node.getInfoCount() + 1);

			}

			for (k = 0; k < socialLinks; k++) {
				Double pickedNumber = rand.nextDouble();
				node = nodeList.get(CheckValues.mappedValue(socTreeMap,
						pickedNumber));

				if (selected.contains(node.getNodeId())) {
					k--;
					continue;
				}
				selected.add(node.getNodeId());

				node.getFollowers().add(i);
				node.getFollowing().add(i);
				node.setSocialCount(node.getSocialCount() + 1);
				newNode.getFollowing().add(node.getNodeId());
				newNode.getFollowers().add(node.getNodeId());
				newNode.setSocialCount(newNode.getSocialCount() + 1);

			}
			nodeList.add(newNode);
			
			if(flag){
				nodeList = prob.updateOutProbability(nodeList);
				infoOutTreeMap = prob.updateInfoOutTreeMap(nodeList);

				Node node1 = new Node();
				Node newNode1 = new Node();
				Double pickedNumber = rand.nextDouble();
				newNode1 = nodeList.get(CheckValues.mappedValue(infoOutTreeMap, pickedNumber));				
				
				
				for (j = 0; j < infoLinks; j++) {
					Double pNumber = rand.nextDouble();
					node1 = nodeList.get(CheckValues.mappedValue(infoTreeMap,
							pNumber));
					if (newNode1.getFollowing().contains(node1.getNodeId())) {
						continue;
					}
					CheckValues.checkClosure(newNode1, node1.getNodeId(), nodeList);
					
					newNode1.getFollowing().add(node1.getNodeId());
					newNode1.setInfoOutCount(newNode1.getInfoOutCount()+1);
					node1.getFollowers().add(newNode1.getNodeId());
					node1.setInfoCount(node1.getInfoCount() + 1);

				}

				for (k = 0; k < socialLinks; k++) {
					Double pNumber = rand.nextDouble();
					node1 = nodeList.get(CheckValues.mappedValue(socTreeMap,
							pNumber));

					if (newNode1.getFollowing().contains(node1.getNodeId())) {
						continue;
					}

					CheckValues.checkClosure(newNode1, node1.getNodeId(), nodeList);

					node1.getFollowers().add(newNode1.getNodeId());
					node1.getFollowing().add(newNode1.getNodeId());
					node1.setSocialCount(node1.getSocialCount() + 1);
					newNode1.getFollowing().add(node1.getNodeId());
					newNode1.getFollowers().add(node1.getNodeId());
					newNode1.setSocialCount(newNode1.getSocialCount() + 1);

				}
			}
		}
		
		try {
			File file = new File(
					"/home/abhi/workspaceLuna/TwitterSimulator/NodeDetails2");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Node id" + "  " + "Followers" + "\n");
			for (Node node : nodeList) {
				bw.write(node.getNodeId() + "  " + node.getFollowers().size()+ "\n");
			}

			bw.close();
			fw.close();
		} catch (Exception e) {

		}

		return nodeList;

	}

	/*
	 * Method accepts initial network size as input parameter. Creates a fully
	 * connected social network for the given input network size. Returns the
	 * list of nodes populated with respective node information.
	 */
	public List<Node> createInitialNetwork(Integer initialNetworkSize) {

		List<Node> nodeList = new ArrayList<>();

		for (int i = 0; i < initialNetworkSize; i++) {
			Node node = new Node();
			node.setNodeId(i);
			node.setUserName("User " + i);
			node.setHandle("@user" + i);
			nodeList.add(node);
		}

		for (Node node : nodeList) {
			for (int i = 0; i < initialNetworkSize; i++) {
				if (i != node.getNodeId()) {
					node.getFollowers().add(i);
					node.getFollowing().add(i);
					node.setSocialCount(node.getSocialCount() + 1);
				}
			}
		}

		

		return nodeList;
	}
}
