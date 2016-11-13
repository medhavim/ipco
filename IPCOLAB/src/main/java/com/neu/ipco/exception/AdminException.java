/**
 * 
 */
package com.neu.ipco.exception;

/**
 * @author Harsha
 *
 */
public class AdminException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6133806266604753667L;

	/**
	 * 
	 */
	public AdminException() {
	}

	/**
	 * @param arg0
	 */
	public AdminException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public AdminException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AdminException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public AdminException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
