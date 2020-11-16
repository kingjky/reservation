package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import kr.or.connect.reservation.controller.EntityController;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayService;
import kr.or.connect.reservation.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController extends EntityController {
	private final ProductService productService;
	private final CommentService commentService;
	private final DisplayService displayService;
	
	@Autowired
	public ProductApiController(ProductService productService, CommentService commentService,
		DisplayService displayService) {
		this.productService = productService;
		this.commentService = commentService;
		this.displayService = displayService;
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
	
	@GetMapping("/{displayInfoId}")
	public ResponseEntity<Map<String, Object>> getProductUsingDisplayInfoId(@PathVariable Integer displayInfoId) throws NoHandlerFoundException {
		DisplayInfo displayInfo = displayService.getDisplayInfoUsingDisplayInfoId(displayInfoId);
		DisplayInfoImage displayInfoImage = displayService.getDisplayInfoImageUsingDisplayInfoId(displayInfoId);
		int productId = displayInfo.getProductId();
		List<ProductImage> productImages = productService.getProductImagesUsingProductId(productId);
		List<ProductPrice> productPrices = productService.getProductPricesUsingProductId(productId);
		List<Comment> comments = commentService.getCommentsWithPagingUsingProductId(productId, 0, 3);
		Double averageScore = commentService.getAverageScore(comments);
		
		Map<String, Object> map = new HashMap<>();
		map.put("averageScore", averageScore);
		map.put("comments", comments);
		map.put("displayInfo", displayInfo);
		map.put("displayInfoImage", displayInfoImage);
		map.put("productImages", productImages);
		map.put("productPrices", productPrices);
		return handleSuccess(map);
	}
}
