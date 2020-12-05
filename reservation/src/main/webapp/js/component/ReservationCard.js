import API from "../util/api.js";

class ReservationCard {
	constructor(cardElement, cardType) {
		this.card = cardElement;
		this.cardType = cardType;
		this.productId = this.card.dataset.productId;
		this.reservationInfoId = this.card.dataset.reservationInfoId;
		this.registerEvents();
	}
	registerEvents() {
		this.card.querySelector("button.btn").addEventListener("click", () => {
			if(this.cardType === "confirmed")
				this.cancelAsk(this.reservationInfoId);
			else if(this.cardType === "used")
				this.postReview();
		})
	}
	postReview() {
		 location.href=`./reviewWrite?reservationInfoId=${this.reservationInfoId}&productId=${this.productId}`;
	}
	cancelAsk(reservationInfoId) {
		const popup = document.querySelector(".popup_booking_wrapper");
		const productTitleTag = popup.querySelector(".pop_tit span");
		const title = this.card.querySelector(".card_detail .tit").textContent;
		productTitleTag.textContent = title;
		
		const yesBtn = popup.querySelector(".btn_green");
		const noBtn = popup.querySelector(".btn_gray");
		const closeBtn = popup.querySelector(".popup_btn_close");
		yesBtn.addEventListener("click", () => {
			API.cancelReservation(reservationInfoId);
			const cancelList = document.querySelector(".card.cancel");
			this.card.querySelector(".booking_cancel").remove();
			cancelList.appendChild(this.card);
			popup.style.display = "none";
		});
		noBtn.addEventListener("click", () => {
			popup.style.display = "none";
		});
		closeBtn.addEventListener("click", () => {
			popup.style.display = "none";
		});
		popup.style.display = "block";
	}
}
export default ReservationCard;