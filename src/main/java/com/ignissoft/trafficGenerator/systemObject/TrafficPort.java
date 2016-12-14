/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator.systemObject;

import org.apache.commons.lang.NotImplementedException;

import com.ignissoft.trafficGenerator.TgnObject;
import com.ignissoft.trafficGenerator.TrafficException;

/**
 * Base class for all trafic ports.
 */
public class TrafficPort extends TrafficBaseClass implements Cloneable {
	
	private String portName = "";
	
	private String ip = "127.0.0.1";
	
	protected int slot;
	
	protected int port;
	
	protected TgnObject tgnPort = null;
	
	/*
	 * Interface methods.
	 */
	
	/**
	 * Reserve port.
	 * 
	 * @throws TrafficException
	 */
	public void reserve() throws TrafficException {
		throw new NotImplementedException();
	}
	
	/*
	 * Standard Getters and Setters, used also for SUT initialization.
	 */
	
	public int getSlot() {
		return slot;
	}
	
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getPortName() {
		return portName;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public TgnObject getTgnPort() {
		return tgnPort;
	}
	
	public void setTgnPort(TgnObject tgnPort) {
		this.tgnPort = tgnPort;
	}
	
	public void setPortName(String portName) {
		this.portName = portName;
	}
	
}
