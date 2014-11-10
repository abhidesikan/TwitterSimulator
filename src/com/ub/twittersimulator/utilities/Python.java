/**
 * 
 */
package com.ub.twittersimulator.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
		String items = "";
		try{
			File file2 = new File(
					"/home/abhi/workspaceLuna/TwitterSimulator/Arguments");
			if (!file2.exists()) {
				file2.createNewFile();
			}
			FileWriter fw = new FileWriter(file2.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[");
			for (Node node : nodeList) {			
				items = items + (node.getFollowers().size() + ",");
				
			}
			bw.write(items);
			bw.write("]");
			bw.close();
			fw.close();

			
		}catch(IOException e){
			e.printStackTrace();
		}

	

		String line = null;
		int gamma = 0;

		try {
			Process p = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"/Scripts/twitter.py /home/abhi/workspaceLuna/TwitterSimulator/Arguments");
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
