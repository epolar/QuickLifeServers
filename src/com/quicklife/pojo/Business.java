package com.quicklife.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Business entity. @author MyEclipse Persistence Tools
 */

public class Business implements java.io.Serializable {

	// Fields

	private int id;
	private int type;
	private String login_name;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String logo;
	private Double lat;
	private Double lon;
	private Long score;
	private int roleid;

	// Constructors

	/** default constructor */
	public Business() {
	}

	/** minimal constructor */
	public Business(String loginName, String password, String name,
			String phone, String address, int roleid) {
		this.login_name = loginName;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.roleid = roleid;
	}

	/** full constructor */
	public Business(int type, String loginName, String password, String name,
			String phone, String address, String logo, Double lat, Double lon,
			Long score, int roleid) {
		this.type = type;
		this.login_name = loginName;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.logo = logo;
		this.lat = lat;
		this.lon = lon;
		this.score = score;
		this.roleid = roleid;
	}

	// Property accessors

	public int getId() {
		return this.id;
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

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}