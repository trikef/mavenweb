package com.iinur.util.rss.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class PubDateTypeRssDocumentTest {

	@Test
	public void testIsPR() {
		String pr = "PR: DYNAMITE 6 CAMPAIGN";
		PubDateTypeRssDocument doc = new PubDateTypeRssDocument();
		assertTrue(doc.isPR(pr));
	}

}
