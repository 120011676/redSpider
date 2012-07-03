package org.qq120011676.redspider.util;

import java.util.ArrayList;
import java.util.List;

import org.qq120011676.snow.util.file.IFile;

public class RedSpiderURL implements IFile {

	private static List<String> URLS = new ArrayList<String>();

	public static List<String> getURLs() {
		return URLS;
	}

	@Override
	public void readLine(String readLine) {
		URLS.add(readLine);
	}
}
