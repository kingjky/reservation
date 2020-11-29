package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Controller
public class ViewController {
	PromotionService promotionService;

	public ViewController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@GetMapping(path = "/")
	public String mainpage(ModelMap model) {
		List<Promotion> promotions = promotionService.getPromotions();
		model.addAttribute("promotions", promotions);
		return "mainpage";
	}
}
