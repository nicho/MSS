package com.aus.authority.exception;

/**
 * 权限自定义异常
 * 
 * @author duzh
 *
 */
public class AuthorityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AuthorityException() {
        super();
    }
    
    public AuthorityException(String msg) {
        super(msg);
    }
    
    public AuthorityException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public AuthorityException(Throwable cause) {
        super(cause);
    }

}
