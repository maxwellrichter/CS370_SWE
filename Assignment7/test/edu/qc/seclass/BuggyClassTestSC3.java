package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC3 {

	@Test
	void testIf() {
		try {
			assertEquals(BuggyClass.buggyMethod3(0), 0);
		}
		catch(Exception e) {
			fail("Exception hit");
		}
	}

}
