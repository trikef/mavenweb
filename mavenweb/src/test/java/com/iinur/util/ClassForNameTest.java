package com.iinur.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassForNameTest {

	@Test
	public void testInstanceForName() {
		assertEquals(new String(), ClassForName.InstanceForName("java.lang.String"));
	}
}
