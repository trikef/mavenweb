package com.iinur.core.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private AppConfig appConfig;
	private List<RssConfig> rssList = new ArrayList<RssConfig>();

	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public void addRssConfig(RssConfig conf) {
		this.rssList.add(conf);
	}

	public List<RssConfig> getRssList() {
		return rssList;
	}

}