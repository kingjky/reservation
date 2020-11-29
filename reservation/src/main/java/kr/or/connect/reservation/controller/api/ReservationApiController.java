package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.controller.EntityController;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController extends EntityController {
	private final ReservationService reservationService;

	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getReservationsUsingEmail(
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {
		List<Reservation> reservations = reservationService.getReservationsUsingEmail(reservationEmail);

		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservations);
		return handleSuccess(map);
	}
}
