package com.voxcast.model;

import com.google.gson.annotations.SerializedName;

import android.graphics.Bitmap;

public class EditProfileModel {

	@SerializedName("statusCode")
	private int statusCode;
	@SerializedName("name")
	private String name;
	@SerializedName("img")
	private Bitmap img;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setImg(Bitmap img) {
		this.img = img;
	}

}
