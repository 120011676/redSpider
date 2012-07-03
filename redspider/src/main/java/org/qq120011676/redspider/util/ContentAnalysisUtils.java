package org.qq120011676.redspider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.qq120011676.redspider.entity.ComputerAddrEnity;

public class ContentAnalysisUtils {

	private static final String IP = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

	private static final String IP_PORT = IP + " ";

	public static List<ComputerAddrEnity> queryComputerAddrEnity(String text) {
		List<ComputerAddrEnity> computerAddrs = new ArrayList<ComputerAddrEnity>();
		Pattern pattern = Pattern.compile(IP);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			ComputerAddrEnity computerAddr = new ComputerAddrEnity();
			computerAddr.setHost(matcher.group());
			computerAddrs.add(computerAddr);
		}
		return computerAddrs;
	}
}
