package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Reservation;

public interface ReservationService {
	List<Reservation> getReservationsUsingEmail(String email);
}
