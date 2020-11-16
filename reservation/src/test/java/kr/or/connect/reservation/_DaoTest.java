package kr.or.connect.reservation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

public class _DaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao categoryDao = ac.getBean(CategoryDao.class);
		PromotionDao promotionDao = ac.getBean(PromotionDao.class);
		ProductDao productDao = ac.getBean(ProductDao.class);
		
		List<Category> categories = categoryDao.selectAll();
		for (Category category : categories) {
			System.out.println(category);
		}
		System.out.println("============================================================================================================");
		List<Promotion> promotions = promotionDao.selectAll();
		for (Promotion promotion : promotions) {
			System.out.println(promotion);
		}
		System.out.println("============================================================================================================");
		int categoryId = 1;
		List<Product> products = productDao.selectAllUsingCategory(categoryId, 0, 4);
		int count = productDao.selectCountUsingCategory(categoryId);
		System.out.println(count);
		for (Product product : products) {
			System.out.println(product);
		}
	}

}
