package com.iinur.util.rss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.iinur.core.config.RssConfig;
import com.iinur.util.ClassForName;
import com.iinur.util.rss.document.impl.RssDocument;

public class RssFactory {

	private static final Logger log = LoggerFactory.getLogger(RssFactory.class);

	private static final String RSS_CLASS_NAME_SUFFIX = "RssDocument";
	private static final String RSS_CLASS_PACKAGE = "com.iinur.util.rss.document.";

	public RssDocument getRssDocument(RssConfig conf) {

		String rssDocumentName = conf.getName();
		if (!StringUtils.isEmpty(conf.getType())) {
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
			
			StringWriter sw = new StringWriter();
			TransformerFactory tfactory = TransformerFactory.newInstance(); 
			Transformer transformer = tfactory.newTransformer(); 
			transformer.transform(new DOMSource(document), new StreamResult(sw));
			String xml = sw.toString();
			document = builder.parse(new ByteArrayInputStream(replaceContentLF(
					xml).getBytes("utf-8")));

		} catch (ParserConfigurationException e) {
			log.error(e.getMessage());
		} catch (SAXException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (TransformerConfigurationException e) {
			log.error(e.getMessage());
		} catch (TransformerException e) {
			log.error(e.getMessage());
		}
		return document;
	}

	public String replaceContentLF(String xml) {
		return xml.replaceAll("(<content:encoded>)\n(.*)\n", "$1$2");
	}
}
