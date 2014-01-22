package com.iinur.util.rss.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.iinur.util.rss.document.bean.RssDocumentBean;
import com.iinur.util.rss.document.impl.RssDocument;
import com.sun.syndication.io.impl.DateParser;

public class W3CBasicRssDocument implements RssDocument {

	private List<RssDocumentBean> beanList = null;

	public void setAll(Document document) {
		List<RssDocumentBean> list = new ArrayList<RssDocumentBean>();
		// ドキュメントのルートを取得
		Element root = document.getDocumentElement();

		// ルート直下の"channel"に含まれるノードリストを取得
		NodeList channel = root.getElementsByTagName("channel");

		// "channel"直下の"title"に含まれるノードリストを取得
		NodeList title = ((Element) channel.item(0))
				.getElementsByTagName("title");

		// 各"item"とその中の"title"と"description"を取得する。
		NodeList item_list = root.getElementsByTagName("item");

		// item分ループする
		for (int i = 0; i < item_list.getLength(); i++) {

			Element element = (Element) item_list.item(i);

			// title を取得する
			NodeList item_title = element.getElementsByTagName("title");
			// description を取得する
			NodeList item_description = element
					.getElementsByTagName("description");
			// link を取得する
			NodeList item_link = element.getElementsByTagName("link");

			// date を取得する
			NodeList item_date = element.getElementsByTagName("dc:date");

			RssDocumentBean bean = new RssDocumentBean();
			bean.setBlogTitle(title.item(0).getFirstChild().getNodeValue());
			bean.setTitle(item_title.item(0).getFirstChild().getNodeValue());
			
			if(item_description.item(0).getFirstChild() != null){
				bean.setDescription(item_description.item(0).getFirstChild().getNodeValue());
			}
			bean.setLink(item_link.item(0).getFirstChild().getNodeValue());
			bean.setDate(new Timestamp(DateParser.parseW3CDateTime(item_date.item(0)
					.getFirstChild().getNodeValue()).getTime()));

			list.add(bean);
		}
		setBeanList(list);
	}

	public List<RssDocumentBean> getBeanList() {
		return this.beanList;
	}

	public void setBeanList(List<RssDocumentBean> beanList) {
		this.beanList = beanList;

	}

}
