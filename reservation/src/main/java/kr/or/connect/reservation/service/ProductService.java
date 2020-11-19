package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<Product> getProducts(Integer start);

	public List<Product> getProductsUsingCategory(Integer categoryId, Integer start);

	public int getCount();

	public int getCountUsingCategory(Integer categoryId);
	
	public List<ProductImage> getProductImagesUsingProductId(Integer productId);
	
	public List<ProductPrice> getProductPricesUsingProductId(Integer productId);
}
