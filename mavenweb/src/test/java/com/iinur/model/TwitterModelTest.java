package com.iinur.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import twitter4j.Status;

public class TwitterModelTest {

	@Test
	public void testSearch() {
		TwitterModel model = new TwitterModel();
		List<Status> s = model.search("http://jin115.com/archives/52000081.html");
		System.out.println(s.size());
		assertTrue(s.size() > 0);
	}

	@Test
	public void testCountSearch(){
		TwitterModel model = new TwitterModel();
		int result = model.countSearch("http://jin115.com/archives/52000081.html");
		System.out.println("count:"+result);
		assertTrue(result > 0);
	}
}
