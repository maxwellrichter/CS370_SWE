/**
 * 
 */
package edu.qc.seclass;

/**
 * @author julianmoskovits
 *
 */
public class MyCustomString implements MyCustomStringInterface {
	
	private String s;

	/* (non-Javadoc)
	 * @see edu.qc.seclass.MyCustomStringInterface#getString()
	 */
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return s;
	}

	/* (non-Javadoc)
	 * @see edu.qc.seclass.MyCustomStringInterface#setString(java.lang.String)
	 */
	@Override
	public void setString(String string) {
		// TODO Auto-generated method stub
		s = string;
	}

	/* (non-Javadoc)
	 * @see edu.qc.seclass.MyCustomStringInterface#countNumbers()
	 */
	@Override
	public int countNumbers() {
		// TODO Auto-generated method stub
		if(s == null){
			throw new NullPointerException();
		}
		int count = 0;
		for(int i = 0; i < s.length(); ++i){
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
				while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
					i++;
				}
				count++;
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see edu.qc.seclass.MyCustomStringInterface#getEveryNthCharacterFromBeginningOrEnd(int, boolean)
	 */
	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		if(n <= 0){
			throw new IllegalArgumentException();
		}
		if(s == null){
			throw new NullPointerException();
		}
		String res = "";
		for(int i = startFromEnd ? s.length() - n : n - 1; i < s.length() && i >= 0; i = startFromEnd ? i - n : i + n){
			res = startFromEnd ? s.charAt(i) + res : res + s.charAt(i);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see edu.qc.seclass.MyCustomStringInterface#convrtDigitsToNamesInSubstring(int, int)
	 */
	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		// TODO Auto-generated method stub
		if(startPosition > endPosition){
			throw new IllegalArgumentException();
		}
		if(startPosition < 1){
			throw new MyIndexOutOfBoundsException();
		}
		if(s == null){
			throw new NullPointerException();
		}
		if(endPosition > s.length()){
			throw new MyIndexOutOfBoundsException();
		}
		String toReplace = "";
		for(int i = startPosition-1; i < endPosition; ++i){
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
				switch(s.charAt(i)){
				case '0': toReplace = "Zero";
					break;
				case '1': toReplace = "One";
					break;
				case '2': toReplace = "Two";
					break;
				case '3': toReplace = "Three";
					break;
				case '4': toReplace = "Four";
					break;
				case '5': toReplace = "Five";
					break;
				case '6': toReplace = "Six";
					break;
				case '7': toReplace = "Seven";
					break;
				case '8': toReplace = "Eight";
					break;
				case '9': toReplace = "Nine";
					break;
				default: toReplace = "ERROR";
					break;
				}
				endPosition += toReplace.length() - 1;
				s = s.substring(0, i) + toReplace + s.substring(i + 1, s.length());
				
			}
		}
	}

}
