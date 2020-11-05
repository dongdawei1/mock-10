package com.mock.constant.teseng;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

public class IHookableImp implements IHookable {

	/**
	 * 在类上注解使用即可@Listeners(IHookableImp.class)
	 * 
	 * 侦听每个@Test 测试method是 test1 开始执行~ 16:58:59.826 [main] INFO
	 * case日志com.mock.example.service.testngimpl.test2.TestNgMysql -
	 * www2222222222222222 结束~
	 */
    //获取执行方法
	@Override
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
		ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();

		String name = method.getName();

		System.out.println("测试method是 " + name);
		System.out.println("开始执行~");
		// 测试用例开始执行
		iHookCallBack.runTestMethod(iTestResult);
		System.out.println("结束~");
	}

	
}
