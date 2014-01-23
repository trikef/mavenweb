package com.iinur.crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.iinur.core.config.RssConfig;
import com.iinur.core.data.bean.Rss;
import com.iinur.model.RssModel;

public class RssCrawler {

	private List<RssConfig> rssUrlList;
	private RssModel model;

	private RssCrawler() {
	};

	public RssCrawler(ServletContext sc) {
		model = new RssModel(sc);
		rssUrlList = model.getRssUrlList();
	}

	public int crawl() {
		List<Rss> list = new ArrayList<Rss>();
		for (RssConfig conf : rssUrlList) {
			list.addAll(model.convert(model.getRssHttp(conf), conf));
		}
		Collections.sort(list, new DateComparator(DateComparator.DESC));

		for (Rss rss : list) {
			model.registration(rss);
		}

		return list.size();
	}
}

class DateComparator implements Comparator<Rss> {

	public static final int ASC = 1;
	public static final int DESC = -1;
	private int sort = ASC;

	public DateComparator() {

	}

	/**
	 * @param sort
	 *            DateComparator.ASC | DateComparator.DESC。昇順や降順を指定します。
	 */
	public DateComparator(int sort) {
		this.sort = sort;
	}

	public int compare(Rss b1, Rss b2) {
		Date d1 = b1.getDate_written();
		Date d2 = b2.getDate_written();
		if (d1 == null && d2 == null) {
			return 0;
		} else if (d1 == null) {
			return 1 * sort;
		} else if (d2 == null) {
			return -1 * sort;
		}
		int diff = d1.compareTo(d2);

		if (diff == 0) {
			return 0;
		} else if (diff < 0) {
			return -1 * sort;
		} else {
			return 1 * sort;
		}
	}
}