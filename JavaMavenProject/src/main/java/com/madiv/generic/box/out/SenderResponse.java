package com.madiv.generic.box.out;

import java.util.List;

public class SenderResponse {
	private boolean status;
	private List<String> errors;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "{\"status\":\""+status+"\", errors:\""+errors+"\"}";
	}
}
