package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class ReportAbuse {

	public static void handle(String reportName, String username, int offense, boolean muted) {
		Calendar c = Calendar.getInstance();
		
		String date= "[" + ((c.get(c.MONTH))+1) + "/" + c.get(c.DATE) + "/" + c.get(c.YEAR) + "]";// You may want to add time
		
			String filepath = "data/reports/report.txt";
			BufferedWriter out;
			try {
				out = new BufferedWriter(new FileWriter(filepath, true));
				out.write(date + " Report by " + reportName + " - Offender: " + username + " Offense: " + getType(offense));
				out.newLine();
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static String getType(int id) {
		
		switch(id) {
		case 6:return "Buying or selling account";
		case 9:return "encourage rule breaking";
		case 5:return "staff impersonation";
		case 7:return "macroing/use of bots";
		case 15:return "scamming";
		case 4:return "Exploiting a bug";
		case 16:return "seriously offensive language";
		case 17:return "solicitation";
		case 18:return "Disruptive behaviour";
		case 19:return "offensive account name";
		case 20:return "real life threats";
		case 13:return "asking for real life info";
		case 21:return "breaking real world laws";
		case 11:return "advertising websites";
		}
		
		return "unknown";
	}
	
}