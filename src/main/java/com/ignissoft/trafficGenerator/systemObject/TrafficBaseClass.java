/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator.systemObject;

import com.ignissoft.trafficGenerator.TgnApp;

import jsystem.framework.system.SystemObjectImpl;

/**
 * Base class for all traffic system object classes.<br>
 */
public class TrafficBaseClass extends SystemObjectImpl {
	
	private static TgnApp traffic;
	
	/*
	 * Standard Getters and Setters.
	 */
	
	public TgnApp getTraffic() {
		return traffic;
	}
	
	public static void setTraffic(TgnApp traffic) {
		TrafficBaseClass.traffic = traffic;
	}
	
}
