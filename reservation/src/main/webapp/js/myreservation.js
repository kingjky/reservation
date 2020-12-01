import api from './util/api.js';
import ReservationCard from './class/ReservationCard.js';

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
		if(reservations.length === 0) {
			const noReservationTag = document.querySelector(".err");
			noReservationTag.style.display = "block";
			ReservationListTag.style.display = "none";
			return;
		}
		ReservationListTag.style.display = "block";
		const summaryBoardTag = document.querySelector(".summary_board"),
			book2FigureTag = summaryBoardTag?.querySelector(".figure.book2"),
			bookSSFigureTag = summaryBoardTag?.querySelector(".figure.book_ss"),
			backFigureTag = summaryBoardTag?.querySelector(".figure.back");

		let totalCount = 0,
			cancelYesCount = 0,
			cancelNoCount = 0;
		reservations.forEach(reservation => {
			if (reservation.cancelYn)
				cancelYesCount++;
			else
				cancelNoCount++;
			totalCount++;
		});
		book2FigureTag.textContent = totalCount;
		bookSSFigureTag.textContent = cancelNoCount;
		backFigureTag.textContent = cancelYesCount;

		const cardTemplate = document.querySelector("#cardTemplaate").innerText,
			reservationBindTemplate = Handlebars.compile(cardTemplate);
		let resultCancelNHTML = reservations.reduce((prev, next) => {
			if (!next.cancelYn)
				return prev + reservationBindTemplate(next);
			else
				return prev;
		}, "");
		const confirmedCardList = document.querySelector(".list_cards .card.confirmed");
		confirmedCardList.insertAdjacentHTML("beforeend", resultCancelNHTML);

		let resultCancelYHTML = reservations.reduce((prev, next) => {
			if (next.cancelYn)
				return prev + reservationBindTemplate(next);
			else
				return prev;
		}, "");
		const cancelCardList = document.querySelector(".list_cards .card.used.cancel");
		cancelCardList.insertAdjacentHTML("beforeend", resultCancelYHTML);

		this.addCancelClickEvent();
	},
	addCancelClickEvent() {
		const cardList = document.querySelectorAll(".card.confirmed article");
		cardList.forEach(card => { new ReservationCard(card); });
	}
}
document.addEventListener("DOMContentLoaded", () => {
	myreservation.initialize();
});