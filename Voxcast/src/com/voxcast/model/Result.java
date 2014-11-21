package com.voxcast.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

	private String Id;
	private Poster poster;
	private String msg;
	private String city;
	private ArrayList<Poster> upVoters = new ArrayList<Poster>();
	private ArrayList<Poster> downVoters = new ArrayList<Poster>();
	private String ts;
	private ArrayList<String> pics = new ArrayList<String>();
	private ArrayList<String> vid = new ArrayList<String>();
	private ArrayList<Comment> comments = new ArrayList<Comment>();

	public Result(String id, Poster poster, String msg, String city,
			ArrayList<Poster> upVoters, ArrayList<Poster> downVoters,
			String ts, ArrayList<String> pics, ArrayList<String> vid,
			ArrayList<Comment> comments) {
		Id = id;
		this.poster = poster;
		this.msg = msg;
		this.city = city;
		this.upVoters = upVoters;
		this.downVoters = downVoters;
		this.ts = ts;
		this.pics = pics;
		this.vid = vid;
		this.comments = comments;
	}

	protected Result(Parcel in) {
		Id = in.readString();
		poster = (Poster) in.readValue(Poster.class.getClassLoader());
		msg = in.readString();
		city = in.readString();
		if (in.readByte() == 0x01) {
			upVoters = new ArrayList<Poster>();
			in.readList(upVoters, Poster.class.getClassLoader());
		} else {
			upVoters = null;
		}
		if (in.readByte() == 0x01) {
			downVoters = new ArrayList<Poster>();
			in.readList(downVoters, Poster.class.getClassLoader());
		} else {
			downVoters = null;
		}
		ts = in.readString();
		if (in.readByte() == 0x01) {
			pics = new ArrayList<String>();
			in.readList(pics, String.class.getClassLoader());
		} else {
			pics = null;
		}
		if (in.readByte() == 0x01) {
			vid = new ArrayList<String>();
			in.readList(vid, String.class.getClassLoader());
		} else {
			vid = null;
		}
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
		dest.writeString(Id);
		dest.writeValue(poster);
		dest.writeString(msg);
		dest.writeString(city);
		if (upVoters == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(upVoters);
		}
		if (downVoters == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(downVoters);
		}
		dest.writeString(ts);
		if (pics == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(pics);
		}
		if (vid == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(vid);
		}
		if (comments == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(comments);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
		@Override
		public Result createFromParcel(Parcel in) {
			return new Result(in);
		}

		@Override
		public Result[] newArray(int size) {
			return new Result[size];
		}
	};

	/**
	 * 
	 * @return The Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * 
	 * @param Id
	 *            The _id
	 */
	public void setId(String Id) {
		this.Id = Id;
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
	 * @return The city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 *            The city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return The upVoters
	 */
	public ArrayList<Poster> getUpVoters() {
		return upVoters;
	}

	/**
	 * 
	 * @param upVoters
	 *            The upVoters
	 */
	public void setUpVoters(ArrayList<Poster> upVoters) {
		this.upVoters = upVoters;
	}

	/**
	 * 
	 * @return The downVoters
	 */
	public ArrayList<Poster> getDownVoters() {
		return downVoters;
	}

	/**
	 * 
	 * @param downVoters
	 *            The downVoters
	 */
	public void setDownVoters(ArrayList<Poster> downVoters) {
		this.downVoters = downVoters;
	}

	/**
	 * 
	 * @return The ts
	 */
	public String getTs() {
		return ts;
	}

	/**
	 * 
	 * @param ts
	 *            The ts
	 */
	public void setTs(String ts) {
		this.ts = ts;
	}

	/**
	 * 
	 * @return The pics
	 */
	public List<String> getPics() {
		return pics;
	}

	/**
	 * 
	 * @param pics
	 *            The pics
	 */
	public void setPics(ArrayList<String> pics) {
		this.pics = pics;
	}

	/**
	 * 
	 * @return The vid
	 */
	public ArrayList<String> getVid() {
		return vid;
	}

	/**
	 * 
	 * @param vid
	 *            The vid
	 */
	public void setVid(ArrayList<String> vid) {
		this.vid = vid;
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
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

}
