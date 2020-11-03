package kr.or.connect.todo.util;

public class ValueTypeInspection {
	public static boolean isValidString(String strNum) {
		if(strNum == null || strNum.length() == 0)
			return false;
		return true;
	}
	
	public static boolean isNumeric(String strNum) {
		if(strNum == null || strNum.length() == 0)
			return false;
		
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
