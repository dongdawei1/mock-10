package com.mock.example.service.testngimpl.test1;
import com.mock.constant.teseng.TestListenerAdapterImp;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mock.constant.teseng.BaseTestNgPrepare;
import com.mock.constant.teseng.IHookableImp;
import com.mock.constant.teseng.IReporterImp;
import com.mock.utils.forcheck.forcheck.TestCount;

import lombok.extern.slf4j.Slf4j;
@Slf4j
//@Listeners({TestListenerAdapterImp.class,IHookableImp.class,
//	IReporterImp.class} )
public class TestNgMysql1 extends BaseTestNgPrepare {

	@Test
	public void test1_TestNgMysql1() {
		log.info("www");
		waitForCheckoutResult(() -> {
			assertEquals("121", "121");
		}, 100L, 5);
	}

	
	@Test
	public void test111112222222222_TestNgMysql1() {
		log.info("www");
		waitForCheckoutResult(() -> {
			assertEquals("121122", "121");
		}, 100L, 5);
	}
	
	@Test
	public void test11112_TestNgMysql1() {
		log.info("www");
		waitForCheckoutResult(() -> {
			assertEquals("1211", "1201");
		}, 100L, 5);
	}
	
	@Test
	public void test2_TestNgMysql1() {
		
			assertEquals("121", "121");
		
	}
	
	@Test
	public void test3_TestNgMysql1() {
		
			assertEquals("121", "121");
		
	}
	@Test
	public void test4_TestNgMysql1() {
		
			assertEquals("120", "121");
		
	}
	@Test
	public void test5_TestNgMysql1() {
		
			assertEquals("120", "121");
		
	}
	@Test
	public void test6_TestNgMysql1() {
		
			assertEquals("120", "121");
		
	}
	@Test
	public static void testLambda() {
		System.out.println(invokeCook((int a, int b) -> {
			return a + b;
		}, 1, 2));
	}

	// 定义一个方法 参数传递Cook接口 方法内部调用Cook接口中的makeFood()方法
	public static int invokeCook(TestCount cook, int a, int b) {
		return cook.count(a, b);
	}
}
