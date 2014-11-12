package com.voxcast.model;

public class LoginResponse  {

private Integer statusCode;

private LoginResponseBean result;

/**
* 
* @return
* The statusCode
*/
public Integer getStatusCode() {
return statusCode;
}

/**
* 
* @param statusCode
* The statusCode
*/
public void setStatusCode(Integer statusCode) {
this.statusCode = statusCode;
}

/**
* 
* @return
* The result
*/
public LoginResponseBean getResult() {
return result;
}

/**
* 
* @param result
* The result
*/
public void setResult(LoginResponseBean result) {
this.result = result;
}

}