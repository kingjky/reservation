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

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController extends EntityController {
	private final PromotionService promotionService;

	@Autowired
	public PromotionApiController(PromotionService promotionService) {
		super();
		this.promotionService = promotionService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getPromotions() {
		List<Promotion> promotions = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		map.put("items", promotions);
		return handleSuccess(map);
	}
}
