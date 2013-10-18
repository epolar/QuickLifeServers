package com.quicklife.pojo;

import java.util.Date;

/**
 * Pushmessage entity. @author MyEclipse Persistence Tools
 */

public class Pushmessage implements java.io.Serializable {

	// Fields

	private int id;
	private int type;
	private int userid;
	private int status;
	private Date push_time;

	// Constructors

	/** default constructor */
	public Pushmessage() {
	}

	public Pushmessage(int id, int type, int userid, int status, Date pushTime) {
		this.id = id;
		this.type = type;
		this.userid = userid;
		this.status = status;
		this.push_time = pushTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPushTime() {
		return push_time;
	}

	public void setPushTime(Date pushTime) {
		this.push_time = pushTime;
	}


}