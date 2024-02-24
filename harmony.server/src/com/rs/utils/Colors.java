package com.rs.utils; //so useful

public final class Colors {

	public static String red = "<col=ff0000>";
	public static String salmon = "<col=ff3333>";
	public static String green = "<col=00ff00>";
	public static String blue = "<col=0000ff>";
	public static String white = "<col=ffffff>";
	public static String black = "<col=000000>";
	public static String orange = "<col=C9A204>";
	public static String cyan = "<col=00B2ED>";
	public static String rcyan = "<col=80ffff>";
	public static String dcyan = "<col=00b3b3>";
	public static String gray = "<col=A6A6A6>";
	public static String lightGray = "<col=C4C4C4>";
	public static String darkRed = "<col=B52100>";
	public static String yellow = "<col=FFFF00>";
	public static String brown = "<col=8C6C0D>";
	public static String gold = "<col=E6CC07>";
	public static String purple = "<col=ff00ff>";
	public static String shad = "<shad=000000>";
	public static String eshad = "</col></shad>";

	public static String check(boolean finished) {
		return finished ? green : red;
	}
	
	public static String shade(String color) {
		return shad+color;
	}
	
}
