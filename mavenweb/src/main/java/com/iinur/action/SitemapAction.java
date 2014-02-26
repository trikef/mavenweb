package com.iinur.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.iinur.core.config.AppConfig;
import com.iinur.core.data.bean.Tag;
import com.iinur.model.ConfigModel;
import com.iinur.model.TagModel;
import com.opensymphony.xwork2.ActionSupport;

public class SitemapAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String url;
	private List<Tag> tags;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String execute() {
		
		ServletContext sc = ServletActionContext.getServletContext();

        AppConfig config = ConfigModel.getAppConfig(sc);
        this.setUrl(config.getUrl());
        
		TagModel tagm = new TagModel();
        this.setTags(tagm.getTags(1000));
		return "success";
	}
}
