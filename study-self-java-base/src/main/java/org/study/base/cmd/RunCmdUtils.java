package org.study.base.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RunCmdUtils {

	public static void main(String[] args) {
		String ip = "192.168.62.103";
		String name = "ggf";
		String pwd = "123";
		String dir = "/home";
		
		String fstr = RunCmdUtils.runLinuxCmd("192.168.62.103", "ggf", "123", "whoami");
		System.out.println(fstr);
		
		String total = RunCmdUtils.runLinuxCmd(ip,name,pwd,"df -h -P " + dir + " | awk 'NR==2 {print $2}'");
		String used = RunCmdUtils.runLinuxCmd(ip,name,pwd,"df -h -P " + dir + " | awk 'NR==2 {print $3}'");
		String avail = RunCmdUtils.runLinuxCmd(ip,name,pwd,"df -h -P " + dir + " | awk 'NR==2 {print $4}'");
	
		System.out.println(total);
		System.out.println(used);
		System.out.println(avail);
	}

	/**
	 * 运行远程linux 命令
	 * 
	 * @param hostname
	 * @param username
	 * @param password
	 * @param cmd
	 */
	public static String runLinuxCmd(String hostname, String username, String password, String cmd) {
		// 指明连接主机的IP地址
		Connection conn = new Connection(hostname);
		Session ssh = null;
		StringBuffer sb = new StringBuffer();
		try {
			// 连接到主机
			conn.connect();
			// 使用用户名和密码校验
			boolean isconn = conn.authenticateWithPassword(username, password);
			if (!isconn) {
				return ConstantUtils.STORE_NAME_OR_PWD_FAILED_STR;
			} else {
				ssh = conn.openSession();
				// 使用多个命令用分号隔开
				ssh.execCommand(cmd);
				// 将屏幕上的文字全部打印出来
				InputStream is = new StreamGobbler(ssh.getStdout());
				BufferedReader brs = new BufferedReader(new InputStreamReader(is));
				while (true) {
					String line = brs.readLine();
					if (line == null) {
						break;
					}
					sb.append(line);
				}
			}
			// 连接的Session和Connection对象都需要关闭
			ssh.close();
			conn.close();

		} catch (IOException e) {
			e.printStackTrace();
			return ConstantUtils.STORE_IP_FAILED_STR;
		}
		return sb.toString();
	}
}
