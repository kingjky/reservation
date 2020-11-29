package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationForm;

public interface ReservationService {
	List<Reservation> getReservationsUsingEmail(String email);
	ReservationForm postReservation(ReservationForm form);
}
