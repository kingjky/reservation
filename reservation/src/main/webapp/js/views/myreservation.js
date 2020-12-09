import Api from '../module/api.js';
import Bind from '../module/bindTemplate.js';
import ReservationCard from '../component/ReservationCard.js';

const myreservation = {
	initialize() {
		this.reservationListTag = document.querySelector("#container > .ct > .section_my > .wrap_mylist");
		this.summaryBoardTag = document.querySelector("#container > .ct > .section_my > .my_summary");
		const email = document.querySelector(".viewReservation").textContent;
		Api.getReservations(email).then(result => {
			this.bindCards(result.reservations)
		});
	},
	bindCards(reservations) {
		if (!reservations?.length) {
			const emptyReservationEl = document.querySelector("#container > .ct > .section_my > .err");
			emptyReservationEl.style.display = "block";
			return;
		}
		this.reservationListTag.style.display = "block";
		const today = new Date().setHours(0, 0, 0, 0);
		let totalCount, cancelCount, completeCount, bookingCount;
		totalCount = cancelCount = completeCount = bookingCount = 0;
		let bookingCardHTML, completeCardHTML, cancelCardHTML;
		bookingCardHTML = completeCardHTML = cancelCardHTML = "";

		const bindReservation = Bind.registerReservationTemplate(document.querySelector('#cardTemplate').innerText);
		reservations.forEach(reservation => {
			const reservationDate = new Date(reservation.reservationDate);
			if (reservation.cancelYn) {
				cancelCount++;
				cancelCardHTML += bindReservation(reservation);
			} else if (reservationDate < today) {
				reservation.isComplete = true;
				completeCount++;
				completeCardHTML += bindReservation(reservation);
			} else {
				bookingCount++;
				bookingCardHTML += bindReservation(reservation);
			}
			totalCount++;
		});
		this.summaryBoardTag.querySelector("span.figure.book2")
			.textContent = totalCount;
		this.summaryBoardTag.querySelector("span.figure.book_ss")
			.textContent = bookingCount;
		this.summaryBoardTag.querySelector("span.figure.check")
			.textContent = completeCount;
		this.summaryBoardTag.querySelector("span.figure.back")
			.textContent = cancelCount;

		const confirmedCardList = this.reservationListTag.querySelector(".list_cards li.card.confirmed");
		confirmedCardList.insertAdjacentHTML("beforeend", bookingCardHTML);

		const usedCardList = this.reservationListTag.querySelector(".list_cards li.card.used");
		usedCardList.insertAdjacentHTML("beforeend", completeCardHTML);

		const cancelCardList = this.reservationListTag.querySelector(".list_cards li.card.cancel");
		cancelCardList.insertAdjacentHTML("beforeend", cancelCardHTML);
		
		confirmedCardList.querySelectorAll(".card.confirmed article").forEach(card => new ReservationCard(card, "confirmed"));
		usedCardList.querySelectorAll(".card.used article").forEach(card => new ReservationCard(card, "used"));
	}
}
document.addEventListener("DOMContentLoaded", () => {
	myreservation.initialize();
});