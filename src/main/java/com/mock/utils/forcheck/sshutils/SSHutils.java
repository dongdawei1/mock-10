package com.mock.utils.forcheck.sshutils;

import static com.mock.utils.forcheck.PropertiesUtil.getProperty;
import java.io.IOException;
import java.io.InputStreamReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStream;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SSHutils {

	/***
	 * linux 连接根据不同环境快速诊断 主流程哪里fail
	 */
	private static Connection sshConnection;
	private static Session sshSession;
	private static String line;

	private SSHutils() {
	}

	@Test
	public static void checkCountTest() {

		String command = "grep -c 2020-10-26T14:26:05* /home/xiaoju/logs/manhattan/manhattan-oppenheimer-hulk/manhattan-oppenheimer-hulk-biz.log";
		// String command = "tail -f
		// /home/xiaoju/logs/manhattan/manhattan-oppenheimer-hulk/manhattan-oppenheimer-hulk-biz.log";
		//log.info("{}", checkCount("func1","name", command, 2));
		Assert.assertTrue(checkCount("func1","name", command, 2));
	}

	/***
	 * 检查总数
	 */
	public static boolean checkCount(String env,String serviceName, String command, int count) {
		createSession(env, command,serviceName);
		return checkLine(count);
	}

	/***
	 * 不检查总数
	 */
	public static void checkNoCount(String env, String command,String serviceName) {
		createSession(env, command,serviceName);
	}

	/***
	 * 环境String env, 命令 String command(只支持单条命令 多条用; 或者 &&分割),String serviceName  服务名 （多环境多服务）
	 */
	public static void createSession(String func, String command,String serviceName) {

		String service_ip_port = getProperty(func + "_service_"+serviceName+"_ip");
		String service_username = getProperty(func + "_service_"+serviceName+"_username");
		String service_password = getProperty(func + "_service_"+serviceName+"_password");

		try {
			sshConnection = new Connection(service_ip_port);
			sshConnection.connect();
			sshConnection.authenticateWithPassword(service_username, service_password);

			sshSession = sshConnection.openSession();
			sshSession.execCommand(command);
			// 获得输出流
			InputStream stdout = new StreamGobbler(sshSession.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			int a = 0;

			while (true) {
				// 只取一行
				if (a > 0) {
					break;
				}
				line = br.readLine();
				a++;
				if (line == null) {
					break;
				}
			}

//			if (sshSession.getExitStatus() == 0) {
//				log.info("执行成功  {}", env);
//			} else {
//				log.error("执行失败 {}", env);
//			}
			sshSession.close();
			sshConnection.close();

		} catch (IOException e) {
			log.error("连接错误 {}", func);
		}

	}

	/***
	 * 总条数比对
	 */
	public static boolean checkLine(int count) {
		int intline;
		try {
			intline = Integer.parseInt(line);
		} catch (Exception e) {
			log.error("结果转数字错误 {}", line);
			return false;
		}
		if (intline == count) {
			return true;
		}
		return false;
	}
}
