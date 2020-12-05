import api from './util/api.js';
import ReservationCard from './component/ReservationCard.js';

const myreservation = {
	initialize() {
		this.loadReservations();
	},
	loadReservations() {
		const emailTag = document.querySelector(".viewReservation"),
			email = emailTag.textContent;
		api.getReservations(email).then((result) => {
			this.bindCards(result.reservations);
		});
	},
	bindCards(reservations = []) {
		const ReservationListTag = document.querySelector(".wrap_mylist");
		if (reservations.length === 0) {
			const noReservationTag = document.querySelector(".err");
			noReservationTag.style.display = "block";
			ReservationListTag.style.display = "none";
			return;
		}
		ReservationListTag.style.display = "block";
		const summaryBoardTag = document.querySelector(".summary_board"),
			totalCountTag = summaryBoardTag.querySelector(".figure.book2"),
			bookingCountTag = summaryBoardTag.querySelector(".figure.book_ss"),
			completeCountTag = summaryBoardTag.querySelector(".figure.check"),
			cancelCountTag = summaryBoardTag.querySelector(".figure.back");

		const today = new Date();

		let totalCount = 0,
			cancelCount = 0,
			completeCount = 0,
			bookingCount = 0;
			
		const cardTemplate = document.querySelector("#cardTemplaate").innerText,
			reservationBindTemplate = Handlebars.compile(cardTemplate);

		let bookingCardHTML = "",
			completeCardHTML = "",
			cancelCardHTML = "";

		reservations.forEach(reservation => {
			const reservationDate = new Date(reservation.reservationDate);
			today.setHours(0,0,0,0);
			reservation.isComplete = (reservationDate < today);
			if (reservation.cancelYn) {
				cancelCount++;
				cancelCardHTML += reservationBindTemplate(reservation);
			} else if (reservation.isComplete) {
				completeCount++;
				completeCardHTML += reservationBindTemplate(reservation);
			} else {
				bookingCount++;
				bookingCardHTML += reservationBindTemplate(reservation);
			}
			totalCount++;
		});
		totalCountTag.textContent = totalCount;
		bookingCountTag.textContent = bookingCount;
		completeCountTag.textContent = completeCount;
		cancelCountTag.textContent = cancelCount;

		const confirmedCardList = document.querySelector(".list_cards .card.confirmed");
		confirmedCardList.insertAdjacentHTML("beforeend", bookingCardHTML);

		const usedCardList = document.querySelector(".list_cards .card.used");
		usedCardList.insertAdjacentHTML("beforeend", completeCardHTML);

		const cancelCardList = document.querySelector(".list_cards .card.cancel");
		cancelCardList.insertAdjacentHTML("beforeend", cancelCardHTML);

		this.addCardClickEvent();
	},
	addCardClickEvent() {
		const confirmedCardList = document.querySelectorAll(".card.confirmed article");
		confirmedCardList.forEach(card => { new ReservationCard(card, "confirmed"); });
		const usedCardList = document.querySelectorAll(".card.used article");
		usedCardList.forEach(card => { new ReservationCard(card, "used"); });
	}
}
document.addEventListener("DOMContentLoaded", () => {
	myreservation.initialize();
});