package com.mock.constant.teseng;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mock.example.service.testngservice.RunTestNgService;
import com.mock.example.service.testngservice.impl.RunTestNgServiceImpl;
import com.mock.utils.forcheck.forcheck.RunnableSupportingThrowingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTestNgPrepare extends BaseTestNg {
	@BeforeClass
	public void beforeClass() {
		log.info("======================================== beforeClass {} ========================================",
				this.getClass().getSimpleName());
	}

	@AfterClass
	public void afterClass() throws Exception {
		log.info("======================================== afterClass: [{}] ========================================",
				this.getClass().getSimpleName());
	}

	public static void waitForCheckoutResult(RunnableSupportingThrowingException runnableSupportingThrowingException,
			Long sleep, int maxRounds) {

		waitForCodeBlockToNotThrowThrowableAndReturnTrue(() -> {
			runnableSupportingThrowingException.run();
			return true;
		}, sleep, maxRounds);

	}

	/**
	 * 是否保存数据
	 */
	public static void waitForCheckoutResultToPreservation(
			RunnableSupportingThrowingException runnableSupportingThrowingException, String name,
			Boolean isPreservation, Boolean isSmoke,String id) {

		try {
			waitForCodeBlockToNotThrowThrowableAndReturnTrue(() -> {
				runnableSupportingThrowingException.run();
				return true;
			}, 100L, 1);

			// 落库
			if (isPreservation) {
				isPreservation(name, isSmoke,id);
			}

		} catch (Exception e) {
			// 落库
			if (isPreservation) {
				isPreservation(name, isSmoke,id);
			}

			// 判断是否是冒烟，冒烟测试停止
			if (isSmoke) {
				throw new SkipException("冒烟测试执行失败:" + name);
			}
		}

	}

	public static void isPreservation(String name, Boolean isSmoke,String id) {
		System.out.println("name" + name);
		System.out.println("isSmoke" + isSmoke);
		System.out.println("id" + id);
		
		
	}



	/**
	 * 重试次数
	 */
	public static void waitForCodeBlockToNotThrowThrowableAndReturnTrue(Callable<Boolean> booleanCallable, Long sleep,
			int maxRounds) {
		int round = 1;
		while (true) {
//          LOGGER.info("========Round [{}]========", round);
			try {
				System.out.println(0);
				if (booleanCallable.call()) { // judge based on the return value
					// LOGGER.info("==============waitForCodeBlockToNotThrowThrowableAndReturnTrue
					// done successfully==============");
					System.out.println(1);
					break;
				} else {
					System.out.println(2);
					if (round == maxRounds) {
						throw new RuntimeException("Timed out waiting for the codeBlockReturnValue to become true!");
					}
					sleep(sleep); // put the sleep in this place to avoid sleeping in vain
					round++;
				}
			} catch (Throwable t) {
				// LOGGER.info("throwable thrown: [{}]", t.getMessage());
				System.out.println(4);
				if (round == maxRounds) {
					System.out.println(5);
					throw new RuntimeException("Timed out waiting for the codeBlock to not throw throwable!", t);
				}

				sleep(sleep); // put the sleep in this place to avoid sleeping in vain
				round++;

			}

		}

	}

	public static void sleep(Long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

}
