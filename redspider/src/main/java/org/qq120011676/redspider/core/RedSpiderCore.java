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

	private static final int CONNECT_TIMEOUT = 3 * 1000;

	private static final int READ_TIMEOUT = 60 * 1000;

	public static String call(String url) throws IOException {
		return RedSpiderCore
				.getContent(RedSpiderCore.getHttpURLConnection(url));
	}

	public static String call(String url, String host, int port)
			throws IOException {
		return RedSpiderCore.getContent(RedSpiderCore.getHttpURLConnection(url,
				host, port));
	}

	public static int callCode(String url) throws IOException {
		return RedSpiderCore.callCode(url, null, 0);
	}

	public static int callCode(String url, String host, int port)
			throws IOException {
		return RedSpiderCore.getHttpURLConnection(url, host, port)
				.getResponseCode();
	}

	public static InputStream callInputStream(String url) throws IOException {
		return RedSpiderCore.getInputStream(RedSpiderCore.getHttpURLConnection(
				url, null, 0));
	}

	public static InputStream callInputStream(String url, String host, int port)
			throws IOException {
		return RedSpiderCore.getInputStream(RedSpiderCore.getHttpURLConnection(
				url, host, port));
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
		connection.setConnectTimeout(CONNECT_TIMEOUT);
		connection.setReadTimeout(READ_TIMEOUT);
		connection.setRequestProperty("user-agent", "redSpider");
		return connection;
	}

	private static String getContent(HttpURLConnection connection)
			throws IOException {
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return getContent(connection.getInputStream());
		}
		return null;
	}

	private static InputStream getInputStream(HttpURLConnection connection)
			throws IOException {
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println(connection.getURL());
			return connection.getInputStream();
		}
		return null;
	}

	private static String getContent(InputStream InputStream)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				InputStream));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r");
		}
		reader.close();
		return sb.toString();
	}
}
