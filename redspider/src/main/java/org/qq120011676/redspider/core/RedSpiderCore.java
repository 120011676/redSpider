package org.qq120011676.redspider.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class RedSpiderCore {

	private static final int CONNECT_TIMEOUT = 60 * 1000;

	private static final int READ_TIMEOUT = 60 * 1000;

	public String get(String url) throws IOException {
		return get(url, null);
	}

	public String get(String url, Map<String, String> map) throws IOException {
		return get(url, map, null);
	}

	public String get(String url, Map<String, String> map,
			Map<String, String> requestProperty) throws IOException {
		if (map != null && !map.isEmpty()) {
			Iterator<String> iterator = map.keySet().iterator();
			boolean isFirst = true;
			while (iterator.hasNext()) {
				String name = iterator.next();
				if (isFirst) {
					isFirst = false;
					url += "?";
				} else {
					url += "&";
				}
				url += name + "=" + map.get(name);
			}
		}
		return getContent(getHttpURLConnection(url, requestProperty));
	}

	public String post(String url) throws IOException {
		return post(url, null);
	}

	public String post(String url, Map<String, String> map) throws IOException {
		return post(url, map, null);
	}

	public String post(String url, Map<String, String> map,
			Map<String, String> requestProperty) throws IOException {
		HttpURLConnection httpURLConnection = getHttpURLConnection(url,
				requestProperty);
		httpURLConnection.setRequestMethod("POST");
		if (map != null && !map.isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			boolean isFirst = true;
			for (String key : map.keySet()) {
				if (!isFirst) {
					stringBuilder.append("&");
				}
				stringBuilder.append(key + "=" + map.get(key));
				isFirst = false;
			}
			httpURLConnection.setDoOutput(true);
			httpURLConnection.getOutputStream().write(
					stringBuilder.toString().getBytes("UTF-8"));
			httpURLConnection.getOutputStream().flush();
			httpURLConnection.getOutputStream().close();
		}

		return getContent(httpURLConnection);
	}

	public HttpURLConnection getHttpURLConnection(String url)
			throws MalformedURLException, IOException {
		return getHttpURLConnection(url, null);
	}

	public HttpURLConnection getHttpURLConnection(String url,
			Map<String, String> requestProperty) throws MalformedURLException,
			IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setConnectTimeout(CONNECT_TIMEOUT);
		connection.setReadTimeout(READ_TIMEOUT);
		connection.setRequestProperty("user-agent", "redSpider");
		if (requestProperty != null) {
			for (String key : requestProperty.keySet()) {
				connection.setRequestProperty(key, requestProperty.get(key));
			}
		}
		return connection;
	}

	public String getContent(HttpURLConnection httpURLConnection)
			throws IOException {
		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try {
				String contentType = httpURLConnection.getContentType();
				String charset = "UTF-8";
				int index = contentType.indexOf("=");
				if (index >= 0) {
					charset = contentType.substring(index + 1);
				}
				return getContent(httpURLConnection.getInputStream(), charset);
			} finally {
				close(null, null, httpURLConnection);
			}
		}
		return null;
	}

	public String getContent(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append("\r\n");
		}
		close(bufferedReader, inputStream, null);
		return stringBuffer.toString().trim();
	}

	public String getContent(InputStream inputStream, String charset)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, charset));
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append("\r\n");
		}
		close(bufferedReader, inputStream, null);
		return stringBuffer.toString().trim();
	}

	public void close(BufferedReader bufferedReader, InputStream inputStream,
			HttpURLConnection httpURLConnection) throws IOException {
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
