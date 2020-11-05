package kr.or.connect.daoexam.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.daoexam.config.ApplicationConfig;
import kr.or.connect.daoexam.dao.RoleDao;
import kr.or.connect.daoexam.dto.Role;

public class JDBCTest {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDao roleDao = ac.getBean(RoleDao.class);
		
		Role role = new Role();
		role.setRoleId(500);
		role.setDescription("PROGRAMMER");
		
//		int count = roleDao.insert(role);
//		System.out.println(count + "건 입력되었습니다.");
		
//		int count = roleDao.update(role);
//		System.out.println(count + "건 수정되었습니다.");
		
		Role resultRole = roleDao.selectById(499);
		System.out.println(resultRole);

		int count = roleDao.deleteById(499);
		System.out.println(count + "건 삭제되었습니다.");
		
		Role resultRole2 = roleDao.selectById(499);
		System.out.println(resultRole2);
	}
}
