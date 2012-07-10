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

	public static void call(String url,
			IRedSpiderInputStreamRead redSpiderInputStreamRead)
			throws IOException {
		HttpURLConnection httpURLConnection = RedSpiderCore
				.getHttpURLConnection(url);
		InputStream inputStream = RedSpiderCore
				.getInputStream(httpURLConnection);
		if (inputStream != null) {
			redSpiderInputStreamRead.readInputStream(inputStream);
		}
		close(null, inputStream, httpURLConnection);
	}

	public static int callCode(String url) throws IOException {
		return RedSpiderCore.callCode(url, null, 0);
	}

	public static int callCode(String url, String host, int port)
			throws IOException {
		HttpURLConnection httpURLConnection = RedSpiderCore
				.getHttpURLConnection(url, host, port);
		int responseCode = httpURLConnection.getResponseCode();
		close(null, null, httpURLConnection);
		return responseCode;
	}

	public static HttpURLConnection getHttpURLConnection(String url)
			throws MalformedURLException, IOException {
		return getHttpURLConnection(url, null, 0);
	}

	public static HttpURLConnection getHttpURLConnection(String url,
			String host, int port) throws MalformedURLException, IOException {
		HttpURLConnection connection;
		URL u = new URL(url);
		if (host != null && !"".equals(host.trim())) {
			Proxy proxy = new Proxy(Type.HTTP,
					new InetSocketAddress(host, port));
			connection = (HttpURLConnection) u.openConnection(proxy);
		} else {
			connection = (HttpURLConnection) u.openConnection();
		}
		connection.setConnectTimeout(CONNECT_TIMEOUT);
		connection.setReadTimeout(READ_TIMEOUT);
		connection.setRequestProperty("user-agent", "redSpider");
		return connection;
	}

	public static String getContent(HttpURLConnection httpURLConnection)
			throws IOException {
		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try {
				return getContent(httpURLConnection.getInputStream());
			} finally {
				close(null, null, httpURLConnection);
			}
		}
		return null;
	}

	private static InputStream getInputStream(HttpURLConnection connection)
			throws IOException {
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return connection.getInputStream();
		}
		return null;
	}

	public static String getContent(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append("\r\n");
		}
		close(bufferedReader, inputStream, null);
		return stringBuffer.toString();
	}

	public static void close(BufferedReader bufferedReader,
			InputStream inputStream, HttpURLConnection httpURLConnection)
			throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();
		}
	}
}
