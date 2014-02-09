package com.iinur.model;

import java.util.List;

import com.iinur.core.data.TagDao;
import com.iinur.core.data.bean.Tag;

public class TagModel {

	public List<Tag> getTags(){
		TagDao dao = new TagDao();
		return dao.getTags();
	}
	
	public List<Tag> getRelationTags(String url){
		TagDao dao = new TagDao();
		return dao.getRelationTags(url);
	}

	public List<Tag> getRecommendTags(String q) {
		TagDao dao = new TagDao();
		return dao.getRecommendTags(q);
	}
}
