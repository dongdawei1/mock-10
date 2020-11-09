package com.mock.example.service.testngservice.impl;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
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

	public String id;
	

	@Override
	public synchronized ServerResponse<String> runAll() {
		log.info("ma :{} ", pe);
//	log.info("ma :{} ",Person.username);
        
		id=new Date().toString();
		
		new Smoke().runAll(id);

		log.info("ma :{} ", "dd");
		log.info("ma :{} ", "dd");
		log.info("ma :{} ", "dd");
		return null;
	}

	@Override
	public String aupguangao(int a) {
		// TODO Auto-generated method stub
		return null;
	}

}
