package com.quicklife.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * ActionType entity. @author MyEclipse Persistence Tools
 */

public class ActionType implements java.io.Serializable {

	// Fields
	private int id;
	private String name;

	// Constructors

	/** default constructor */
	public ActionType() {
	}

	public ActionType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}