package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController extends EntityController {
	private final CategoryService categoryService;

	@Autowired
	public CategoryApiController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getCategories() {
		List<Category> categories = categoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("items", categories);
		return handleSuccess(map);
	}
}
