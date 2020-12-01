package kr.or.connect.reservation.dto;

public class PriceForm {
	Long count;
	Long productPriceId;
	Long reservationInfoId;
	Long reservationInfoPriceId;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(Long reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "\n\tPriceForm \n\t[count=" + count + "\n\t, productPriceId=" + productPriceId + "\n\t, reservationInfoId="
			+ reservationInfoId + "\n\t, reservationInfoPriceId=" + reservationInfoPriceId + "]\n\t";
	}
}