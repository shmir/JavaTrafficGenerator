/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator.systemObject;

import jsystem.framework.system.SystemObjectImpl;

import com.ignissoft.trafficGenerator.TgnApp;
import com.ignissoft.trafficGenerator.TgnObject;

/**
 * Base class for all traffic system object classes.<br>
 */
public class TrafficBaseClass extends SystemObjectImpl {
	
	private static TgnApp traffic;
	
	protected TgnObject tgnObject = null;
	
	/*
	 * Standard Getters and Setters.
	 */
	
	public TgnApp getTraffic() {
		return traffic;
	}
	
	public static void setTraffic(TgnApp traffic) {
		TrafficBaseClass.traffic = traffic;
	}

	public TgnObject getTgnObject() {
		return tgnObject;
	}
	
}
