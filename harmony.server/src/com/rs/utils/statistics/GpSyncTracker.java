package com.rs.utils.statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import com.rs.utils.Utils;

public final class GpSyncTracker {

	public static void printGpSync(long amount) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/gpsync.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + "] - GP sank today: " + Utils.format(amount));
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

}
