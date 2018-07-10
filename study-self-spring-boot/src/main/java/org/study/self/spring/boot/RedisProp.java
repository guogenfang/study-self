package org.study.self.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("redis")
public class RedisProp {
	private String host;
	private Integer port;
	private String pwd;
	private Integer maxIdle;
	private Integer maxTotal;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	
	@Override
	public String toString() {
		return "RedisProp [host=" + host + ", port=" + port + ", pwd=" + pwd + ", maxIdle=" + maxIdle + ", maxTotal="
				+ maxTotal + "]";
	}
	
}
