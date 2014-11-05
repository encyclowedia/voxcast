package com.voxcast.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class GetProfileModel {

	@SerializedName("statusCode")
	private int statusCode;
	private ArrayList<GetUserList> getUserList;
	private ArrayList<GetPostList> getPostList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ArrayList<GetUserList> getGetUserList() {
		return getUserList;
	}

	public void setGetUserList(ArrayList<GetUserList> getUserList) {
		this.getUserList = getUserList;
	}

	public ArrayList<GetPostList> getGetPostList() {
		return getPostList;
	}

	public void setGetPostList(ArrayList<GetPostList> getPostList) {
		this.getPostList = getPostList;
	}

	public class GetUserList {
		@SerializedName("uId")
		private String uId;
		@SerializedName("usrImg")
		private String usrImg;
		@SerializedName("name")
		private String name;
		@SerializedName("msgCnt")
		private int msgCnt;
		@SerializedName("upCnt")
		private int upCnt;
		@SerializedName("downCnt")
		private int downCnt;

		public String getuId() {
			return uId;
		}

		public void setuId(String uId) {
			this.uId = uId;
		}

		public String getUsrImg() {
			return usrImg;
		}

		public void setUsrImg(String usrImg) {
			this.usrImg = usrImg;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getMsgCnt() {
			return msgCnt;
		}

		public void setMsgCnt(int msgCnt) {
			this.msgCnt = msgCnt;
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

	}

	public class GetPostList {
		@SerializedName("pid")
		private String pid;
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

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
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
