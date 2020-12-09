package kr.or.connect.reservation.dto;

public class PriceForm {
	long count;
	long productPriceId;
	long reservationInfoId;
	long reservationInfoPriceId;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public long getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(long reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "\n\tPriceForm \n\t[count=" + count + "\n\t, productPriceId=" + productPriceId + "\n\t, reservationInfoId="
			+ reservationInfoId + "\n\t, reservationInfoPriceId=" + reservationInfoPriceId + "]\n\t";
	}
}