package com.quicklife.pojo;

import java.util.Date;

/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

public class Favorite implements java.io.Serializable {

	// Fields

	private int id;
	private int  userid;
	private int  busid;
	private Date act_time;

	// Constructors

	/** default constructor */
	public Favorite() {
	}

	public Favorite(int id, int userid, int busid, Date act_time) {
		this.id = id;
		this.userid = userid;
		this.busid = busid;
		this.act_time = act_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBusid() {
		return busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public Date getAct_time() {
		return act_time;
	}

	public void setAct_time(Date act_time) {
		this.act_time = act_time;
	}



}