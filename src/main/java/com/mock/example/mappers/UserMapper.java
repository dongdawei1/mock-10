package com.mock.example.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mock.example.entity.User;


@Mapper
public interface UserMapper {
//测试mybatis
//	@Select("select * from t_user")
	List<Map<String, Object>> getall(int type);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(User record);
//
//    int insertSelective(User record);
	
// 根据id 查询出用户信息
    User selectUserById(long id);


  
}