package com.edu;

import com.edu.dao.EmployeeMapper;
import com.edu.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot05CrudApplicationTests {

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate; // 操作字符串

	@Autowired
	RedisTemplate redisTemplate; // k-v
	
	

	/**
	 * Redis常见的五大数据类型
	 *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
	 *  stringRedisTemplate.opsForValue()[String（字符串）]
	 *  stringRedisTemplate.opsForList()[List（列表）]
	 *  stringRedisTemplate.opsForSet()[Set（集合）]
	 *  stringRedisTemplate.opsForHash()[Hash（散列）]
	 *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
	 */
	@Test
	public void test01(){
//		stringRedisTemplate.opsForValue().append("msg","hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
	}
	
	@Test
	public void test02(){
		Employee employee = employeeMapper.selectByPrimaryKey(1);
		// 默认保存对象，使用jdk序列化机制
//		redisTemplate.opsForValue().set("emp-01",employee);
		//1、将数据以json的方式保存
		//(1)自己将对象转为json
		//(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
//		empRedisTemplate.opsForValue().set("emp-01",employee);
	}
	
	@Test
	public void contextLoads() throws SQLException {

		System.out.println(dataSource.getClass());

		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}

}
