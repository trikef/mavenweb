package com.iinur.model;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import com.iinur.core.config.Config;
import com.iinur.core.config.ConfigLoader;
import com.iinur.core.config.RssConfig;

public class ConfigModel {

	private static ConfigModel instance = null;
	private Config conf = null;

	
	private static final String CONFIG_FILE_PATH = "/WEB-INF/config.xml";

	private ConfigModel(){}
	
	private ConfigModel(ServletContext context) {

		ConfigLoader confLoader = new ConfigLoader();
		File f = new File(context.getRealPath("./") + CONFIG_FILE_PATH);
		conf = (Config) confLoader.load(f);
	}

	public static ConfigModel getInstance(ServletContext context) {
		if (instance == null) {
			instance = new ConfigModel(context);
		}

		return instance;
	}

	public static List<RssConfig> getRssConfigList(ServletContext context) {

		return getInstance(context).conf.getRssList();
	}
}
