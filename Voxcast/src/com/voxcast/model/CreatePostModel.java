package com.voxcast.model;


public class CreatePostModel {
	private String whichTypeImage;
	private String bitmapImageOrVideoUrl;

	public CreatePostModel(String bitmapImageOrVideo, String whichTypeImage) {
		// TODO Auto-generated constructor stub
		this.bitmapImageOrVideoUrl = bitmapImageOrVideo;
		this.whichTypeImage = whichTypeImage;
	}

	public String getUrl() {
		return bitmapImageOrVideoUrl;
	}

	public void setUrl(String bitmap) {
		bitmapImageOrVideoUrl = bitmap;
	}

	public String getwhichTypeImage() {
		return whichTypeImage;
	}

	public void setwhichTypeImage(String whichTypeImage) {
		this.whichTypeImage = whichTypeImage;
	}

}