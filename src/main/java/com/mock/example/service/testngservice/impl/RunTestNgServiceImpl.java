package com.mock.example.service.testngservice.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.constant.reponse.ServerResponse;
import com.mock.example.entity.Person;
import com.mock.example.mappers.UserMapper;
import com.mock.example.service.testngservice.RunTestNgService;
import com.mock.example.service.testngservice.impl.smoke.Smoke;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("runTestNgService")
public class RunTestNgServiceImpl implements RunTestNgService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	Person pe;

	public String batchId;

	@Override
	public synchronized ServerResponse<String> runAll() {
		try {
			batchId = new Date().toString();
			Smoke.runAll(batchId);
			//所有测试类的都加runAll()方法
			return ServerResponse.createBySuccessMessage("SUCCESS: "+"执行成功，执行结果请去详情页查看");			
		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("FAIL: "+"执行失败，请联系自动化维护人员");
		}

	}

	@Override
	public String aupguangao(int a) {
		// TODO Auto-generated method stub
		return null;
	}

}
