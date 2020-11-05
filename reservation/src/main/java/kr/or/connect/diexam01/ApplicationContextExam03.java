package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * 이번에는 xml이 아니다
 * ApplicationConfig라는 Class를 통해 xml을 대체하는 설정을 해주었다
 * ApplicationConfig 내부 메소드의 구성을 살펴보면
 * 	그저 xml 설정과 동일한 작업을 java 코드를 통해 했다는 것을 알 수 있다
 */
public class ApplicationContextExam03 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		Car car = ac.getBean(Car.class);
		car.run();
	}
}
