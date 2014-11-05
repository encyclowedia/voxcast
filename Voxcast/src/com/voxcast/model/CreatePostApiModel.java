package com.voxcast.model;

import com.google.gson.annotations.SerializedName;

public class CreatePostApiModel {

	@SerializedName("statusCode")
	private int statusCode;
	@SerializedName("postId")
	private String postId;
	@SerializedName("msg")
	private String msg;
	@SerializedName("anon")
	private String anon;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAnon() {
		return anon;
	}

	public void setAnon(String anon) {
		this.anon = anon;
	}

}
