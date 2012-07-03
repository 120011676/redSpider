package org.qq120011676.redspider.core;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.qq120011676.redspider.entity.ComputerAddrEnity;
import org.qq120011676.redspider.util.ContentAnalysisUtils;
import org.qq120011676.redspider.util.ProxyUtils;
import org.qq120011676.snow.util.FileUtils;

public class TestRedSpiderCore {

	private static final String FILE_NAME = "ip.properties";

	@Test
	public void saveIp() throws IOException {
		String text = new RedSpiderCore().call(
				"http://www.sooip.cn/guoneidaili/2012-06-26/2881.html",
				"113.106.48.103", 80);
		Set<ComputerAddrEnity> computerAddrs = ContentAnalysisUtils
				.queryComputerAddrEnity(text);
		String pathname = this.getClass().getResource("/").getPath()
				+ FILE_NAME;
		if (FileUtils.isFileOrFolder(pathname)) {
			FileUtils.createFolder(pathname);
		}
		for (ComputerAddrEnity computerAddrEnity : computerAddrs) {
			FileUtils.fileWriter(pathname, computerAddrEnity.getHost() + " "
					+ computerAddrEnity.getPort() + "\r\n");
		}
	}

	@Test
	public void dq() throws IOException {
		FileUtils.fileReader(this.getClass().getResource("/").getPath()
				+ FILE_NAME, new ProxyUtils());
		for (ComputerAddrEnity computerAddrEnity : ProxyUtils
				.getComputerAddrs()) {
			System.out.println(computerAddrEnity.getHost() + " "
					+ computerAddrEnity.getPort());
		}
	}

	@Test
	public void test() throws IOException {
		// RedSpiderCore core = new RedSpiderCore();
		// String code = core.call("http://www.baidu.com");
		// System.out.println(code);
		System.out.println(new RedSpiderCore().callCode(
				"http://222.211.85.18:9004/", "113.106.48.103", 80));
	}

}
