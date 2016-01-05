package cn.thinkjoy.common.mybatis.core.mybatis.support.resp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FacadeResp<T> implements Serializable {

	private boolean success;
	private String successMsg;
	private String errorCode;
	private String errorMsg;
	private T bizData;

	public FacadeResp() {
		this(false, null, null);
	}

	public FacadeResp(boolean success) {
		this(success, null, null);
	}

	public FacadeResp(String errorMsg) {
		this(false, errorMsg, null);
	}

	public FacadeResp(String errorCode, String errorMsg) {
		this(false, errorMsg, null);
		this.errorCode = errorCode;
	}

	public FacadeResp(T userInfo) {
		this(true, null, userInfo);
	}

	public FacadeResp(boolean success, String errorMsg, T bizData) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.bizData = bizData;
	}

	public boolean isSuccess() {
		return success;
	}

	public FacadeResp<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public FacadeResp<T> setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this;
	}

	public T getBizData() {
		return bizData;
	}

	public FacadeResp<T> setBizData(T bizData) {
		this.bizData = bizData;
		return this;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public FacadeResp<T> setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public FacadeResp<T> setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
		return this;
	}

}
