package org.qq120011676.redspider.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TestRun {

	@Test
	public void test() throws IOException {
		Set<String> url = new HashSet<String>();
		url.add("http://localhost:8080/js/ueditor1_2_2_0-utf8-jsp/dialogs/image/image.html");
//		url.add("http://www.baidu.com");
//		url.add("http://www.qq.com");
//		url.add("http://www.google.com");
//		url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		RedSpider.runVisit(url, new IRedSpiderInputStreamRead() {
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
		Set<String> url = new HashSet<String>();
		url.add("http://localhost:8080/js/ueditor1_2_2_0-utf8-jsp/dialogs/image/image.html");
		//url.add("http://www.baidu.com");
		// url.add("http://www.qq.com");
		// url.add("http://www.google.com");
		//url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		try {
			RedSpider.runVisit(url, new IRedSpiderInputStreamRead() {

				@Override
				public void readInputStream(InputStream inputStream) {
					try {
						System.out.println(new String(RedSpiderCore
								.getContent(inputStream).getBytes("gbk"),"gb2312"));
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
