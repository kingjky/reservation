package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping
	public Map<String, Object> getProduct(@RequestParam(name = "categoryId", required = false) Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start) {
		List<Product> products = productService.getProductsUsingCategory(categoryId, start);
		Integer totalCount = productService.getCountUsingCategory(categoryId);
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("items", products);
		return map;
	}
}
