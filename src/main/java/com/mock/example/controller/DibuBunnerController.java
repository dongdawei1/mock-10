package com.mock.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mock.example.service.BunnerService;




//http://localhost:8083/bunner/getpguang   127

/**
 * 
 * 
 * 
 * 
 * 
 * testNg   开始执行之前获取 数据库连接，运行spring  用mybatis
 * 
 * 
 * 
 * 
 * 
 * */



@Controller
@RequestMapping("bunner/")
public class DibuBunnerController {
	//http://localhost:8080/bunner/getpguang   127
	@Autowired
	
	BunnerService bunnerService;
	
	@RequestMapping(value = "getpguang", method = RequestMethod.GET)
	@ResponseBody
	public String getpguang(HttpServletRequest httpServletRequest) {
		
        String appid= httpServletRequest.getHeader("appid");
        System.out.println("DibuBunnerController.getpguang()"+appid);
       // return ServerResponse.createBySuccess("aa");
		return bunnerService.aupguangao(12);

	}
}
