package org.qq120011676.redspider.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.qq120011676.redspider.entity.ComputerAddrEnity;
import org.qq120011676.redspider.util.ProxyUtils;
import org.qq120011676.redspider.util.RedSpiderURL;
import org.qq120011676.snow.util.FileUtils;

public class RedSpider extends Thread {

	private static final String URL_FILE_NAME = "url.properties";

	private static final String IP_FILE_NAME = "ip.properties";

	private static final String LOG_FILE_NAME = "log.properties";

	@Override
	public void run() {
	}

	public static void runVisit() throws IOException {
		String path = RedSpider.class.getResource("/").getPath();
		FileUtils.fileReader(path + URL_FILE_NAME, new RedSpiderURL());
		FileUtils.fileReader(path + IP_FILE_NAME, new ProxyUtils());
		Iterator<ComputerAddrEnity> iterator = ProxyUtils.getComputerAddrs()
				.iterator();
		while (iterator.hasNext()) {
			ComputerAddrEnity computerAddrEnity = (ComputerAddrEnity) iterator
					.next();
			int code = 0;
			try {
				code = RedSpiderCore.callCode(RedSpiderURL.getURLs().get(0),
						computerAddrEnity.getHost(),
						computerAddrEnity.getPort());
			} catch (Exception e) {
				System.out.println("出错了:" + RedSpiderURL.getURLs().get(0) + " "
						+ computerAddrEnity.getHost() + " "
						+ computerAddrEnity.getPort());
				continue;
			}

			if (code == HttpURLConnection.HTTP_OK) {
				FileUtils.fileWriter(
						path + LOG_FILE_NAME,
						computerAddrEnity.getHost() + " "
								+ computerAddrEnity.getPort() + "\r\n");
			}
		}
	}
}
