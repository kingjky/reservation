package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationForm;

public interface ReservationService {
	List<Reservation> getReservationsUsingEmail(String email);
	ReservationForm postReservation(ReservationForm form);
	Comment postComment(Long reservationInfoId, String productId, String score, String comment, MultipartFile file, String now);
	Reservation putReservationCancel(Long reservationInfoId);
}
