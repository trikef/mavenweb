package com.iinur.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.iinur.core.data.bean.Rss;
import com.iinur.core.data.bean.Tag;
import com.iinur.model.RssModel;
import com.iinur.model.TagModel;
import com.opensymphony.xwork2.ActionSupport;

public class ContentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private Rss rss;
	private List<Tag> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Rss getRss() {
		return rss;
	}

	public void setRss(Rss rss) {
		this.rss = rss;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String execute() {
		
		ServletContext sc = ServletActionContext.getServletContext();

        RssModel model = new RssModel(sc);
        if(!StringUtils.isEmpty(id)){
        	this.setRss(model.get(Integer.parseInt(id)));
        	
        	TagModel tmodel = new TagModel();
        	setTags(tmodel.getRelationTags(getRss().getLink()));
        }

		return "success";
	}
}
