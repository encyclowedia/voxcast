package com.voxcast.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {

	private Poster poster;
	private String msg;
	private String isDel;
	private List<Comment> comments = new ArrayList<Comment>();

	public Comment(Poster poster, String msg, String isDel,
			List<Comment> comments) {
		this.poster = poster;
		this.msg = msg;
		this.isDel = isDel;
		this.comments = comments;
	}

	/**
	 * 
	 * @return The poster
	 */
	public Poster getPoster() {
		return poster;
	}

	/**
	 * 
	 * @param poster
	 *            The poster
	 */
	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	/**
	 * 
	 * @return The msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 
	 * @param msg
	 *            The msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * @return The isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * 
	 * @param isDel
	 *            The isDel
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	/**
	 * 
	 * @return The comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * 
	 * @param comments
	 *            The comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	protected Comment(Parcel in) {
		poster = (Poster) in.readValue(Poster.class.getClassLoader());
		msg = in.readString();
		isDel = in.readString();
		if (in.readByte() == 0x01) {
			comments = new ArrayList<Comment>();
			in.readList(comments, Comment.class.getClassLoader());
		} else {
			comments = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(poster);
		dest.writeString(msg);
		dest.writeString(isDel);
		if (comments == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(comments);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
		@Override
		public Comment createFromParcel(Parcel in) {
			return new Comment(in);
		}

		@Override
		public Comment[] newArray(int size) {
			return new Comment[size];
		}
	};
}