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
		// url.add("http://www.baidu.com");
		// url.add("http://www.qq.com");
		// url.add("http://www.google.com");
		// url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		RedSpider.runVisit(url, 1, new IRedSpiderInputStreamRead() {
			@Override
			public void readContent(String content) {
				System.out.println(content);
			}
		});
	}

	public static void main(String[] args) {
		Set<String> url = new HashSet<String>();
		//url.add("http://www.baidu.com");
		 url.add("http://localhost:8080/file/html/1343034726670.html");
		// url.add("http://www.baidu.com");
		// url.add("http://www.qq.com");
		// url.add("http://www.google.com");
		// url.add("http://www.soso.com");
		// for (int i = 0; i < 100; i++) {
		// url.add("http://www.soso.com");
		// }
		try {
			RedSpider.runVisit(url, 1, new IRedSpiderInputStreamRead() {

				@Override
				public void readContent(String content) {
					System.out.println(content);
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
