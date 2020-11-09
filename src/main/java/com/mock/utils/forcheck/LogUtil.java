package com.mock.utils.forcheck;

import com.mock.constant.reponse.ResponseCode;
import com.mock.constant.reponse.ServerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
	public static void logTiming(ServerResponse<String> result,String msg) {
		if (result.getStatus() == ResponseCode.SUCCESS.getCode()) {
			log.info("{} : 执行成功",msg);
		} else {
			log.info("{} : 执行失败",msg);
		}
	}
}
