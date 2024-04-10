package com.pp.advice;

import java.time.LocalDateTime;

public class ErrorDetails {
public String statusCode;
public String message;
public LocalDateTime whem;
public ErrorDetails(String statusCode, String message, LocalDateTime whem) {
	super();
	this.statusCode = statusCode;
	this.message = message;
	this.whem = whem;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LocalDateTime getWhem() {
	return whem;
}
public void setWhem(LocalDateTime whem) {
	this.whem = whem;
}


}
