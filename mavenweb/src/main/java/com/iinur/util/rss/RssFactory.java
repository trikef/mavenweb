package com.iinur.util.rss;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.iinur.core.config.RssConfig;
import com.iinur.util.ClassForName;
import com.iinur.util.rss.document.impl.RssDocument;

public class RssFactory {

	private static final String RSS_CLASS_NAME_SUFFIX = "RssDocument";
	private static final String RSS_CLASS_PACKAGE = "com.iinur.util.rss.document.";

	public RssDocument getRssDocument(RssConfig conf) {
		
		String rssDocumentName = conf.getName();
		if(!StringUtils.isEmpty(conf.getType())){
			rssDocumentName = conf.getType();
		}

		RssDocument rd = (RssDocument) ClassForName
				.InstanceForName(RSS_CLASS_PACKAGE + rssDocumentName
						+ RSS_CLASS_NAME_SUFFIX);

		Document document = getDocument(conf.getUrl());
		rd.setAll(document);

		return rd;
	}

	public Document getDocument(String rssUrl) {

		Document document = null;
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			document = builder.parse(rssUrl);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;

	}
}
