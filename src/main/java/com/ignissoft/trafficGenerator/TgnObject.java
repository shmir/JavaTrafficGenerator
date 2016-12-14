/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.NotImplementedException;

import com.ignissoft.java2tcl.ShellCommand;
import com.ignissoft.java2tcl.TclShell;

/**
 * Base class for all traffic generators objects.
 */
public class TgnObject {
	
	static protected TclShell shell;
	
	protected String object = null;
	
	protected TgnObject parent = null;
	
	protected String handle = null;
	
	protected HashMap<String, TgnObject> name2object = new HashMap<String, TgnObject>();
	
	private static ArrayList<TgnObject> tgnInstances = new ArrayList<TgnObject>();
	
	/**
	 * Default constructor.
	 */
	public TgnObject() {}
	
	/**
	 * Create new object with known handle to the system (without going to the chassis).
	 * 
	 * @param object
	 *        object type
	 * @param parent
	 *        parent object
	 * @param handle
	 *        object handler
	 * @throws TrafficException
	 */
	public TgnObject(String object, TgnObject parent, String handle) throws TrafficException {
		this.object = object;
		this.parent = parent;
		this.handle = handle;
		TgnObject.getTgnInstances().add(this);
	}
	
	/*
	 * Low level API (currently Tcl only).
	 */
	
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
	protected static String handelShellCommand(ShellCommand cmd, String msg) throws TrafficException {
		shell.executeCommand(cmd);
		if (cmd.isFail()) {
			throw new TrafficException(msg + cmd.getErrorString().split("[\\n\\r]")[0]);
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
	protected static String handelShellCommand(ShellCommand cmd) throws TrafficException {
		return handelShellCommand(cmd, "");
	}
	
	/**
	 * Build Tcl attributes string.
	 * 
	 * @param properties
	 *        hash map of attributes.
	 * @return Tcl string of attributes.
	 */
	public static Object[] buildPropertiesString(HashMap<String, Object> properties) {
		ArrayList<String> propertiesS = new ArrayList<String>();
		for (String key : properties.keySet()) {
			propertiesS.add("-" + key);
			propertiesS.add(properties.get(key).toString());
		}
		return propertiesS.toArray();
	}
	
	/*
	 * Utilities.
	 */
	
	/**
	 * Simple debug tool that prints all object children to the console.
	 */
	public void printName2object() {
		System.out.println("\n");
		for (String key : name2object.keySet()) {
			System.out.println(key + " = " + name2object.get(key).getHandle() + " ; " + name2object.get(key));
		}
	}
	
	/*
	 * Interface implementation.
	 */
	
	public String getName() throws TrafficException {
		throw new NotImplementedException();
	}
	
	public void deleteFromChassis() throws TrafficException {
		throw new NotImplementedException();
	}
	
	/*
	 * Standard Getters and Setters.
	 */
	
	public static TclShell getShell() {
		return TgnObject.shell;
	}
	
	public static void setShell(TclShell shell) {
		TgnObject.shell = shell;
	}
	
	public TgnObject getParent() {
		return parent;
	}
	
	public HashMap<String, TgnObject> getName2object() {
		return name2object;
	}
	
	public static ArrayList<TgnObject> getTgnInstances() {
		return tgnInstances;
	}
	
	public String getObject() {
		return object;
	}
	
	public String getHandle() {
		return handle;
	}
	
}
