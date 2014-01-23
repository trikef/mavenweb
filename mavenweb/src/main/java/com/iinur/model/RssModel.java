package com.iinur.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.iinur.core.config.RssConfig;
import com.iinur.core.data.RssDao;
import com.iinur.core.data.bean.Rss;
import com.iinur.util.rss.RssFactory;
import com.iinur.util.rss.document.bean.RssDocumentBean;
import com.iinur.util.rss.document.impl.RssDocument;

public class RssModel {
	
	private List<RssConfig> rssUrlList;

	private RssModel(){};

	public RssModel(ServletContext sc){
		setRssUrlList(ConfigModel.getRssConfigList(sc));
	}

	public List<RssConfig> getRssUrlList() {
		return rssUrlList;
	}

	public void setRssUrlList(List<RssConfig> rssUrlList) {
		this.rssUrlList = rssUrlList;
	}

	public List<Rss> get(String category1, String category2, String day){
        RssDao dao = new RssDao();
        return dao.getWhereDay(category1, category2, day);
	}

	public List<RssDocumentBean> getRssHttp(RssConfig conf){
		RssFactory rf = new RssFactory();
		RssDocument rd = rf.getRssDocument(conf);
		
		return rd.getBeanList();
	}
	
	public List<Rss> search(String query){
		RssDao dao = new RssDao();
        return dao.search(query);
	}

	public int registration(Rss rss){
		int exists;
		RssDao dao = new RssDao();
		exists = dao.existTitle(rss.getTitle());
		if(exists == 0){
			dao.insert(rss.getBlog_title(), rss.getCategory1(), rss.getCategory2(), rss.getTitle(),
					rss.getDescription(), rss.getLink(), rss.getDate_written());
		}
		return exists;
	}
	
	public List<Rss> convert(List<RssDocumentBean> beans, RssConfig conf){
		List<Rss> list = new ArrayList<Rss>();
		for (RssDocumentBean bean : beans) {
			Rss r = new Rss();
			r.setBlog_title(bean.getBlogTitle());
			r.setCategory1(conf.getCategory1());
			r.setCategory2(conf.getCategory2());
			r.setTitle(bean.getTitle());
			r.setDescription(bean.getDescription());
			r.setLink(bean.getLink());
			r.setDate_written(bean.getDate());
			
			list.add(r);
		}
		return list;
	}
}

class DateComparator implements Comparator<Rss>{

	public static final int ASC = 1;
	public static final int DESC = -1;
	private int sort = ASC;
	
	
	public DateComparator(){
		
	}
	/** 
     * @param sort  DateComparator.ASC | DateComparator.DESC。昇順や降順を指定します。 
     */  
	public DateComparator(int sort){
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
		
		if(diff == 0){
			return 0;
		} else if(diff < 0){
			return -1 * sort; 
		} else {
			return 1 * sort;
		}
	}
	
}