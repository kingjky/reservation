package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;

public interface ProductService {
	int PRODUCT_PAGING_ROWS = 4;

	List<Product> getProducts(Integer start);

	List<Product> getProductsUsingCategory(Integer categoryId, Integer start);

	int getCount();

	int getCountUsingCategory(Integer categoryId);

	List<ProductImage> getProductImagesUsingProductId(Long productId);

	List<ProductPrice> getProductPricesUsingProductId(Long productId);
}
