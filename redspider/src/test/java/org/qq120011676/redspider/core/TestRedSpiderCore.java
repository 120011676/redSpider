package org.qq120011676.redspider.core;

import java.io.IOException;
import java.util.Set;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

		//
		// http://hzs3.cnzz.com/stat.htm?id=4103253&r=&lg=zh-cn&ntime=1341334151&repeatip=2&rtime=2&cnzz_eid=65205928-1341073796-&showp=1366x768&st=48540&sin=&res=0&rnd=762756669

		// System.out
		// .println(RedSpiderCore
		// .callCode("http://www.51mike.com/idxexedown.do?minor=365&amp;sid=431383&amp;clickid=38616485007",
		// "113.106.48.103", 80));

		// System.out
		// .println(RedSpiderCore
		// .call("http://www.51mike.com/idxexedown.do?minor=365&amp;sid=431383&amp;clickid=38616485007",
		// "221.7.228.138", 80));

		String host = "115.236.98.109";
		int port = 80;
		System.out.println(RedSpiderCore.callCode("http://count.chanet.com.cn/click.cgi?a=431383&d=76014&u=&e=", host,
				port));
		String pathname = "G:\\新建文件夹 (2)\\FreeKaraoke_3323_6.4.0331_minor0.exe";
		if (FileUtils.isFileOrFolder(pathname)) {
			FileUtils.deleteFileOrFolder(pathname);
		}
//		FileUtils
//				.fileStreamWrite(
//						"G:\\新建文件夹 (2)\\FreeKaraoke_3323_6.4.0331_minor0.exe",
//						RedSpiderCore
//								.callInputStream(
//										"http://www.51mike.com/idxexedown.do?minor=365&amp;sid=431383&amp;clickid=38616485007",
//										host, port));
	}

	@Test
	public void web() throws IOException {
		Runtime.getRuntime().exec("explorer.exe http://www.qq.com");
	}

	@Test
	public void js() throws IOException, ScriptException {
		http: // www.51mike.com/idxexedown.do?minor=365&sid=431383&clickid=38616485007
		System.out
				.println(RedSpiderCore
						.call("http://www.51mike.com/idxexedown.do?minor=365&amp;sid=431383&amp;clickid=38616485007"));
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		// ScriptEngine engine =manager.getEngineByExtension("js");
		// engine.eval(RedSpiderCore
		// .call("http://s19.cnzz.com/stat.php?id=4295816&web_id=4295816"));
		// Invocable invocable = (Invocable) engine;
		// invocable.
	}

}
