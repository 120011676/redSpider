package org.qq120011676.redspider.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.qq120011676.redspider.entity.ComputerAddrEnity;

public class ContentAnalysisUtils {

	public static final String IP = "\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}";

	public static final String IP_PORT = IP + "[ ]*\\d{0,5}";

	public static Set<ComputerAddrEnity> queryComputerAddrEnity(String text) {
		Set<ComputerAddrEnity> computerAddrs = new HashSet<ComputerAddrEnity>();
		Pattern pattern = Pattern.compile(IP_PORT);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			ComputerAddrEnity computerAddr = new ComputerAddrEnity();
			computerAddr.setHost(matcher.group());
			computerAddrs.add(computerAddr);
		}
		return computerAddrs;
	}
}
