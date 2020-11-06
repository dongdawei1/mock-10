package com.mock.example.service.testngservice.impl.smoke;

import static com.mock.utils.forcheck.mysqlutils.SpringBootMysqlUtils.isPreservation;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mock.constant.teseng.BaseTestNgPrepare;
import com.mock.constant.teseng.TestListenerAdapterImp;
import com.mock.utils.forcheck.forcheck.TestCount;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Listeners(TestListenerAdapterImp.class )
public class Smoke extends BaseTestNgPrepare {
	/**
	 * 
	 * 冒烟测试 有失败的结束执行
	 * */
	@Test
	public static void test1Smoke() {
		log.info("www2222222222222222222222222222222222222222222222222");

	}

	@Test
	public static void test3_TestNgMysql1Smoke() {

		try {

			waitForCheckoutResult(() -> {
				// assertEquals("120", "121");
				SoftAssert assertion = new SoftAssert();
				assertion.assertEquals(5, 6, "我俩不是一样大");
				System.out.println("脚本执行结束");
				System.out.println("我是观望，到这会不会执行的");
				// 执行上边的全部断言 这个必须放到最后，没什么可说的
				assertion.assertAll();

			}, 100L, 5);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			//throw new SkipException("冒烟测试执行失败:test3_TestNgMysql1Smoke");
		}

	}

	@Test
	public static void test1Smoke12() {
		try {

			waitForCheckoutResult(() -> {
				// assertEquals("120", "121");
				SoftAssert assertion = new SoftAssert();
				assertion.assertEquals(5, 5, "我俩不是一样大");
				System.out.println("脚本执行结束");
				System.out.println("我是观望，到这会不会执行的");
				// 执行上边的全部断言 这个必须放到最后，没什么可说的
				assertion.assertAll();

			}, 100L, 5);

		} catch (Exception e) {
			
			//平台执行需要落库
			if (isPreservation()) {
	        //在这里执行落库    TODO
			}
			// TODO: handle exception
			log.info(e.getMessage());
			throw new SkipException("冒烟测试执行失败:test3_TestNgMysql1Smoke");
		}
	}
	
	
	@Test
	public static void test1Smoke122() {
		try {

			waitForCheckoutResult(() -> {
				// assertEquals("120", "121");
				SoftAssert assertion = new SoftAssert();
				assertion.assertEquals(5, 6, "我俩不是一样大");
				System.out.println("脚本执行结束");
				System.out.println("我是观望，到这会不会执行的");
				// 执行上边的全部断言 这个必须放到最后，没什么可说的
				assertion.assertAll();

			}, 100L, 5);

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			throw new SkipException("冒烟测试执行失败:test3_TestNgMysql1Smoke");
		}
	}
	@Test
	public static void test4_TestNgMysql1Smoke() {

		assertEquals("120", "121");

	}

	@Test
	public static void test4_TestNgMysql1Smok() {

		assertEquals("120", "120");

	}
	public static void runAll() {
		test1Smoke();
		test4_TestNgMysql1Smok();
		test3_TestNgMysql1Smoke();
		test1Smoke12();
		test1Smoke122();
		test4_TestNgMysql1Smoke();

	}
	
	
}
