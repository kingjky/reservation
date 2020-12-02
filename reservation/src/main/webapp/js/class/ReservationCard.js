import API from "../util/api.js";

class ReservationCard {
	constructor(cardElement) {
		this.card = cardElement;
		this.registerEvents();
	}
	registerEvents() {
		this.card.addEventListener("click", () => {
			this.cancel();
		})
	}
	cancel() {
		const cancelBtn = this.card?.querySelector(".btn");
		const reservationInfoId = cancelBtn?.value;
		this.cancelAsk(reservationInfoId);
	}
	cancelAsk(reservationInfoId) {
		const popup = document.querySelector(".popup_booking_wrapper");
		const productTitleTag = popup?.querySelector(".pop_tit span");
		const title = this.card?.querySelector(".card_detail .tit").textContent;
		productTitleTag.textContent = title;
		
		const yesBtn = popup.querySelector(".btn_green");
		const noBtn = popup.querySelector(".btn_gray");
		const closeBtn = popup.querySelector(".popup_btn_close");
		yesBtn.addEventListener("click", () => {
			API.cancelReservation(reservationInfoId);
			const cancelList = document.querySelector(".card.used.cancel");
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