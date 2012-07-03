package org.qq120011676.redspider.entity;

public class ComputerAddrEnity {

	private String host;

	private int port;

	private String username;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return true;
		} else if (this == obj) {
			return true;
		} else if (obj instanceof ComputerAddrEnity) {
			ComputerAddrEnity computerAddr = (ComputerAddrEnity) obj;
			if (computerAddr.getHost() != null
					&& !("".equals(computerAddr.getHost().trim()))
					&& computerAddr.getHost().equals(this.host)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.host.hashCode();
	}

	private String password;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
