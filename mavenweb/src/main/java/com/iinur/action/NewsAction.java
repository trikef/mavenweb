package com.iinur.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.iinur.model.RssModel;
import com.iinur.util.rss.document.bean.RssDocumentBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author k
 *
 */
public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<RssDocumentBean> rssList;
	
	//category
	public String c;

	public List<RssDocumentBean> getRssList() {
		return rssList;
	}

	public void setRssList(List<RssDocumentBean> rssList) {
		this.rssList = rssList;
	}

	public String execute() {

		ServletContext sc = ServletActionContext.getServletContext();

		RssModel rssM = new RssModel();
		if(StringUtils.isEmpty(c)){
			setRssList(rssM.getFromConfig(sc));
		} else {
			setRssList(rssM.getFromConfig(sc, c));
		}

		return "success";
	}
}
