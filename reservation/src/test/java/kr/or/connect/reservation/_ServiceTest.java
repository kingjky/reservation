package kr.or.connect.reservation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;

public class _ServiceTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PromotionService promotionService = ac.getBean(PromotionService.class);
		ProductService productService = ac.getBean(ProductService.class);
		CategoryService categoryService = ac.getBean(CategoryService.class);

		List<Category> categories = categoryService.getCategories();
		for (Category category : categories) {
			System.out.println(category);
		}
		System.out.println("============================================================================================================");
		List<Promotion> promotions = promotionService.getPromotions();
		for (Promotion promotion : promotions) {
			System.out.println(promotion);
		}
		System.out.println("============================================================================================================");
		int categoryId = 2;
		int count = productService.getCountUsingCategory(categoryId);
		System.out.println(count);
		List<Product> products = productService.getProductsUsingCategory(categoryId, 0);
		for (Product product : products) {
			System.out.println(product);
		}
	}

}
