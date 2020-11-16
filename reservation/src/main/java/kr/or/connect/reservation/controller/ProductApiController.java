package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController extends EntityController {
	ProductService productService;

	@Autowired
	public ProductApiController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(name = "categoryId", required = false) Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start) throws NoHandlerFoundException {
		List<Product> products = productService.getProductsUsingCategory(categoryId, start);
		Integer totalCount = productService.getCountUsingCategory(categoryId);
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("items", products);
		return handleSuccess(map);
	}
}
