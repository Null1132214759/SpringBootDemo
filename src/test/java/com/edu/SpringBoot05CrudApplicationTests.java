package com.edu;

import com.edu.dao.EmployeeMapper;
import com.edu.entity.Department;
import com.edu.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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


	/**
	 * RabbitMQ 测试
	 * docker 拉取 RabbitMQ 的镜像
	 * docker run -d -p 5672:5672 -p 15672:15672 --name myrabbitmq d69a5113ceae
	 * 5672 为 RabbitMQ 占用端口，15672 为 Rabbit Management 占用端口
	 * 
	 */
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	// 单播测试
	@Test
	public void DirectTest() {
		//Message需要自己构造一个;定义消息体内容和消息头
		//rabbitTemplate.send(exchage,routeKey,message);

		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
		//rabbitTemplate.convertAndSend(exchange,routeKey,object);
		Map<String, Object> map = new HashMap<>();
		map.put("msg", " 这是第一条消息！");
		map.put("data", Arrays.asList("Hello World", 123, true));
//		rabbitTemplate.convertAndSend("exchange.direct", "atguigu", map);
		Department department = new Department();
		department.setId(2);
		department.setDepartmentName("开发部");
		// 对象默认序列化以后发送出去
		rabbitTemplate.convertAndSend("exchange.direct", "atguigu", department);
	}
	
	//接受数据， 自动反序列化
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("atguigu");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	// 广播测试
	@Test
	public void FanoutTest(){
		Department department = new Department();
		department.setId(3);
		department.setDepartmentName("行政部");
		// routingKey 可留空
		rabbitTemplate.convertAndSend("exchange.fanout","", department);
	}

}
