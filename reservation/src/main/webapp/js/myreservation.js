import api from './api.js';

const myreservation = {
	initialize() {
		this.loadReservations();
	},
	loadReservations() {
		const emailTag = document.querySelector(".viewReservation");
		const email = emailTag.textContent;

		api.getReservations(email).then(function (result) {
			console.log(result);
			this.bindCards(result.reservations);
		}.bind(this));
	},
	bindCards(reservations) {
		console.log(reservations);
		const summaryBoardTag = document.querySelector(".summary_board");
		const book2FigureTag = summaryBoardTag.querySelector(".figure.book2");
		const bookSSFigureTag = summaryBoardTag.querySelector(".figure.book_ss");
		const checkFigureTag = summaryBoardTag.querySelector(".figure.check");
		const backFigureTag = summaryBoardTag.querySelector(".figure.back");

		let totalCount = 0,
			cancelYCount = 0,
			cancelNCount = 0;
		reservations.forEach(reservation => {
			totalCount++;
			if(reservation.cancelYn)
				cancelYCount++;
			else
				cancelNCount++;
		});
		book2FigureTag.textContent = totalCount;
		bookSSFigureTag.textContent = cancelNCount;
		backFigureTag.textContent = cancelYCount;

		const cardTemplate = document.querySelector("#cardTemplaate").innerText,
			reservationBindTemplate = Handlebars.compile(cardTemplate);
		let resultCancelNHTML = reservations.reduce(function (prev, next) {
			if(!next.cancelYn)
				return prev + reservationBindTemplate(next);
			else
				return prev;
		}, "");
		const confirmedCardList = document.querySelector(".list_cards .card.confirmed");
		confirmedCardList.insertAdjacentHTML("beforeend", resultCancelNHTML);
		
		let resultCancelYHTML = reservations.reduce(function (prev, next) {
			if(next.cancelYn)
				return prev + reservationBindTemplate(next);
			else
				return prev;
		}, "");
		const cancelCardList = document.querySelector(".list_cards .card.used.cancel");
		cancelCardList.insertAdjacentHTML("beforeend", resultCancelYHTML);

		// this.addCancelBtnClickEvent();
	},
}
document.addEventListener("DOMContentLoaded", function () {
	myreservation.initialize();
});