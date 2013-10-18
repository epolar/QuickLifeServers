package com.quicklife.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */

public class Tuser implements java.io.Serializable {

	// Fields

	private int id;
	private String login_name;
	private String nickname;
	private String password;
	private int sex;
	private Double lat;
	private Double lon;
	private int score;
	private String bind_phone;
	private Date last_login_time;
	private Date last_loc_time;
	private String photo;
	private String email;


	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String loginName, String name, String password, int sex) {
		this.login_name = loginName;
		this.nickname = name;
		this.password = password;
		this.sex = sex;
	}

	/** full constructor */
	public Tuser(int id, String login_name, String name, String password,
			int sex, Double lat, Double lon, int score, String bind_phone,
			Date lastLoginTime, Date lastLocTime, String photo, String email) {
		super();
		this.id = id;
		this.login_name = login_name;
		this.nickname = name;
		this.password = password;
		this.sex = sex;
		this.lat = lat;
		this.lon = lon;
		this.score = score;
		this.bind_phone = bind_phone;
		this.last_login_time = lastLoginTime;
		this.last_loc_time = lastLocTime;
		this.photo = photo;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getBind_phone() {
		return bind_phone;
	}

	public void setBind_phone(String bindPhone) {
		this.bind_phone = bindPhone;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Date getLast_loc_time() {
		return last_loc_time;
	}

	public void setLast_loc_time(Date last_loc_time) {
		this.last_loc_time = last_loc_time;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Property accessors

}