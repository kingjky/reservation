package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final ProductImageDao productImageDao;
	private final ProductPriceDao productPriceDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao, ProductImageDao productImageDao, ProductPriceDao productPriceDao) {
		this.productDao = productDao;
		this.productImageDao = productImageDao;
		this.productPriceDao = productPriceDao;
	}

	@Override
	public List<Product> getProducts(Integer start) {
		List<Product> products = productDao.selectAllWithPaging(start, LIMIT);
		return products;
	}

	@Override
	public List<Product> getProductsUsingCategory(Integer categoryId, Integer start) {
		List<Product> products;
		if (categoryId == null || categoryId == 0)
			return getProducts(start);
		products = productDao.selectAllWithPagingUsingCategory(categoryId, start, LIMIT);
		return products;
	}

	@Override
	public int getCount() {
		return productDao.selectCount();
	}

	@Override
	public int getCountUsingCategory(Integer categoryId) {
		if (categoryId == null || categoryId == 0)
			return getCount();
		return productDao.selectCountUsingCategory(categoryId);
	}

	@Override
	public List<ProductImage> getProductImagesUsingProductId(Integer productId) {
		List<ProductImage> productImages = productImageDao.selectAllUsingProductId(productId);
		return productImages;
	}

	@Override
	public List<ProductPrice> getProductPricesUsingProductId(Integer productId) {
		List<ProductPrice> productPrices = productPriceDao.selectAllUsingProductId(productId);
		return productPrices;
	}

}
