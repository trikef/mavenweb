package com.iinur.util.rss.document.impl;

import java.util.List;

import org.w3c.dom.Document;

import com.iinur.util.rss.document.bean.RssDocumentBean;

public interface RssDocument {
	
	void setAll(Document document);

	List<RssDocumentBean> getBeanList();
	void setBeanList(List<RssDocumentBean> beanList);
	
}
