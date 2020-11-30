package kr.or.connect.reservation.controller.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.controller.EntityController;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationForm;
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
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail) throws ParseException {
		List<Reservation> reservations = reservationService.getReservationsUsingEmail(reservationEmail);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Reservation reservation : reservations) {
			Date reservationDate = dateFormat.parse(reservation.getReservationDate());
			reservation.setReservationDate(dateFormat.format(reservationDate));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservations);
		return handleSuccess(map);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> postReservation(@RequestBody ReservationForm reservationForm)
		throws ParseException {
		reservationService.postReservation(reservationForm);
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfoId", reservationForm.getReservationInfoId());
		map.put("productId", reservationForm.getProductId());
		map.put("displayInfoId", reservationForm.getDisplayInfoId());
		map.put("reservationName", reservationForm.getReservationName());
		map.put("reservationTelephone", reservationForm.getReservationTelephone());
		map.put("reservationEmail", reservationForm.getReservationEmail());
		DateFormat originalFormat = new SimpleDateFormat("yyyy.MM.dd");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date reservationDate = originalFormat.parse(reservationForm.getReservationYearMonthDay());
		map.put("reservationDate", targetFormat.format(reservationDate));
		map.put("cancelYn", reservationForm.isCancelYn());
		map.put("createDate", reservationForm.getCreateDate());
		map.put("modifyDate", reservationForm.getModifyDate());
		map.put("prices", reservationForm.getPrices());
		return handleSuccess(map);
	}

	@PutMapping("/{reservationInfoId}")
	public ResponseEntity<Map<String, Object>> putReservationCancel(
		@PathVariable(name = "reservationInfoId") Long reservationInfoId) {
		Reservation reservation = reservationService.putReservationCancel(reservationInfoId);
		return handleSuccess(Collections.singletonMap("reservation", reservation));
	}
}
