package kr.or.connect.diexam01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * ApplicationConfig2
 * 내가 직접 작성한 Class 중 Bean으로 관리하고자 하는 것들을 자동으로 Scan하는 방식
 * class 위에 Component, 혹은 Controller, Service, Repository 등등과
 * Autowired를 포함한 Annotation을 사용해 지정해놓으면
 * ComponentScan이 지정된 package 하위를 탐색하며 Bean을 생성 해놓는다.
 */
@Configuration
@ComponentScan("kr.or.connect.diexam01")
public class ApplicationConfig2 {
	
}
