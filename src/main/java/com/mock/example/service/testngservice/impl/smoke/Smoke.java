package com.mock.example.service.testngservice.impl.smoke;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.mock.constant.teseng.BaseTestNgPrepare;
import com.mock.constant.teseng.TestListenerAdapterImp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Listeners(TestListenerAdapterImp.class)
/**
 * 1、testNg正常执行，如果是冒烟 加@Listeners(TestListenerAdapterImp.class)，执行失败后会中断
 * 2、如果是SpringBoot定时任务或者前端触发 ，只需正常写case，然后加到runAll 方法中，waitForCheckoutResultToPreservation方法会根据入参判断是否中断执行case或者落库
 */
public class Smoke extends BaseTestNgPrepare {
	@Test
	public static void test1Smoke() {
		log.info("www2222222222222222222222222222222222222222222222222");

	}

	@Test
	public static void test1Smoke1123() {
		//spring执行时才生效，TestNg执行不生效，依然靠监听判断是否中断
		waitForCheckoutResult(() -> {
		assertEquals(1, 2);
		}, 100L, 5);
	}

//	@Test
//	public static void test3_TestNgMysql1Smoke() {
//
//		waitForCheckoutResult(() -> {
//			// assertEquals("120", "121");
//			SoftAssert assertion = new SoftAssert();
//			assertion.assertEquals(5, 6, "我俩不是一样大");
//			System.out.println("脚本执行结束");
//			System.out.println("我是观望，到这会不会执行的");
//			// 执行上边的全部断言 这个必须放到最后，没什么可说的
//			assertion.assertAll();
//
//		}, 100L, 5);
//
//	}

//	@Test
//	public static void test1Smoke12() {
//
//		waitForCheckoutResult(() -> {
//			// assertEquals("120", "121");
//			SoftAssert assertion = new SoftAssert();
//			assertion.assertEquals(5, 5, "我俩不是一样大");
//			System.out.println("脚本执行结束");
//			System.out.println("我是观望，到这会不会执行的");
//			// 执行上边的全部断言 这个必须放到最后，没什么可说的
//			assertion.assertAll();
//
//		}, 100L, 5);
//
//	}

//	@Test
//	public static void test1Smoke122() {
//	
//
//			waitForCheckoutResult(() -> {
//				// assertEquals("120", "121");
//				SoftAssert assertion = new SoftAssert();
//				assertion.assertEquals(5, 6, "我俩不是一样大");
//				System.out.println("脚本执行结束");
//				System.out.println("我是观望，到这会不会执行的");
//				// 执行上边的全部断言 这个必须放到最后，没什么可说的
//				assertion.assertAll();
//
//			}, 100L, 5);
//
//	}
	@Test
	public static void test4_TestNgMysql1Smoke() {

		
		
			assertEquals("120", "121");
			

	}

	@Test
	public static void test4_TestNgMysql1Smok() {

		assertEquals("120", "120");

	}

	public static void runAll( String batchId) {
		
		waitForCheckoutResultToPreservation(() -> {
			test1Smoke();

		}, "test1Smoke", true, true,batchId);

		waitForCheckoutResultToPreservation(() -> {
			test1Smoke();

		}, "test1Smoke", true, true,batchId);

		
		waitForCheckoutResultToPreservation(() -> {
			test1Smoke1123();

		}, "test1Smoke1123", true, false,batchId);

		waitForCheckoutResultToPreservation(() -> {
			test4_TestNgMysql1Smok();

		}, "test1Smoke1123", true, false,batchId);
		waitForCheckoutResultToPreservation(() -> {
			test4_TestNgMysql1Smoke();

		}, "test1Smoke1123", true, false,batchId);
		
	

	}

}
