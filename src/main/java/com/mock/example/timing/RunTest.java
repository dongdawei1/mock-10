package com.mock.example.timing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mock.constant.reponse.ServerResponse;
import com.mock.example.service.testngservice.RunTestNgService;
import com.mock.utils.LogUtil;


@Component
public class RunTest {

	@Autowired
	RunTestNgService runTestNgService;

	// 每天00点00分01秒时执行,执行自动化测试
	@Scheduled(cron = "01 00 00 * * ?")
	public void runTestAll() {
		ServerResponse<String> result = runTestNgService.runAll();

		LogUtil.logTiming(result, "定时任务全部测试case");
	}

	// 每隔5分钟执行一次
	// @Scheduled(fixedRate = 1000 * 60 * 5)
	public void timerSelsetPayOrder() {
		ServerResponse<String> result = runTestNgService.runAll();
		LogUtil.logTiming(result, "定时任务某某");
	}
}
