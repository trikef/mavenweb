package com.iinur.util.rss.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.iinur.util.rss.document.bean.RssDocumentBean;
import com.iinur.util.rss.document.impl.RssDocument;

public class PubDateTypeRssDocument implements RssDocument {

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
			String itile = item_title.item(0).getFirstChild().getNodeValue();
			if(isPR(itile)){
				continue;
			}
			// description を取得する
			//NodeList item_description = element
			//		.getElementsByTagName("description");
			// link を取得する
			NodeList item_link = element.getElementsByTagName("link");

			// date を取得する
			NodeList item_date = element.getElementsByTagName("pubDate");
			
			NodeList item_content = element.getElementsByTagName("description");

			RssDocumentBean bean = new RssDocumentBean();
			bean.setBlogTitle(title.item(0).getFirstChild().getNodeValue());
			bean.setTitle(itile);
			/*
			if(item_description.item(0).getFirstChild() != null){
				bean.setDescription(item_description.item(0).getFirstChild().getNodeValue());
			}
			*/
			bean.setLink(item_link.item(0).getFirstChild().getNodeValue());
			bean.setDate(new Timestamp(new Date(item_date.item(0)
					.getFirstChild().getNodeValue()).getTime()));
			bean.setContent(item_content.item(0).getFirstChild().getNodeValue());

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
	
	public boolean isPR(String title){
		Pattern ptn = Pattern.compile("^PR:", Pattern.DOTALL);
		Matcher matcher = ptn.matcher(title);

		return matcher.find();
	}

}
