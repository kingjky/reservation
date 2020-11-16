package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProducts(Integer start) {
		List<Product> products = productDao.selectAll(start, LIMIT);
		return products;
	}

	@Override
	public List<Product> getProductsUsingCategory(Integer categoryId, Integer start) {
		List<Product> products;
		if (categoryId == null || categoryId == 0)
			return getProducts(start);
		products = productDao.selectAllUsingCategory(categoryId, start, LIMIT);
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
}
