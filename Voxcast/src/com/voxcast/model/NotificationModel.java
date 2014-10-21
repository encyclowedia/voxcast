package com.voxcast.model;

import android.graphics.Bitmap;

public class NotificationModel {

	private String imagePath;
	private String notificationMsg;
	private Bitmap imageBitmap;

	public Bitmap getImageBitmap() {
		return imageBitmap;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getNotificationMsg() {
		return notificationMsg;
	}

	public NotificationModel() {
		// TODO Auto-generated constructor stub
	}

}
