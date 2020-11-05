package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * 이번엔 ApplicationConfig2다
 * ApplicationConfig2에서는 단지 ComponentScan을 달았을 뿐이다.
 * 그리고 Bean으로 등록할 Car와 Engine에 각각 @Component 를 달아주었고
 * Car의 내부 의존성인 Engine을 Autowired로 등록해줬다
 * 결과적으로 알아서 등록이 완료되어 car.run() 이 정상 실행되었다.
 * 
 * getBean을 id가 아닌 Car.class로 요청하니 자동으로 Car로 형변환된 객체를 받아온다
 */
public class ApplicationContextExam04 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig2.class);

		Car car = ac.getBean(Car.class);
		car.run();
	}
}
