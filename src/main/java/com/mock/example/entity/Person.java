package com.mock.example.entity;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
	
	public  long id;
	@NotEmpty
	public  String  usernam;
	public  String  username;  //用户名
}
