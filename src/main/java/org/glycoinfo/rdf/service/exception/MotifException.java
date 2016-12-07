package org.glycoinfo.rdf.service.exception;

public class MotifException extends Exception {

	/*
   * 
   * eclipse generated serial id
   * 
   */
  private static final long serialVersionUID = -8669688150635152658L;

  public MotifException() {
	}

	/**
	 * @param message
	 */
	public MotifException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MotifException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MotifException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MotifException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
