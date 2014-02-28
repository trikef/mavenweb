package com.iinur.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.iinur.core.data.bean.Rss;
import com.iinur.core.data.bean.Tag;
import com.iinur.model.RssModel;
import com.iinur.model.TagModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author k
 *
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

    private List<Rss> rsss;
    private List<Rss> rsssRanking;
	private List<Tag> tags;
	private int limitRanking = 10;

	//category
    public String c1;
    public String c2;
    public String d = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

    public String q;

    public List<Rss> getRsss() {
		return rsss;
	}

	public void setRsss(List<Rss> rsss) {
		this.rsss = rsss;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Rss> getRsssRanking() {
		return rsssRanking;
	}

	public void setRsssRanking(List<Rss> rsssRanking) {
		this.rsssRanking = rsssRanking;
	}

	public String execute() {

		ServletContext sc = ServletActionContext.getServletContext();

        RssModel model = new RssModel(sc);
        TagModel tagm = new TagModel();
        if(!StringUtils.isEmpty(q)){
        	this.setRsss(model.search(q));
        	this.setTags(tagm.getRecommendTags(q));
        } else {
        	this.setRsss(model.get(c1, c2, d));
        	this.setTags(tagm.getTags());
        }
    	this.setRsssRanking(model.getRanking(limitRanking));

		return "success";
	}
}
