package com.iinur.core.data.bean;

import java.sql.Timestamp;

public class Tweet {

	private Long id;
	private String text;
	private String url;
	private String screen_name;
	private String name;
	private String mini_profile_image_url;
	private Timestamp created_at;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMini_profile_image_url() {
		return mini_profile_image_url;
	}
	public void setMini_profile_image_url(String mini_profile_image_url) {
		this.mini_profile_image_url = mini_profile_image_url;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
}
