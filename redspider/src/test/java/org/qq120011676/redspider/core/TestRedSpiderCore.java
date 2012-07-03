package org.qq120011676.redspider.core;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.qq120011676.redspider.entity.ComputerAddrEnity;
import org.qq120011676.redspider.util.ContentAnalysisUtils;

public class TestRedSpiderCore {

	@Test
	public void saveIp() throws IOException {
		String text = new RedSpiderCore().call(
				"http://www.sooip.cn/guoneidaili/2012-06-26/2881.html", "113.106.48.103", 80);
		List<ComputerAddrEnity> computerAddrs = ContentAnalysisUtils
				.queryComputerAddrEnity(text);
		for (ComputerAddrEnity computerAddrEnity : computerAddrs) {
			System.out.println(computerAddrEnity.getHost());
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
