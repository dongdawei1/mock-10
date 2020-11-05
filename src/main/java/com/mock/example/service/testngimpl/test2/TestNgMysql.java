package com.mock.example.service.testngimpl.test2;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mock.constant.teseng.BaseTestNgPrepare;
import com.mock.utils.forcheck.forcheck.TestCount;
import com.mock.constant.teseng.IHookableImp;
import lombok.extern.slf4j.Slf4j;
@Slf4j
//@Listeners(IHookableImp.class )
public class TestNgMysql extends BaseTestNgPrepare {

	@Test
	public void test1() {
		log.info("www2222222222222222");
	
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
