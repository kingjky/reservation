import Api from '../module/api.js';
import Formatter from '../module/format.js';
import Bind from '../module/bindTemplate.js';
import InputValidCheck from "../module/inputValidCheck.js";
import AgreementControl from "../component/AgreementControl.js";
import CountControl from "../component/CountControl.js";


const priceTypeList = {
	A: { name: "성인", description: "성인(만 19~64세)" },
	Y: { name: "청소년", description: "청소년(만 13~18세)" },
	B: { name: "유아", description: "유아(만 3세 이하)" },
	S: { name: "세트" },
	D: { name: "장애인" },
	C: { name: "지역주민" },
	E: { name: "얼리버드" },
	V: { name: "VIP석" },
	R: { name: "R석" }
};
const reserve = {
	initialize() {
		const url = new URL(window.location);
		this.displayInfoId = url.searchParams.get("id");

		const BackBtn = document.querySelector(".btn_back");
		BackBtn.href = `./detail?id=${this.displayInfoId}`;

		Api.getDisplayInfo(this.displayInfoId).then(result => {
			this.updateProductImages(result.displayInfo, result.productImages, result.productPrices);
			this.updateDisplayInfo(result.displayInfo, result.productPrices);
			this.bindProductPrices(result.productPrices);
			this.registerEvent(result.displayInfo);
		});
	},
	registerEvent(displayInfo) {
		const formTag = document.querySelector(".form_horizontal");
		formTag.addEventListener("change", evt => this.validCheck(evt.target.name, evt.target, evt.target.parentNode.querySelector(".warning_msg")));

		const agreementList = document.querySelectorAll(".agreement");
		agreementList.forEach((agreement) => new AgreementControl(agreement));

		const submitButton = document.querySelector(".bk_btn");
		submitButton.addEventListener("click", () => this.submitForm(displayInfo));

		const checkBtn = document.querySelector(".chk_agree");
		checkBtn.addEventListener("click", () => document.querySelector(".bk_btn_wrap").classList.toggle("disable"));

		const tabList = document.querySelectorAll(".ticket_body > .qty");
		tabList.forEach(tab => new CountControl(tab));
	},
	updateDisplayInfo(displayInfo, productPrices) {
		const storePlaceTag = document.querySelector(".store_details .dsc.place");
		const storeTimeTag = document.querySelector(".store_details .dsc.time");
		const storePriceTag = document.querySelector(".store_details .dsc.price");

		storePlaceTag.textContent = displayInfo.placeStreet;
		storeTimeTag.textContent = displayInfo.openingHours;

		let priceInfoHTML = productPrices.reduce((prev, next) => prev + priceTypeList[next.priceTypeName].description ?? priceTypeList[next.priceTypeName].name + ": " + next.price + "원" + "<br>", "");
		storePriceTag.innerHTML = priceInfoHTML;
	},
	updateProductImages(displayInfo, productImages, productPrices) {
		const visualImg = document.querySelector(".visual_img > li > img");
		visualImg.src = `./download?fileName=${productImages[0].saveFileName}&contentType=${productImages[0].contentType}`;

		const priceTag = document.querySelector(".preview_txt").children[1];
		const minPrice = productPrices.reduce((prev, next) => Math.min(prev, next.price), Number.MAX_VALUE);
		priceTag.textContent = `₩${minPrice} ~ `;
	},
	bindProductPrices(productPrices) {
		const priceList = document.querySelector(".ticket_body");
		const bindPrice = Bind.registerPriceTemplate(document.querySelector('#priceTemplate').innerText, priceTypeList);
		const resultHTML = productPrices.reduce((prev, next) => prev + bindPrice(next), "");
		priceList.innerHTML = resultHTML;
	},
	formValidCheck() {
		const form = document.querySelector(".section_booking_form form");
		const nameInput = form.querySelector("input.name");
		const nameValid = this.validCheck(nameInput.name, nameInput, nameInput.parentNode.querySelector(".warning_msg"));
		const telInput = form.querySelector("input.tel");
		const telValid = this.validCheck(telInput.name, telInput, telInput.parentNode.querySelector(".warning_msg"));
		const emailInput = form.querySelector("input.email");
		const emailValid = this.validCheck(emailInput.name, emailInput, emailInput.parentNode.querySelector(".warning_msg"));
		return nameValid && telValid && emailValid;
	},
	validCheck(type, input, warning) {
		warning.style.visibility = "visible";
		warning.style.opacity = 1;
		setTimeout(() => {
			warning.style.visibility = "hidden";
			warning.style.opacity = 0;
		}, 1000);
	},
	submitForm(displayInfo) {
		const checkBtn = document.querySelector("input.chk_agree"),
			form = document.querySelector(".section_booking_form form"),
			nameInput = form.querySelector("input.name"),
			telInput = form.querySelector("input.tel"),
			emailInput = form.querySelector("input.email");

		if (checkBtn.checked && InputValidCheck.checkName(nameInput.value) && InputValidCheck.checkTel(telInput.value) && InputValidCheck.checkEmail(emailInput.value)) {
			const reservation = this.makeReservationObj(displayInfo);
			const json = JSON.stringify(reservation);
			Api.postBookingForm(json);
			location.href = "./";
		}
	},
	makeReservationObj(displayInfo) {
		const form = document.querySelector(".section_booking_form form");
		const nameInput = form.querySelector("input.name");
		const telInput = form.querySelector("input.tel");
		const emailInput = form.querySelector("input.email");

		const obj = {
			reservationName: nameInput.value,
			reservationTelephone: telInput.value,
			reservationEmail: emailInput.value,
			displayInfoId: displayInfo.displayInfoId,
			productId: displayInfo.productId,
			reservationYearMonthDay: Formatter.formatDate(new Date().toDateString()),
			prices: this.makeReservationPriceArray()
		};
		return obj;
	},
	makeReservationPriceArray() {
		const quantityTagList = document.querySelectorAll(".qty");
		let arr = new Array();
		quantityTagList.forEach((qty) => {
			const countInput = qty.querySelector("input.count_control_input");
			if (countInput && countInput.value > 0) {
				let id = qty.dataset.productPriceId;
				let price = {
					productPriceId: id,
					count: countInput.value
				};
				arr.push(price);
			}
		})
		return arr;
	},
}
document.addEventListener("DOMContentLoaded", () => {
	reserve.initialize();
});