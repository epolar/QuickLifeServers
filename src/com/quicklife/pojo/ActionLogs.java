package com.quicklife.pojo;

import java.util.Date;

/**
 * ActionLogs entity. @author MyEclipse Persistence Tools
 */

public class ActionLogs implements java.io.Serializable {

	// Fields

	private int id;
	private int ac_type;
	private int couponid;
	private int busid;
	private int userid;
	private Date ac_time;
	private String comments;
	private int score;
	private String photo;
	private String recording;
	private String video;

	// Constructors

	/** default constructor */
	public ActionLogs() {
	}

	/** full constructor */
	public ActionLogs(int id, int ac_type, int couponid, int busid, int userid,
			Date ac_time, String comments, int score, String photo,
			String recording, String video) {
		super();
		this.id = id;
		this.ac_type = ac_type;
		this.couponid = couponid;
		this.busid = busid;
		this.userid = userid;
		this.ac_time = ac_time;
		this.comments = comments;
		this.score = score;
		this.photo = photo;
		this.recording = recording;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAc_type() {
		return ac_type;
	}

	public void setAc_type(int ac_type) {
		this.ac_type = ac_type;
	}

	public int getCouponid() {
		return couponid;
	}

	public void setCouponid(int couponid) {
		this.couponid = couponid;
	}

	public int getBusid() {
		return busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getAc_time() {
		return ac_time;
	}

	public void setAc_time(Date ac_time) {
		this.ac_time = ac_time;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRecording() {
		return recording;
	}

	public void setRecording(String recording) {
		this.recording = recording;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
}