package com.voxcast.model;

import com.google.gson.annotations.SerializedName;

public class PostImageModel {

	@SerializedName("statusCode")
	private int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@SerializedName("id")
	private String id;

}
