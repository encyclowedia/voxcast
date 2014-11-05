package com.voxcast.model;

import com.google.gson.annotations.SerializedName;

import android.graphics.Bitmap;

public class GetUserListModel {

	@SerializedName("statusCode")
	private int statusCode;

	@SerializedName("uId")
	private String uId;
	@SerializedName("usrImg")
	private Bitmap usrImg;
	@SerializedName("name")
	private String name;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public Bitmap getUsrImg() {
		return usrImg;
	}

	public void setUsrImg(Bitmap usrImg) {
		this.usrImg = usrImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
