package com.voxcast.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import android.graphics.Bitmap;

public class GetPostModel {

	@SerializedName("statusCode")
	private int statusCode;
	@SerializedName("cityImg")
	private Bitmap cityImg;

	private ArrayList<GetPostList> getPostList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Bitmap getCityImg() {
		return cityImg;
	}

	public void setCityImg(Bitmap cityImg) {
		this.cityImg = cityImg;
	}

	public ArrayList<GetPostList> getGetPostList() {
		return getPostList;
	}

	public void setGetPostList(ArrayList<GetPostList> getPostList) {
		this.getPostList = getPostList;
	}

	public class GetPostList {

		@SerializedName("uid")
		private String uid;
		@SerializedName("pid")
		private String pid;
		@SerializedName("usrImg")
		private Bitmap usrImg;
		@SerializedName("name")
		private String name;
		@SerializedName("msg")
		private String msg;
		@SerializedName("city")
		private String city;
		@SerializedName("upCnt")
		private int upCnt;
		@SerializedName("downCnt")
		private int downCnt;
		@SerializedName("comCnt")
		private int comCnt;
		@SerializedName("ts")
		private long ts;
		@SerializedName("imgIds")
		private ArrayList<String> imgIds;
		@SerializedName("vidId")
		private String vidId;
		@SerializedName("comments")
		private String[] comments;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
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

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getUpCnt() {
			return upCnt;
		}

		public void setUpCnt(int upCnt) {
			this.upCnt = upCnt;
		}

		public int getDownCnt() {
			return downCnt;
		}

		public void setDownCnt(int downCnt) {
			this.downCnt = downCnt;
		}

		public int getComCnt() {
			return comCnt;
		}

		public void setComCnt(int comCnt) {
			this.comCnt = comCnt;
		}

		public long getTs() {
			return ts;
		}

		public void setTs(long ts) {
			this.ts = ts;
		}

		public ArrayList<String> getImgIds() {
			return imgIds;
		}

		public void setImgIds(ArrayList<String> imgIds) {
			this.imgIds = imgIds;
		}

		public String getVidId() {
			return vidId;
		}

		public void setVidId(String vidId) {
			this.vidId = vidId;
		}

		public String[] getComments() {
			return comments;
		}

		public void setComments(String[] comments) {
			this.comments = comments;
		}

	}

}
