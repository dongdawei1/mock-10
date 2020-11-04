package com.mock.constant.teseng;

import java.util.concurrent.Callable;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mock.utils.forcheck.forcheck.RunnableSupportingThrowingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTestNgPrepare extends BaseTestNg {
	@BeforeClass
	public void beforeClass() {
		log.info("======================================== beforeClass {} ========================================",
				this.getClass().getSimpleName());
	}

//
//  @BeforeMethod
//  public void beforeMethod(Method testMethod) throws Exception {
//
//  	log.info(format("======================================== beforeMethod of ========================================"));
//
//   
//
//      // LOGGER.info(format("======================================== test: [%s] ========================================", testMethod.getName()));
//
//  }
//
//  @AfterMethod
//  public void afterMethod() throws Exception {
//  	log.info(format("======================================== afterMethod of  ========================================"));
//  }
//
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
	 * 重试次数
	 */
	public static void waitForCodeBlockToNotThrowThrowableAndReturnTrue(Callable<Boolean> booleanCallable, Long sleep,
			int maxRounds) {

		// LOGGER.info("==============waitForCodeBlockToNotThrowThrowableAndReturnTrue==============");

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
