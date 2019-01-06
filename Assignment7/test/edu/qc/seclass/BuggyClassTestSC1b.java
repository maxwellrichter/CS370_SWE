package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC1b {

	@Test
	public void testDivideZero() {
		try {
			assertEquals(BuggyClass.buggyMethod1(10, 1), 0);
		}catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	

}
