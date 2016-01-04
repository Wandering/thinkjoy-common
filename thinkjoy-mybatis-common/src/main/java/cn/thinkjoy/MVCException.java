package cn.thinkjoy;

/**
 * MVC基层异常
 * 
 * @author shadow
 * 
 */
@SuppressWarnings("serial")
public class MVCException extends Exception {

	private String errorCode;

	public MVCException() {
		super();
	}

	public MVCException(Throwable e) {
		super(e);
	}

	public MVCException(String msg) {
		super(msg);
	}

	public MVCException(String msg, Throwable e) {
		super(msg, e);
	}

	public MVCException(String msg, String errorCode) {
		super(msg);
		setErrorCode(errorCode);
	}

	public MVCException(String msg, Throwable e, String errorCode) {
		super(msg, e);
		setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
