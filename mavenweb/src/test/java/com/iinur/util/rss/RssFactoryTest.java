package com.iinur.util.rss;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iinur.core.config.RssConfig;
import com.iinur.util.rss.document.impl.RssDocument;

public class RssFactoryTest {

	public RssConfig conf;
	@Before
	public void setUp() throws Exception {
		conf = new RssConfig();

		conf.setName("codezine");
		conf.setType("PubDateType");
		conf.setUrl("http://rss.rssad.jp/rss/codezine/new/20/index.xml");
	}

	@Test
	public void testGetRssDocument() {
		RssFactory rf = new RssFactory();
		RssDocument rss = rf.getRssDocument(conf);

		//System.out.println(rss.getBeanList().size());
		assertTrue(rss.getBeanList().size() > 0);
	}

}
