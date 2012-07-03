package org.qq120011676.redspider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedSpiderCore {

	public void call(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		//connection.setRequestMethod("POST");
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream input = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			reader.close();
			connection.disconnect();
		}
	}
}
