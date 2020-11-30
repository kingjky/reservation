package kr.or.connect.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.service.PromotionService;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ViewController {
	private final PromotionService promotionService;
	private final ReservationService reservationService;

	public ViewController(PromotionService promotionService, ReservationService reservationService) {
		this.promotionService = promotionService;
		this.reservationService = reservationService;
	}

	@GetMapping(path = "/")
	public String mainpage(ModelMap model) {
		List<Promotion> promotions = promotionService.getPromotions();
		model.addAttribute("promotions", promotions);
		return "mainpage";
	}

	@GetMapping(path = "/login")
	public String login(
		@RequestParam(name = "reservationEmail", required = false) String reservationEmail,
		HttpSession session) {
		if (reservationEmail != null) {
			List<Reservation> reservations = reservationService.getReservationsUsingEmail(reservationEmail);
			if (reservations.size() > 0) {
				session.setAttribute("email", reservationEmail);
				return "redirect:/myreservation";
			}
		}
		return "redirect:/bookinglogin";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("email");
		return "redirect:/";
	}
}
