package com.voxcast.model;

import android.graphics.Bitmap;

public class DownvotesModel {

	private String imagePath;
	private String downvoteMsg;
	private Bitmap imageBitmap;

	public Bitmap getImageBitmap() {
		return imageBitmap;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getNotificationMsg() {
		return downvoteMsg;
	}
}
