package com.iinur.core.data.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tag {
	
	private static final Logger log = LoggerFactory.getLogger(Tag.class);

	private String word;
	private int rank;

	public String getWord() {
		return word;
	}
	public String getEword(){
		try {
			return URLEncoder.encode(word, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
