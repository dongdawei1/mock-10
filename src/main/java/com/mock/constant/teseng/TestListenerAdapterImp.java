package com.mock.constant.teseng;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;

import lombok.extern.slf4j.Slf4j;

import static com.mock.utils.mysqlutils.SpringBootMysqlUtils.isPreservation;
@Slf4j
public class TestListenerAdapterImp extends TestListenerAdapter {
	/**
	 * 获取每个执行方法的结果，冒烟使用如果有失败和跳过停止运行
	 */



	@Override
	public void onTestFailure(ITestResult tr) {
		skipException(tr.getName()+"Run mode for Failure");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		skipException(tr.getName()+"Run mode for Skipped");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// sys(tr.getName()+ "--Test method success\n");
	}

	private void skipException(String string) {
		// 直接中断执行所有套件
		throw new SkipException("冒烟测试执行失败:"+string);
	
	}

}