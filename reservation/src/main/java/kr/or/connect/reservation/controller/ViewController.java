package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestScope;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Controller
@RequestMapping(path = "/")
public class ViewController {
	PromotionService promotionService;
	
	@Autowired
	public ViewController(PromotionService promotionService) {
		super();
		this.promotionService = promotionService;
	}
	
	@GetMapping
	public String mainpage(ModelMap model) {
		List<Promotion> promotions = promotionService.getPromotions();
		model.addAttribute("promotions", promotions);
		return "mainpage";
	}
}
