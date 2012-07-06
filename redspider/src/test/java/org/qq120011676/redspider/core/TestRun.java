package org.qq120011676.redspider.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestRun {

	@Test
	public void test() throws IOException {
		List<String> url = new ArrayList<String>();
		url.add("http://www.baidu.com");
		url.add("http://www.qq.com");
		url.add("http://www.google.com");
		url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		new RedSpider().runVisit(url, new IRedSpiderInputStreamRead() {

			@Override
			public void readInputStream(InputStream inputStream) {
				try {
					System.out.println(RedSpiderCore.getContent(inputStream));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		List<String> url = new ArrayList<String>();
		url.add("http://www.baidu.com");
		// url.add("http://www.qq.com");
		// url.add("http://www.google.com");
		url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		try {
			new RedSpider().runVisit(url, new IRedSpiderInputStreamRead() {

				@Override
				public void readInputStream(InputStream inputStream) {
					try {
						System.out.println(RedSpiderCore
								.getContent(inputStream));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
