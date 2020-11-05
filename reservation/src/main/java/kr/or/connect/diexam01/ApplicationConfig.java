package kr.or.connect.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * ApplicationConfig
 * ComponentScan으로 탐색할 수 없지만 Bean으로 관리해야 하는 객체들
 * 		ex) Spring JDBC 관련.. 
 * 			-> 내가 직접 만들지 않아서 수정할 수 없는 것들
 * 을 수동? 으로 등록하기 위한 방법
 */
@Configuration
public class ApplicationConfig {
	@Bean
	public Car car(Engine e) {
		Car c = new Car();
		c.setEngine(e);
		return c;
	};
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
}
