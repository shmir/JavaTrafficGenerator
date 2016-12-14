/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

import java.util.HashMap;

/**
 * Helper class to convert from TGN String values to int/boolean.<br>
 */
@Deprecated
public class ConversionTable {
	
	private HashMap<String, Integer> string2int = new HashMap<String, Integer>();
	
	private HashMap<Integer, String> int2string = new HashMap<Integer, String>();
	
	private HashMap<Boolean, String> boolean2string = new HashMap<Boolean, String>();
	
	private HashMap<String, Boolean> string2boolean = new HashMap<String, Boolean>();
	
	public void put(Integer enumVal, String stringVal) {
		int2string.put(enumVal, stringVal);
	}
	
	public void put(Boolean booleanVal, String stringVal) {
		boolean2string.put(booleanVal, stringVal);
		string2boolean.put(stringVal, booleanVal);
	}
	
	public void put(String stringVal, Boolean booleanVal) {
		boolean2string.put(booleanVal, stringVal);
		string2boolean.put(stringVal, booleanVal);
	}
	
	public Integer get(String stringVal) {
		return string2int.get(stringVal);
	}
	
	public String get(Integer intVal) {
		return int2string.get(intVal);
	}
	
	public String get(Boolean intVal) {
		return boolean2string.get(intVal);
	}
	
	public Boolean getBool(String stringVal) {
		return string2boolean.get(stringVal);
	}
	
}
