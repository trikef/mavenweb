package com.iinur.model;

import java.util.List;

import com.iinur.core.data.TagDao;
import com.iinur.core.data.bean.Tag;

public class TagModel {

	public List<Tag> getTags(){
		TagDao dao = new TagDao();
		return dao.getTags();
	}
}
