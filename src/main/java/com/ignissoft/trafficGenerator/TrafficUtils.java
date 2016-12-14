/**
 * @author yoram@ignissoft.com
 */
package com.ignissoft.trafficGenerator;

/**
 * All sorts of traffic utilities.
 */
public class TrafficUtils {
	
	/**
	 * Test if the given IPv4 address represents localhost or not.
	 * 
	 * @param ip
	 *        IPv4 to test.
	 * @return true - localhost, false - network host.
	 */
	public static boolean isLocalIp(String ip) {
		return ip == null || ip.equals("127.0.0.1") || ip.equals("localhost");
	}
	
}
