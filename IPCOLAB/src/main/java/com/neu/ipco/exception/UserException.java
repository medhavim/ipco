/**
 * 
 */
package com.neu.ipco.exception;

/**
 * @author harsh
 *
 */
public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8188696816147955110L;

	/**
	 * 
	 */
	public UserException() {
	}

	/**
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
