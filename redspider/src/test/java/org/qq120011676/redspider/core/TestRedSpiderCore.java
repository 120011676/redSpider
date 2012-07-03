package org.qq120011676.redspider.core;

import java.io.IOException;

import org.junit.Test;

public class TestRedSpiderCore {

	@Test
	public void test() throws IOException {
		// RedSpiderCore core = new RedSpiderCore();
		// String code = core.call("http://www.baidu.com");
		// System.out.println(code);
		System.out.println(new RedSpiderCore().callCode(
				"http://222.211.85.18:9004/", "113.106.48.103", 80));
	}
}
