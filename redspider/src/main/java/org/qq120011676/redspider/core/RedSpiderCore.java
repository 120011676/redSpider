package org.qq120011676.redspider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public class RedSpiderCore {

	public static String call(String url) throws IOException {
		return RedSpiderCore.call(RedSpiderCore.getHttpURLConnection(url));
	}

	public static String call(String url, String host, int port)
			throws IOException {
		return RedSpiderCore.call(RedSpiderCore.getHttpURLConnection(url, host, port));
	}

	public static int callCode(String url) throws IOException {
		return RedSpiderCore.callCode(url, "", 0);
	}

	public static int callCode(String url, String host, int port)
			throws IOException {
		return RedSpiderCore.getHttpURLConnection(url, host, port).getResponseCode();
	}

	private static HttpURLConnection getHttpURLConnection(String url)
			throws MalformedURLException, IOException {
		return (HttpURLConnection) new URL(url).openConnection();
	}

	private static HttpURLConnection getHttpURLConnection(String url,
			String host, int port) throws MalformedURLException, IOException {
		HttpURLConnection connection;
		if (host != null && !"".equals(host.trim())) {
			Proxy proxy = new Proxy(Type.HTTP,
					new InetSocketAddress(host, port));
			connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		} else {
			connection = (HttpURLConnection) new URL(url).openConnection();
		}
		return connection;
	}

	private static String call(HttpURLConnection connection) throws IOException {
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream input = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\r");
			}
			reader.close();
			connection.disconnect();
			return sb.toString();
		}
		return null;
	}
}
