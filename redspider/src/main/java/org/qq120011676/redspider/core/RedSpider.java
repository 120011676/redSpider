package org.qq120011676.redspider.core;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class RedSpider {

	private static class RedSpiderThread extends Thread {

		private volatile static Set<String> URLS;

		private static IRedSpiderInputStreamRead RED_SPIDER_INPUT_STREAM_READ;

		@Override
		public void run() {
			String url = null;
			synchronized (URLS) {
				if (URLS.size() == 0) {
					return;
				}
				Iterator<String> iterator = URLS.iterator();
				if (iterator.hasNext()) {
					url = iterator.next();
					URLS.remove(url);
				}
			}
			try {
				RedSpiderCore.call(url, RED_SPIDER_INPUT_STREAM_READ);
			} catch (IOException e) {
				Logger.getLogger("error:url：" + url);
			} finally {
				this.run();
			}
		}
	}

	public static void runVisit(Set<String> urls, int threadCount,
			IRedSpiderInputStreamRead redSpiderInputStreamRead)
			throws IOException {
		if (threadCount <= 0) {
			throw new RuntimeException("error:threadCount Not less than 0");
		}
		RedSpiderThread.URLS = urls;
		RedSpiderThread.RED_SPIDER_INPUT_STREAM_READ = redSpiderInputStreamRead;
		for (int i = 0; i < threadCount; i++) {
			new RedSpiderThread().start();
		}
	}
}
