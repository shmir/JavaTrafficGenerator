/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator.systemObject;

import java.util.HashMap;

import org.apache.commons.lang.NotImplementedException;

import com.ignissoft.trafficGenerator.TrafficException;

/**
 * Base class for all traffic generators system objects.
 */
public class TrafficGenerator extends TrafficBaseClass {
	
	private String tclExe = null;
	
	private String installPath = null;
	
	private String runFiles = "c:/jsystemTraffic";
	
	private String chassis = null;
	
	private String server = null;
	
	public TrafficPort[] ports;
	
	@Override
	public void init() throws Exception {
		
		super.init();
		setPrintStatuses(true);
		
		/*
		 * Set system object minimum close timeout to 2 seconds since generator system object is responsible to close all ports and Tcl
		 * shell. In case there are many ports or delays in the network this process might take more than 1 second which is the default
		 * timeout.
		 */
		setExitTimeout(2000);
		
		report("Connect to traffic generator: " + getChassis());
		try {
			getTraffic().init(getTclExe(), getInstallPath());
			getTraffic().connect(getChassis(), getServer());
		} catch (Throwable e) {
			if (getTraffic().getShell() != null) {
				getTraffic().getShell().exit();
				getTraffic().getShell().close();
			}
			throw new TrafficException(e.getMessage());
		}
		
	}
	
	@Override
	public void close() {
		report("Exit traffic generator: " + getChassis());
		try {
			getTraffic().exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
	}
	
	public void loadConfigFile(String fileName) throws Exception {
		throw new NotImplementedException();
	}
	
	/**
	 * Get map of <name, object> of all traffic ports.
	 * 
	 * @return map of traffic ports.
	 */
	public <T extends HashMap<String, TrafficPort>> HashMap<String, TrafficPort> getPorts() {
		HashMap<String, TrafficPort> portsMap = new HashMap<String, TrafficPort>();
		for (int i = 0; i < ports.length; i++) {
			portsMap.put(ports[i].getPortName(), ports[i]);
		}
		return portsMap;
	}
	
	/*
	 * Standard Getters and Setters, used also for SUT initialization.
	 */
	
	public void setPorts(TrafficPort[] ports) {
		this.ports = ports;
	}
	
	public String getTclExe() {
		return tclExe;
	}
	
	public void setTclExe(String tclExe) {
		this.tclExe = tclExe;
	}
	
	public String getInstallPath() {
		return installPath;
	}
	
	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}
	
	public String getRunFiles() {
		return runFiles;
	}
	
	public void setRunFiles(String runFiles) {
		this.runFiles = runFiles;
	}
	
	public String getChassis() {
		return chassis;
	}
	
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
}
