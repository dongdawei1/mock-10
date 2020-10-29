package com.mock.example.service.impl;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.example.entity.Person;
import com.mock.example.mappers.UserMapper;
import com.mock.example.service.BunnerService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("bunnerService")
public class BunnerServiceImpl implements BunnerService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	Person pe;
	
	@Override
	public String aupguangao(int a) {
		
		log.info("ma :{} ", pe);
	//	log.info("ma :{} ",Person.username);
		
	aupguangao1();
	List<Map<String, Object>>   list= userMapper.getall(1);
	
	for (int i = 0; i < list.size(); i++) {
		Map<String, Object> map=list.get(i);
		
		
		//log.error("setex key:{} value:{} error", key, value, e);
//		log.info("ma :{} ",map.toString(),i);
//		log.trace("trace :{} ",map.toString(),i);
//		log.debug("debug :{} ",map.toString(),i);
//		log.warn("warn :{} ",map.toString(),i);
//		log.error("error :{} ",map.toString(),i);
	}
		return "success";
	}

	
	
	

@Test
public static void aupguangao1() {
	log.info("sss");
	
}





}
