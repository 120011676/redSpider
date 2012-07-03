package org.qq120011676.redspider.util;

import java.util.HashSet;
import java.util.Set;

import org.qq120011676.redspider.entity.ComputerAddrEnity;
import org.qq120011676.snow.util.file.IFile;

public class ProxyUtils implements IFile {

	private static Set<ComputerAddrEnity> COMPUTER_ADDRS = new HashSet<ComputerAddrEnity>();

	public static Set<ComputerAddrEnity> getComputerAddrs() {
		return COMPUTER_ADDRS;
	}

	@Override
	public void readLine(String readLine) {
		ComputerAddrEnity computerAddr = new ComputerAddrEnity();
		int i = readLine.indexOf(" ");
		if (i >= 0) {
			computerAddr.setHost(readLine.substring(0, i));
			computerAddr.setPort(Integer.parseInt(readLine.substring(i + 1,
					readLine.length())));
		} else {
			computerAddr.setHost(readLine);
		}
		COMPUTER_ADDRS.add(computerAddr);
	}
}
