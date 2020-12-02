package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationForm {
	Long reservationInfoId;
	Long displayInfoId;
	Long productId;
	List<PriceForm> prices;
	String reservationEmail;
	String reservationName;
	String reservationTelephone;
	String reservationYearMonthDay;
	Boolean cancelYn;
	String createDate;
	String modifyDate;

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<PriceForm> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceForm> prices) {
		this.prices = prices;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationForm \n[displayInfoId=" + displayInfoId + "\n, prices=" + prices + "\n, productId="
			+ productId
			+ "\n, reservationEmail=" + reservationEmail + "\n, reservationName=" + reservationName
			+ "\n, reservationTelephone=" + reservationTelephone + "\n, reservationYearMonthDay="
			+ reservationYearMonthDay
			+ "]";
	}
}
