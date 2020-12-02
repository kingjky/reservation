package kr.or.connect.reservation.dto;

public class Reservation {
	Long reservationInfoId;
	Long productId;
	Long displayInfoId;
	String reservationName;
	String reservationTelephone;
	String reservationEmail;
	boolean cancelYn;
	String reservationDate;
	String createDate;
	String modifyDate;
	DisplayInfo displayInfo;
	int totalPrice;

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
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

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Reservation\n [reservationInfoId=" + reservationInfoId + ",\n productId=" + productId
			+ ",\n displayInfoId="
			+ displayInfoId + ",\n reservationName=" + reservationName + ",\n reservationTelephone="
			+ reservationTelephone
			+ ",\n reservationEmail=" + reservationEmail + ",\n cancelYn=" + cancelYn + ",\n reservationDate="
			+ reservationDate + ",\n createDate=" + createDate + ",\n modifyDate=" + modifyDate + ",\n displayInfo="
			+ displayInfo + ",\n totalPrice=" + totalPrice + "]";
	}
}
