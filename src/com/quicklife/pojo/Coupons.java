package com.quicklife.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Coupons entity. @author MyEclipse Persistence Tools
 */

public class Coupons implements java.io.Serializable {

	// Fields
	private int id;
	private int type;
	private int busid;
	private String name;
	private String context;
	private int area;
	private Date pubTime;
	private Date begTime;
	private Date endTime;
	private String image;
	private int uses;
	private int score;
	private int status;

	// Constructors

	/** default constructor */
	public Coupons() {
	}
	
	/** full constructor */
	public Coupons(int id, int type, int busid, String name, String context,
			int area, Date pubTime, Date begTime, Date endTime, String image,
			int uses, int score, int status) {
		this.id = id;
		this.type = type;
		this.busid = busid;
		this.name = name;
		this.context = context;
		this.area = area;
		this.pubTime = pubTime;
		this.begTime = begTime;
		this.endTime = endTime;
		this.image = image;
		this.uses = uses;
		this.score = score;
		this.status = status;
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

	public int getBusid() {
		return busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Date getBegTime() {
		return begTime;
	}

	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}