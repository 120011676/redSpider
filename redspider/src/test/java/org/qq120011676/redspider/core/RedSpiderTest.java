package org.qq120011676.redspider.core;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <b>@author</b> Say<br>
 * <b>email</b> 120011676@qq.com<br>
 */
public class RedSpiderTest {

	@Ignore
	@Test
	public void get() throws IOException, JSONException {
		RedSpiderCore redSpiderCore = new RedSpiderCore();
		String r = redSpiderCore.get("http://app.cditv.tv/list-28-1-13.html");
		System.out.println(r);
		System.out.println(new JSONObject(r).getJSONArray("result")
				.getJSONObject(0).getString("title"));
	}
}
