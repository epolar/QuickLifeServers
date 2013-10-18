package com.quicklife.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * CouponType entity. @author MyEclipse Persistence Tools
 */

public class CouponType implements java.io.Serializable {

	// Fields
	private int id;
	private String type;

	// Constructors

	/** default constructor */
	public CouponType() {
	}

	/** full constructor */
	public CouponType(int id, String type) {
		this.type = type;
		this.id = id;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}