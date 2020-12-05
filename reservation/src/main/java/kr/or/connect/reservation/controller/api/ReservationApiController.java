package kr.or.connect.reservation.controller.api;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.controller.EntityController;
import kr.or.connect.reservation.dto.Comment;
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
		map.put("cancelYn", false);
		map.put("createDate", new Date());
		map.put("modifyDate", new Date());
		map.put("prices", reservationForm.getPrices());
		return handleSuccess(map);
	}

	@PutMapping("/{reservationInfoId}")
	public ResponseEntity<Map<String, Object>> putReservationCancel(
		@PathVariable(name = "reservationInfoId") Long reservationInfoId) {
		Reservation reservation = reservationService.putReservationCancel(reservationInfoId);
		return handleSuccess(Collections.singletonMap("reservation", reservation));
	}
	
	@PostMapping("/{reservationInfoId}/comments")
	public ResponseEntity<Map<String, Object>> postReview(
		@PathVariable(name = "reservationInfoId") Long reservationInfoId,
		@RequestParam(name = "productId", required = true) String productId,
		@RequestParam(name = "score", required = true) String score,
		@RequestParam(name = "comment", required = true) String review,
		@RequestParam("attachedImage") MultipartFile file)
		throws ParseException {
		
		System.out.println("reservationInfoId : " + reservationInfoId);
		System.out.println("productId : " + productId);
		System.out.println("score : " + score);
		System.out.println("comment : " + review);
		
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		
		DateFormat targetFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_");
		String now = targetFormat.format(new Date());
		
		Comment comment = reservationService.postComment(reservationInfoId, productId, score, review, file, now);
		
		try (
			FileOutputStream fos = new FileOutputStream("c:/tmp/img/" + now + file.getOriginalFilename());
			InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("test", "ok");
		return handleSuccess(map);
	}
}
