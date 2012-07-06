package org.qq120011676.redspider.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class RedSpider {

	private static final String URL_FILE_NAME = "url.properties";

	private static final String IP_FILE_NAME = "ip.properties";

	private static final String LOG_FILE_NAME = "log.properties";

	private static class RedSpiderThread extends Thread {

		private volatile static List<String> URLS;

		private static IRedSpiderInputStreamRead RED_SPIDER_INPUT_STREAM_READ;

		@Override
		public void run() {
			// String url = null;
			// System.out.println(this.getName());
			// // synchronized (URLS) {
			// if (URLS.size() != 0) {
			// url = URLS.get(0);
			// URLS.remove(0);
			// } else {
			// Thread.yield();
			// }
			// // }
			// //System.out.println(this.getName());
			// System.out.println(url);
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
			// try {
			// RED_SPIDER_INPUT_STREAM_READ.readInputStream(RedSpiderCore
			// .callInputStream(url));
			// } catch (IOException e) {
			// e.printStackTrace();
			// } finally {
			// this.run();
			// }
			// this.run();
		}
	}

	public void runVisit(List<String> urls,
			IRedSpiderInputStreamRead redSpiderInputStreamRead)
			throws IOException {
		RedSpiderThread.URLS = urls;
		RedSpiderThread.RED_SPIDER_INPUT_STREAM_READ = redSpiderInputStreamRead;
		for (int i = 0; i < 3; i++) {
			new RedSpiderThread().start();
		}
		// String path = RedSpider.class.getResource("/").getPath();
		//
		// FileUtils.fileReader(path + URL_FILE_NAME, new RedSpiderURL());
		// FileUtils.fileReader(path + IP_FILE_NAME, new ProxyUtils());
		// Iterator<ComputerAddrEnity> iterator = ProxyUtils.getComputerAddrs()
		// .iterator();
		// while (iterator.hasNext()) {
		// ComputerAddrEnity computerAddrEnity = (ComputerAddrEnity) iterator
		// .next();
		// int code = 0;
		// try {
		// code = RedSpiderCore.callCode(RedSpiderURL.getURLs().get(0),
		// computerAddrEnity.getHost(),
		// computerAddrEnity.getPort());
		// } catch (Exception e) {
		// System.out.println("出错了:" + RedSpiderURL.getURLs().get(0) + " "
		// + computerAddrEnity.getHost() + " "
		// + computerAddrEnity.getPort());
		// continue;
		// }
		//
		// if (code == HttpURLConnection.HTTP_OK) {
		// FileUtils.fileWriter(
		// path + LOG_FILE_NAME,
		// computerAddrEnity.getHost() + " "
		// + computerAddrEnity.getPort() + "\r\n");
		// }
		// }
	}
}
