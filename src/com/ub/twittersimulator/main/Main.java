/**
 * 
 */
package com.ub.twittersimulator.main;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.ub.twittersimulator.node.NetworkGenerator;
import com.ub.twittersimulator.node.Node;
import com.ub.twittersimulator.node.NodeStatistics;
import com.ub.twittersimulator.utilities.Python;

/**
 * @author abhi
 *
 */

/*
 * Main class. Entry point of the program.
 */
public class Main {

	/*
	 * Main method. Accepts input arguments and calls methods in NetworkGenerator and NodeStatistics class.
	 */
	public static void main(String args[]){
		
		
		Scanner in = new Scanner(System.in);
		
		int networkSize = 0;
		int initialNetworkSize = 0;
		int links = 0;
		int nodeId = 0;
		double ratio = 0;
		int gamma = 0;
		boolean flag = false;
		String response = null;
		HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		
		try{
			
			System.out.println("Enter network size: ");
			networkSize = in.nextInt();
						
			System.out.println("Enter initial network size: ");
			initialNetworkSize = in.nextInt();
			
			System.out.println("Enter number of links to be formed: ");
			links = in.nextInt();
			
			System.out.println("Enter the social ratio: ");
			ratio = in.nextDouble();
			
			System.out.println("Do you want a static/dynamic network? (S/D)");
			response = in.next();
			if(response.equalsIgnoreCase("d")){
				flag = true;
			}
			Calendar cal = Calendar.getInstance();
			
			System.out.println("Start time "+cal.getTime());
			
			NetworkGenerator netGen = new NetworkGenerator();
			nodeMap = netGen.createNodes(networkSize, initialNetworkSize, links, ratio, flag);
			
			
			Python python = new Python();
	//		python.getGammaValue(nodeMap);
			
			System.out.println("Network of size "+networkSize+" is generated. \n");
			System.out.println("End time "+ cal.getTime());
			while(true){
				System.out.println("Enter node id to retrieve node statistics: ");
				nodeId = in.nextInt();
				
				NodeStatistics stats = new NodeStatistics();
				stats.printNodeInformation(nodeMap, nodeId);
				
				System.out.println("Do you want to exit the program? (Y/N)");
				response = in.next();
				if(response.equalsIgnoreCase("y")){
					break;
				}
			}
	
			
		}
		catch(Exception e){
			System.out.println("Incorrect input");
			e.printStackTrace();
		}
	
	}
}
