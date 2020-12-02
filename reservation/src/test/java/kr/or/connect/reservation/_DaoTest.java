package kr.or.connect.reservation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.CommentImageDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.DisplayInfoImageDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Reservation;

public class _DaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CategoryDao categoryDao = ac.getBean(CategoryDao.class);
		PromotionDao promotionDao = ac.getBean(PromotionDao.class);
		ProductDao productDao = ac.getBean(ProductDao.class);
		CommentDao commentDao = ac.getBean(CommentDao.class);
		CommentImageDao commentImageDao = ac.getBean(CommentImageDao.class);
		DisplayInfoDao displayInfoDao = ac.getBean(DisplayInfoDao.class);
		DisplayInfoImageDao displayInfoImageDao = ac.getBean(DisplayInfoImageDao.class);
		ProductImageDao productImageDao = ac.getBean(ProductImageDao.class);
		ReservationDao reservationDao = ac.getBean(ReservationDao.class);
		
		/*List<Category> categories = categoryDao.selectAll();
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
		List<Product> products = productDao.selectAllWithPagingUsingCategory(categoryId, 0, 4);
		int count = productDao.selectCountUsingCategory(categoryId);
		System.out.println(count);
		for (Product product : products) {
			System.out.println(product);
		}
		System.out.println("================================================ CommentDao Test ============================================================");
		List<Comment> comments = commentDao.selectAllUsingProductId(1);
		for (Comment comment : comments) {
			comment.setCommentImages(commentImageDao.selectAllUsingCommentId(comment.getCommentId()));
			System.out.println(comment);
		}
		
		System.out.println("================================================ CommentImageDao Test ============================================================");
		List<CommentImage> commentImages;
		commentImages = commentImageDao.selectAllUsingCommentId(2);
		for (CommentImage commentImage : commentImages) {
			System.out.println(commentImage);
		}
		commentImages = commentImageDao.selectAllUsingCommentId(3);
		for (CommentImage commentImage : commentImages) {
			System.out.println(commentImage);
		}
		
		System.out.println("================================================ DisplayInfoDao Test ============================================================");
		DisplayInfo displayInfo = displayInfoDao.selectUsingDisplayInfoId(1);
		System.out.println(displayInfo);
		
		System.out.println("================================================ DisplayInfoImageDao Test ============================================================");
		DisplayInfoImage displayInfoImage = displayInfoImageDao.selectUsingDisplayInfoId(1);
		System.out.println(displayInfoImage);
		
		System.out.println("================================================ ProductImageDao Test ============================================================");
		List<ProductImage> productImages = productImageDao.selectAllUsingProductId(1);
		for (ProductImage productImage : productImages) {
			System.out.println(productImage);
		}
		
		System.out.println("================================================ ProductPriceDao Test ============================================================");
		ProductPriceDao productPriceDao = ac.getBean(ProductPriceDao.class);
		List<ProductPrice> productPrices = productPriceDao.selectAllUsingProductId(1);
		for (ProductPrice productPrice : productPrices) {
			System.out.println(productPrice);
		}*/
		
		System.out.println("================================================ ReservationDao Test ============================================================");
		List<Reservation> reservations = reservationDao.selectAllUsingEmail("kimjinsu@connect.co.kr");
		for (Reservation reservation : reservations) {
			reservation.setDisplayInfo(displayInfoDao.selectUsingDisplayInfoId(reservation.getDisplayInfoId()));
			System.out.println(reservation);
		}
	}

}
