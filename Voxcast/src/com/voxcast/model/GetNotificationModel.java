package com.voxcast.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import android.graphics.Bitmap;

public class GetNotificationModel {

	@SerializedName("statusCode")
	private int statusCode;
	@SerializedName("cnt")
	private int cnt;
	private ArrayList<NotificationList> notificationList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ArrayList<NotificationList> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(ArrayList<NotificationList> notificationList) {
		this.notificationList = notificationList;
	}

	public class NotificationList {
		@SerializedName("uid")
		private int uid;
		@SerializedName("usrImg")
		private Bitmap usrImg;
		@SerializedName("msg")
		private String msg;

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		public Bitmap getUsrImg() {
			return usrImg;
		}

		public void setUsrImg(Bitmap usrImg) {
			this.usrImg = usrImg;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

}
