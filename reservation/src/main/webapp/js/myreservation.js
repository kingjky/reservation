import api from './api.js';

const myreservation = {
	initialize() {
		this.loadReservations();
	},
	loadReservations() {
		const emailTag = document.querySelector(".viewReservation");
		const email = emailTag.textContent;

		api.getReservations(email).then(function (result) {
			this.bindCards(result.reservations);
		}.bind(this));
	},
	bindCards(reservations) {
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

		this.addCancelClickEvent();
	},
	addCancelClickEvent() {
		const cardList = document.querySelectorAll(".card.confirmed article");
		cardList.forEach(card => {
			new Card(card);
		});
	}
}
class Card {
	constructor(cardElement) {
		this.card = cardElement;
		this.registerEvents();
	}
	registerEvents() {
		this.card.addEventListener("click", function (event) {
			this.cancel()
		}.bind(this))
	}
	cancel() {
		const cancelBtn = this.card.querySelector(".btn");
		const reservationInfoId = cancelBtn.value;
		this.cancelAsk(reservationInfoId);
	}
	cancelAsk(reservationInfoId) {
		const popup = document.querySelector(".popup_booking_wrapper");
		const yesBtn = popup.querySelector(".btn_green");
		const noBtn = popup.querySelector(".btn_gray");
		const closeBtn = popup.querySelector(".popup_btn_close");

		yesBtn.addEventListener("click", function() {
			api.cancelReservation(reservationInfoId);
			const cancelList = document.querySelector(".card.used.cancel");
			cancelList.appendChild(this.card);
			popup.style.display = "none";
		}.bind(this));

		noBtn.addEventListener("click", function() {
			popup.style.display = "none";
		});
		closeBtn.addEventListener("click", function() {
			popup.style.display = "none";
		});
		
		popup.style.display = "block";
	}
}
document.addEventListener("DOMContentLoaded", function () {
	myreservation.initialize();
});