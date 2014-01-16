package com.iinur.core.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private List<DBConfig> dbList = new ArrayList<DBConfig>();
	private List<RssConfig> rssList = new ArrayList<RssConfig>();

	public void addDBConfig(DBConfig conf) {
		dbList.add(conf);
	}

	public List<DBConfig> getDBList() {
		return dbList;
	}

	public void addRssConfig(RssConfig conf) {
		this.rssList.add(conf);
	}

	public List<RssConfig> getRssList() {
		return rssList;
	}

}