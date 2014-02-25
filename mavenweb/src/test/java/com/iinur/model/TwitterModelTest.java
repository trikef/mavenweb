package com.iinur.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TwitterModelTest {

	@Test
	public void testSearch() {
		TwitterModel model = new TwitterModel();
		List<Status> s = model.search("http://pazusoku.blog.fc2.com/blog-entry-2254.html");
		System.out.println(s.size());
		assertTrue(s.size() > 0);
	}

}
