package org.qq120011676.redspider.core;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class RedSpider {

	private static class RedSpiderThread extends Thread {

		private volatile static List<String> URLS;

		private static IRedSpiderInputStreamRead RED_SPIDER_INPUT_STREAM_READ;

		@Override
		public void run() {
			String url = null;
			synchronized (URLS) {
				if (URLS.size() == 0) {
					return;
				}
				url = URLS.get(0);
				URLS.remove(0);
			}
			try {
				RedSpiderCore.call(url, RED_SPIDER_INPUT_STREAM_READ);
			} catch (IOException e) {
				Logger.getLogger(this.getClass().getName() + " 出错了，url：" + url);
			} finally {
				this.run();
			}
		}
	}

	public static void runVisit(List<String> urls,
			IRedSpiderInputStreamRead redSpiderInputStreamRead)
			throws IOException {
		RedSpiderThread.URLS = urls;
		RedSpiderThread.RED_SPIDER_INPUT_STREAM_READ = redSpiderInputStreamRead;
		for (int i = 0; i < 3; i++) {
			new RedSpiderThread().start();
		}
	}
}
