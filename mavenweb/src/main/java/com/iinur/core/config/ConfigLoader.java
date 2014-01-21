package com.iinur.core.config;

import java.io.*;

import org.apache.commons.digester3.*;
import org.xml.sax.*;

public class ConfigLoader {
	public Config load(File configfile) {
		try {
			Digester digester = new Digester();
			// digester.setValidating(false);
			// digester.push(this);
			// 読み込みルール
			digester.addObjectCreate("conf", Config.class);

			digester.addObjectCreate("conf/rss", RssConfig.class);
			digester.addSetNext("conf/rss", "addRssConfig");
			digester.addSetProperties("conf/rss", "no", "id");
			digester.addSetProperties("conf/rss", "view", "view");
			digester.addBeanPropertySetter("conf/rss/name", "name");
			digester.addBeanPropertySetter("conf/rss/url", "url");
			digester.addBeanPropertySetter("conf/rss/type", "type");
			digester.addBeanPropertySetter("conf/rss/category", "category");
			
			return (Config) digester.parse(configfile);

		} catch (IOException e) {
			throw new RuntimeException(configfile.getPath() + ":"
					+ e.toString());
		} catch (SAXException e) {
			throw new RuntimeException(configfile.getPath() + ":"
					+ e.toString());
		}
	}
}