/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

public class TrafficException extends Exception {
	
	private static final long serialVersionUID = 530720052572263134L;
	
	public TrafficException() {}
	
	public TrafficException(String msg) {
		super(msg);
	}
}
