/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

import java.io.File;

import org.apache.commons.lang.NotImplementedException;

import com.ignissoft.java2tcl.ShellCommand;
import com.ignissoft.java2tcl.TclShell;
import com.ignissoft.java2tcl.TclShellLocal;

/**
 * Base class for traffic generator applications (IxNetwork, STC, Avalanche etc...).
 */
public class TgnApp {
	
	private String chassis = null;
	
	private String server = null;
	
	private TclShell shell;
	
	/*
	 * Interfaces methods.
	 */
	
	/**
	 * Initialize the application:<br>
	 * Start low level API (this version supports Tcl only).<br>
	 * 
	 * @param tclExe
	 *        full path to Tcl interpreter.
	 * @param installPath
	 *        full path to TGN client install directory, including version number.
	 * @throws Exception
	 */
	public void init(String tclExe, String installPath) throws Exception {
		startTcl(tclExe);
	}
	
	/**
	 * Connect to chassis/server if needed.
	 * 
	 * @param chassis
	 *        chassis IP address.
	 * @param server
	 *        server IP address.
	 * @throws Exception
	 */
	public void connect(String chassis, String server) throws Exception {
		setChassis(chassis);
		setServer(server);
	}
	
	/**
	 * Disconnect from chassis.<br>
	 * Call disconnect to reload a configuration and start a new test without restarting the low level API.
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception {}
	
	/**
	 * Exit the application - close low level API and exit.
	 * 
	 * @throws TrafficException
	 */
	public void exit() throws Exception {
		disconnect();
		if (getShell() != null) {
			getShell().exit();
		}
	}
	
	/**
	 * Load TGN configuration file.
	 * 
	 * @param fileName
	 *        full path to configuration file.
	 * @throws TrafficException
	 */
	public void loadConfigFile(String fileName) throws Exception {
		throw new NotImplementedException();
	}
	
	/**
	 * Save current configuration to disk.
	 * 
	 * @param fileName
	 *        full path to configuration file name.
	 * @throws TrafficException
	 */
	public void saveConfigFile(String fileName) throws TrafficException {
		throw new NotImplementedException();
	}
	
	/*
	 * Low level API (currently Tcl only).
	 */
	
	/**
	 * Launch Tcl interpreter and load all packages/procs required to run the traffic generator API.
	 */
	public void startTcl(String tclExe) throws Exception {
		// Launch Tcl interpreter
		setShell(new TclShellLocal(new File(tclExe)));
		getShell().init();
		getShell().setLogFile("c:/temp/tgnLog.tcl");
		getShell().setTimeout(60000 * 4);
		getShell().launch();
		
		// Set shell for all phy objects.
		TgnObject.setShell(getShell());
	}
	
	/**
	 * Execute API command.
	 * 
	 * @param cmd
	 *        the command to execute
	 * @param msg
	 *        a message to display after command is executed
	 * @return The return value from the Tcl command
	 * @throws TrafficException
	 */
	public String handelShellCommand(ShellCommand cmd, String msg) throws TrafficException {
		getShell().executeCommand(cmd);
		if (cmd.isFail()) {
			throw new TrafficException(msg + cmd.getErrorString());
		}
		return cmd.getReturnValue();
	}
	
	/**
	 * Execute API command.
	 * 
	 * @param cmd
	 *        the command to execute.
	 * @return The return value from the Tcl command
	 * @throws TrafficException
	 */
	public String handelShellCommand(ShellCommand cmd) throws TrafficException {
		return handelShellCommand(cmd, "");
	}
	
	/**
	 * Source Tcl script.
	 * 
	 * @param script
	 *        full path to script.
	 * @throws Exception
	 */
	protected void sourceScript(String script) throws Exception {
		getShell().source(this.getClass().getClassLoader().getResourceAsStream(script));
	}
	
	/**
	 * Build DB of major objects in the configuration - ports, devices and streams for faster access and easier use during the test.
	 */
	public void readObjectNames() throws Exception {
		throw new NotImplementedException();
	}
	
	public void printObjects() throws Exception {
		boolean printCommand = getShell().isPrintCommand();
		boolean printReturn = getShell().isPrintReturn();
		getShell().setPrintCommand(false);
		getShell().setPrintReturn(false);
		for (TgnObject object : TgnObject.getTgnInstances()) {
			System.out.println(object.getName() + " = " + object.getHandle() + " ; " + object);
		}
		getShell().setPrintCommand(printCommand);
		getShell().setPrintReturn(printReturn);
	}
	
	/*
	 * Standard Getters and Setters
	 */
	
	public String getChassis() {
		return chassis;
	}
	
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	
	public TclShell getShell() {
		return shell;
	}
	
	public void setShell(TclShell shell) {
		this.shell = shell;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
}