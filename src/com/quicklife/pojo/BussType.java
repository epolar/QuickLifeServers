package com.quicklife.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * BussType entity. @author MyEclipse Persistence Tools
 */

public class BussType implements java.io.Serializable {

	// Fields
	private int id;
	private String type;

	// Constructors

	/** default constructor */
	public BussType() {
	}

	/** full constructor */

	public BussType(int id, String type) {
		this.id = id;
		this.type = type;
	}

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