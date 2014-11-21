package com.voxcast.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Poster implements Parcelable {

	private String uId;
	private String usrImg;
	private String name;

	public Poster(String uId, String usrImg, String name) {
		this.uId = uId;
		this.usrImg = usrImg;
		this.name = name;
	}

	/**
	 * 
	 * @return The uId
	 */
	public String getUId() {
		return uId;
	}

	/**
	 * 
	 * @param uId
	 *            The uId
	 */
	public void setUId(String uId) {
		this.uId = uId;
	}

	/**
	 * 
	 * @return The usrImg
	 */
	public String getUsrImg() {
		return usrImg;
	}

	/**
	 * 
	 * @param usrImg
	 *            The usrImg
	 */
	public void setUsrImg(String usrImg) {
		this.usrImg = usrImg;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	protected Poster(Parcel in) {
		uId = in.readString();
		usrImg = in.readString();
		name = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(uId);
		dest.writeString(usrImg);
		dest.writeString(name);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Poster> CREATOR = new Parcelable.Creator<Poster>() {
		@Override
		public Poster createFromParcel(Parcel in) {
			return new Poster(in);
		}

		@Override
		public Poster[] newArray(int size) {
			return new Poster[size];
		}
	};

	@Override
	public boolean equals(Object o) {
		return this.uId.equalsIgnoreCase(((Poster) o).uId);
	}
}
