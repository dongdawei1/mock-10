package com.mock.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static com.mock.constant.Constants.PROPERTY_INT_NULL_RETURN;

/**
 * 需要导入jar 包
 * 
 * <!-- 处理时间 时间和字符串转化 --> <dependency> <groupId>org.apache.commons</groupId>
 * <artifactId>commons-lang3</artifactId> <version>3.5</version> </dependency>
 * 
 */
@Slf4j
public class PropertiesUtil {

	// 配置redis 基本信息
	private static Properties props;

	static {
		// 源码中没有btea包名，但是找不到
		String fileName = "devtestngproperties/devtestng.properties";
		props = new Properties();
		try {
			props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),
					"UTF-8"));
		} catch (IOException e) {
			log.error("配置文件读取异常", e);
		}
	}

	public static String getProperty(String key) {
		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return value.trim();
	}

	public static int getPropertyInt(String key){
    	String value=getProperty( key);
    	if(value==null || value.equals("")) {
    		return PROPERTY_INT_NULL_RETURN;
    	}
    	try {
    		return Integer.parseInt(getProperty(key));
		} catch (Exception e) {
			return PROPERTY_INT_NULL_RETURN;
		}
    }

	public static String getPropertyTo(String key, String defaultValue) {

		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			value = defaultValue;
		}
		return value.trim();
	}

	public static void main(String[] args) {

		System.out.println(PropertiesUtil.getProperty("redis.ip"));

		// System.out.println(PropertiesUtil.class.getClassLoader().getResourceAsStream("redis.ip"));

	}

}
