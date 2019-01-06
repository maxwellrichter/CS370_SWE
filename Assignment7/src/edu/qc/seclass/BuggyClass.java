package edu.qc.seclass;
public class BuggyClass {
	
//	public static void main(String[] args) {
//		System.out.print("MAIN");
//	}

	public static int buggyMethod1(int a, int b) {
		int res = 0;
		if(a > 0) {
			res = a / b;
		}
		else if(b == 1) {
			res = (a / b) + 1;
		}
		else if(a == b) {
			res = 90;
		}
		else {
			res = (a / b) + 2;
		}
		return res;
	}
	
	public static void buggyMethod2(int a, int b) {
		/*
			It's not possible because if it doesn't 
			get detected with 100% statement coverage
			that means that it must be possible to
			avoid the error using specific inputs.
			That means that there is also a way to 
			avoid it when getting over 50% branch
			coverage.
		  
		 */
	}
	
	public static int buggyMethod3(int a) {
		int res = 0;
		if(a > -1) {
			res = 1000;
		}

		return res/a;
	}
	
	public static void buggyMethod4() {
		/*
		 	Its not possible because if every test suite
		 	that achieves 100% statement coverage reveals
		 	the fault, it must be an explicit unavoidable 
		 	divide by 0 and therefore a suite that has 100%
		 	branch coverage will also find it.
		 */
	}
	
	public void buggyMethod5 (int i) {
		/*
		 	It's impossible to create the method since there
		 	will always be a line with the fault and creating
		 	a suite with 100% statement coverage must hit that
		 	line.
		 */
	}
}
