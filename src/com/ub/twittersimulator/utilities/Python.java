/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.ub.twittersimulator.node.Node;

/**
 * @author abhi
 *
 */

/*
 * Class containing method to calculate gamma (Scaling parameter) value.
 */
public class Python {

	/*
	 * Method executes python script and prints gamma value. Accepts node list
	 * as input parameter.
	 */
	public void getGammaValue(List<Node> nodeList) {

		String items = "[";
		for (Node node : nodeList) {
			items = items + (node.getFollowers().size() + ",");
		}
		items = items + "]";
		String line = null;
		int gamma = 0;

		try {
			Process p = Runtime.getRuntime().exec("python twitter.py " + items);
			BufferedReader bri = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
		} catch (NumberFormatException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
