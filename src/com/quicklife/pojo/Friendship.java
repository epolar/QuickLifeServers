package com.quicklife.pojo;

import java.util.Date;

/**
 * Friendship entity. @author MyEclipse Persistence Tools
 */

public class Friendship implements java.io.Serializable {

	// Fields

	private int id;
	private int user2;
	private int user1;
	private int status;
	private Date req_time;

	// Constructors

	/** default constructor */
	public Friendship() {
	}

	public Friendship(int id, int user2, int user1, int status, Date req_time) {
		this.id = id;
		this.user2 = user2;
		this.user1 = user1;
		this.status = status;
		this.req_time = req_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser2() {
		return user2;
	}

	public void setUser2(int user2) {
		this.user2 = user2;
	}

	public int getUser1() {
		return user1;
	}

	public void setUser1(int user1) {
		this.user1 = user1;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getReq_time() {
		return req_time;
	}

	public void setReq_time(Date req_time) {
		this.req_time = req_time;
	}

	
}