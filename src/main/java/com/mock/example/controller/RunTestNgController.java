package com.mock.example.controller;


import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mock.constant.reponse.ServerResponse;
import com.mock.example.service.testngservice.RunTestNgService;
import  com.mock.constant.Constants;



//http://localhost:8083/api/v1/automatic/runTest/runAll

/**
 * testNg   开始执行之前获取 数据库连接，运行spring  用mybatis
 * */



@Controller
@RequestMapping(Constants.AUTOMATIC+"runTest/")
public class RunTestNgController {
	//http://localhost:8083/api/v1/automatic/runTest/runAll
	@Autowired	
	RunTestNgService runTestNgService;
	
	@RequestMapping(value = "runAll", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> runAll(HttpServletRequest httpServletRequest) {
		
       // String appid= httpServletRequest.getHeader("appid");
        //System.out.println("DibuBunnerController.getpguang()"+appid);
       // return ServerResponse.createBySuccess("aa");
		return runTestNgService.runAll();

	}
	
	
	
	public static void main(String[] args) {
		String sjiachun = "1.020436E8"; 
		BigDecimal db = new BigDecimal(sjiachun); 
		String ii = db.toPlainString(); 
		System.out.println(ii);
	}
}
