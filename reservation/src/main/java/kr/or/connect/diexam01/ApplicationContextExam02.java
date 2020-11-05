package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 마찬가지로 xml으로 설정된 ApplicationContext를 생성한다
 * 여기서 주목할 점은 xml의 설정 부분인데
 * car와 engine Class를 각각 Bean으로 설정하면서
 * Car의 property에 engine을 담는 것까지 설정 사항에 입력했다
 * 똑똑한 ac는 그 engine을 car에 DI 시켜줬고,
 * car.run() 을 호출하자 내부에 engine이 주입되어 있음을 확인할 수 있었다.
 */
public class ApplicationContextExam02 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Car car = (Car) ac.getBean("c");
		car.run();
	}

}
