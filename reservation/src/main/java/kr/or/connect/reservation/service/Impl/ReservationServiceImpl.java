package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationDao reservationDao;
	private final DisplayInfoDao displayInfoDao;

	public ReservationServiceImpl(ReservationDao reservationDao, DisplayInfoDao displayInfoDao) {
		this.reservationDao = reservationDao;
		this.displayInfoDao = displayInfoDao;
	}

	@Override
	public List<Reservation> getReservationsUsingEmail(String email) {
		List<Reservation> reservations = reservationDao.selectAllUsingEmail(email);
		for (Reservation reservation : reservations) {
			int displayInfoId = reservation.getDisplayInfoId();
			DisplayInfo displayInfo = displayInfoDao.selectUsingDisplayInfoId(displayInfoId);
			reservation.setDisplayInfo(displayInfo);
		}
		return reservations;
	}

}
