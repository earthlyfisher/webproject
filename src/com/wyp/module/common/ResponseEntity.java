package com.wyp.module.common;

public class ResponseEntity {

	private String resCode;

	private Object value;

	private String errMsg;

	public ResponseEntity() {
		super();
	}

	public ResponseEntity(String resCode, String errMsg) {
		super();
		this.resCode = resCode;
		this.errMsg = errMsg;
	}

	public ResponseEntity(String resCode, Object value, String errMsg) {
		super();
		this.resCode = resCode;
		this.value = value;
		this.errMsg = errMsg;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "ResponseEntity [resCode=" + resCode + ", value=" + value + ", errMsg=" + errMsg + "]";
	}

}
