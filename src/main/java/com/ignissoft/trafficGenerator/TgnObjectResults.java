/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

import java.util.ArrayList;

public class TgnObjectResults {
	
	private ArrayList<String> captions;
	
	private ArrayList<String> results;
	
	public TgnObjectResults(ArrayList<String> captions, ArrayList<String> results) throws TrafficException {
		this.captions = captions;
		this.results = results;
	}
	
	public ArrayList<String> getResults() {
		return results;
	}
	
	public String getResult(String name) {
		return results.get(captions.indexOf(name));
	}
	
	public double getCounter(String name) {
		return Double.parseDouble(getResult(name));
	}
	
}
