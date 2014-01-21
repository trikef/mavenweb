package com.iinur.core.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private List<RssConfig> rssList = new ArrayList<RssConfig>();

	public void addRssConfig(RssConfig conf) {
		this.rssList.add(conf);
	}

	public List<RssConfig> getRssList() {
		return rssList;
	}

}