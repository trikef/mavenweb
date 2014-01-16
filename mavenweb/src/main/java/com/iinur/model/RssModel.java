package com.iinur.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.iinur.core.config.RssConfig;
import com.iinur.util.rss.RssFactory;
import com.iinur.util.rss.document.bean.RssDocumentBean;
import com.iinur.util.rss.document.impl.RssDocument;

public class RssModel {
	
	public List<RssDocumentBean> getFromConfig(ServletContext sc){
		List<RssConfig> rssUrlList = ConfigModel.getRssConfigList(sc);
		
		List<RssDocumentBean> list = new ArrayList<RssDocumentBean>();
		for (RssConfig conf : rssUrlList) {
			if("off".equals(conf.getView())){
				continue;
			}
			list.addAll(getRss(conf));
		}
		Collections.sort(list, new DateComparator(DateComparator.DESC));
		
		return list;
	}
	
	public List<RssDocumentBean> getFromConfig(ServletContext sc, String category){
		List<RssConfig> rssUrlList = ConfigModel.getRssConfigList(sc);
		
		List<RssDocumentBean> list = new ArrayList<RssDocumentBean>();
		for (RssConfig conf : rssUrlList) {
			if("off".equals(conf.getView())){
				continue;
			} else if(!category.equals(conf.getCategory())){
				continue;
			}
			list.addAll(getRss(conf));
		}
		Collections.sort(list, new DateComparator(DateComparator.DESC));
		
		return list;
	}
	
	public List<RssDocumentBean> getRss(RssConfig conf){
		
		RssFactory rf = new RssFactory();
		RssDocument rd = rf.getRssDocument(conf);
		
		return rd.getBeanList();
	}
}

class DateComparator implements Comparator<RssDocumentBean>{

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
	
	public int compare(RssDocumentBean b1, RssDocumentBean b2) {
		Date d1 = b1.getDate();
		Date d2 = b2.getDate();
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