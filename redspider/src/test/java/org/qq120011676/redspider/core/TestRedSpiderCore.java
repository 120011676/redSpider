package org.qq120011676.redspider.core;

import java.io.IOException;

import org.junit.Test;

public class TestRedSpiderCore {

	@Test
	public void test() throws IOException{
		RedSpiderCore core = new RedSpiderCore();
		core.call("http://www.baidu.com");
	}
}
