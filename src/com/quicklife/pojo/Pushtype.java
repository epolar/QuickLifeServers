package com.quicklife.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Pushtype entity. @author MyEclipse Persistence Tools
 */

public class Pushtype implements java.io.Serializable {

	// Fields

	private int id;
	private String type;

	// Constructors

	/** default constructor */
	public Pushtype() {
	}

	/** full constructor */
	public Pushtype(int id, String type) {
		this.id = id;
		this.type = type;
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