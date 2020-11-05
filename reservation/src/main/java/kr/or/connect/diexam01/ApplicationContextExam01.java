package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * xml 방식으로 설정된 ApplicationContext 객체를 생성한다
 * ac는 생성됨과 동시에 xml 내부에 Bean으로 설정된 녀석들의 객체를 singletone으로 생성해 쥐고 있고
 * 이곳에서 getBean을 통해 요청하면 가져올 수 있다
 */
public class ApplicationContextExam01 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("초기화 완료!");
		
		UserBean userBean = (UserBean)ac.getBean("userBean");
		userBean.setName("kang");
		
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean)ac.getBean("userBean");
		
		System.out.println(userBean == userBean2);
	}

}
