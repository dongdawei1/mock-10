package com.mock.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.mock.constant.Constants;

public class FileUtil {

	private static String slash="/";
	private static String oldFileName="old";
	public static void write(String env,String moduleName , String fileName,List<String> lines) {
		try {
			FileUtils.writeLines(new File(Constants.TEMPORARY_PATH+env+slash+moduleName+slash+fileName),lines,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void copy(String env,String moduleName , String fileName, String oldFileName) {
		try {
			//FileUtils.copyFile(new File("d:/cxyapi.xml"), new File("d:/cxyapi.xml.bak")); 
			FileUtils.copyFile(new File(Constants.TEMPORARY_PATH+env+slash+moduleName+slash+fileName),new File(Constants.TEMPORARY_PATH+env+slash+moduleName+slash+oldFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void delete(String env,String moduleName , String fileName) {
			FileUtils.deleteQuietly(new File(Constants.TEMPORARY_PATH+env+slash+moduleName+slash+fileName));
	}
	public static void createConfig(String env,String moduleName , String fileName,List<String> lines) {
		copy(env,moduleName,fileName,oldFileName+fileName);		
		write(env,moduleName,fileName,lines);
		delete(env,moduleName,oldFileName+fileName);
	}
	
	public static void main(String[] args) {
		List<String> lines=new ArrayList<String>();
		lines.add("欢迎访问11111111:");
		lines.add("ww2222w.cxyapi.com");
		createConfig("env2","manhattan-oppenheimer-product" , "application-test.yaml",lines);
	}
}
