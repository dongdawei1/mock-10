package com.mock.constant;

import static com.mock.utils.forcheck.PropertiesUtil.getProperty;


public class Constants {
	private static void ________________________杂项sundry________________________() {
	}
	//TestNg执行时获取配置文件中的int值报错或者null返回
	public static final int PROPERTY_INT_NULL_RETURN =-999999999;
	
	private static void ________________________sql________________________() {
	}

	// testng 自动化使用那套环境，本地测试的数据库地址和服务地址
	public static final String TESTNG__ENVIRONMENT = getProperty("env");
	// testng func1是springboot 执行环境，执行结果落测试平台库
	public static final String IS_FUNC1 = "func1";

	private static void ________________________服务探活ServerSound________________________() {
	}
	//  服务名称list
	public static final String[] SOUND_SERVICE_NAME = getListString(getProperty("ServerSound.service_name"), ",");
    //  服务环境list
	public static final String[] SOUND_SERVICE_FUNC = getListString(getProperty("ServerSound.func"), ",");

	/**
	 * 根据分割字符返回 字符串数组，默认“,”
	 */
	public static String[] getListString(String stringList, String sign) {
		if (sign == null) {
			sign = ",";
		}
		return stringList.split(sign);
	}

	public static void main(String[] args) {
		int length = SOUND_SERVICE_NAME.length;
		for (int a = 0; a < length; a++) {
			System.out.println(SOUND_SERVICE_NAME[a]);
		}
	}
}
