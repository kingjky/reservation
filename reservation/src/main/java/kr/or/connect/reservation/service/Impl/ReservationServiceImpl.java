package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.PriceForm;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationForm;
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
			Long displayInfoId = reservation.getDisplayInfoId();
			DisplayInfo displayInfo = displayInfoDao.selectUsingDisplayInfoId(displayInfoId);
			reservation.setDisplayInfo(displayInfo);
			int totalPrice = reservationDao.selectTotalPrice(reservation.getReservationInfoId());
			reservation.setTotalPrice(totalPrice);
		}
		return reservations;
	}

	@Override
	public ReservationForm postReservation(ReservationForm reservationForm) {
		Long reservationInfoId = reservationDao.insert(reservationForm);
		reservationForm.setReservationInfoId(reservationInfoId);
		for (PriceForm priceForm : reservationForm.getPrices()) {
			priceForm.setReservationInfoId(reservationInfoId);
			Long reservationInfoPriceId = reservationDao.insertPrice(priceForm);
			priceForm.setReservationInfoPriceId(reservationInfoPriceId);
		}
		return reservationForm;
	}

	@Override
	public Reservation putReservationCancel(Long reservationInfoId) {
		reservationDao.updateCancel(reservationInfoId);
		Reservation reservation = reservationDao.selectUsingId(reservationInfoId);
		return reservation;
	}

	@Override
	@Transactional
	public Comment postComment(Long reservationInfoId, String productId, String score, String comment,
		MultipartFile file, String now) {
		Long reservationUserCommentId = reservationDao.insertComment(reservationInfoId, productId, score, comment);
		if(file != null) {
			reservationDao.insertCommentImage(reservationInfoId, reservationUserCommentId, now + file.getOriginalFilename(), file.getContentType());
		}
		return new Comment();
	}

}
