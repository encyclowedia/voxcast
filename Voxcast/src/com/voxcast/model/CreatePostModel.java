package com.voxcast.model;

import android.graphics.Bitmap;

public class CreatePostModel {
	private String whichTypeImage;
	private Bitmap bitmapImageOrVideo;

	public CreatePostModel(Bitmap bitmapImageOrVideo, String whichTypeImage) {
		// TODO Auto-generated constructor stub
		this.bitmapImageOrVideo = bitmapImageOrVideo;
		this.whichTypeImage = whichTypeImage;
	}

	public Bitmap getBitmap() {
		return bitmapImageOrVideo;
	}

	public void setBitmap(Bitmap bitmap) {
		bitmapImageOrVideo = bitmap;
	}

	public String getwhichTypeImage() {
		return whichTypeImage;
	}

	public void setwhichTypeImage(String whichTypeImage) {
		this.whichTypeImage = whichTypeImage;
	}

}