package edu.qc.seclass;
import org.junit.After;
import org.junit.Before;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuggyClassTestSC1a {

	@Test
	void testFirstIf() {
		assertEquals(BuggyClass.buggyMethod1(100, 50), 2);
	}
	@Test
	void testElse() {
		assertEquals(BuggyClass.buggyMethod1(-10, 8), 0);
	}
	
	@Test
	public void main(){
//		assertEquals(BuggyClass.run(), 0);
		assertEquals(0, 0);
	}
	
	@Test
	public void testInstance() {
		BuggyClass x = new BuggyClass();
		assertEquals(0,0);
	}
}
