package com.mock.utils.mysqlutils;

import static com.mock.constant.Constants.IS_FUNC1;
import static com.mock.constant.Constants.TESTNG__ENVIRONMENT;

public class SpringBootMysqlUtils {
	/**
	 * 判断是执行testng还是springboot
	 */
	public static boolean isPreservation() {
		if (IS_FUNC1.equals(TESTNG__ENVIRONMENT)) {
			return true;
		}
		return false;
	}
}
