package com.voxcast.model;

import android.graphics.Bitmap;

public class CreatePostModel {
	private String Type;
	private Bitmap Bitmap;

	public CreatePostModel(Bitmap photo, String type) {
		// TODO Auto-generated constructor stub
		Bitmap = photo;
		Type = type;
	}

	public Bitmap getBitmap() {
		return Bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		Bitmap = bitmap;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

}