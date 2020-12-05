import API from './util/api.js';
import format from './util/format.js';
import inputValidCheck from "./util/inputValidCheck.js";
import AgreementControl from "./component/AgreementControl.js";
import CountControl from "./component/CountControl.js";

const priceTypeList = {
	A: {
		name: "성인",
		description: "성인(만 19~64세)"
	},
	Y: {
		name: "청소년",
		description: "청소년(만 13~18세)"
	},
	B: {
		name: "유아",
		description: "유아(만 3세 이하)"
	},
	S: {
		name: "세트",
	},
	D: {
		name: "장애인",
	},
	C: {
		name: "지역주민",
	},
	E: {
		name: "얼리버드",
	},
	V: {
		name: "VIP석",
	},
	R: {
		name: "R석",
	}
};
const reserve = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");

		API.getDisplayInfo(displayInfoId).then(result => {
			this.updateProductReserve(result.averageScore,
				result.comments,
				result.displayInfo,
				result.displayInfoImage,
				result.productImages,
				result.productPrices);
		});
	},
	updateProductReserve(averageScore, comments, displayInfo, displayInfoImage, productImages, productPrices) {
		this.updateProductImages(displayInfo, productImages, productPrices);
		this.updateDisplayInfo(displayInfo, productPrices);
		this.bindProductPrices(productPrices);
		this.addFormCheck();
		this.addAgreeDetailClickEvent();
		this.addSubmitBtnClickEvent(displayInfo);
		this.addCheckAgreeClickEvent();
	},
	updateDisplayInfo(displayInfo, productPrices) {
		const BackBtn = document.querySelector(".btn_back");
		BackBtn.href = `./detail?id=${displayInfo?.displayInfoId}`;

		const storePlaceTag = document.querySelector(".store_details .dsc.place");
		const storeTimeTag = document.querySelector(".store_details .dsc.time");
		const storePriceTag = document.querySelector(".store_details .dsc.price");

		storePlaceTag.textContent = displayInfo.placeStreet;
		storeTimeTag.textContent = displayInfo.openingHours;

		let priceInfoHTML = productPrices.reduce((prev, next) => {
			let desc;
			const info = priceTypeList[next.priceTypeName];
			if (info.description) {
				desc = info.description;
			} else {
				desc = info.name;
			}
			desc += ": " + next.price + "원";
			return prev + desc + "<br>";
		}, "");
		storePriceTag.innerHTML = priceInfoHTML;
	},
	updateProductImages(displayInfo, productImages, productPrices) {
		const visualImg = document.querySelector(".visual_img > li > img");
		// visualImg.src = productImages[0].saveFileName;
		visualImg.src = `./download?fileName=${productImages[0].saveFileName}&contentType=${productImages[0].contentType}`;

		const priceTag = document.querySelector(".preview_txt").children[1];
		const minPrice = productPrices.reduce((prev, next) => {
			return Math.min(prev, next.price);
		}, Number.MAX_VALUE);
		priceTag.textContent = `₩${minPrice} ~ `;
	},
	bindProductPrices(productPrices) {
		Handlebars.registerHelper("getTypeName", priceTypeName => {
			const typeName = priceTypeList[priceTypeName].name;
			return typeName;
		});
		Handlebars.registerHelper("getFormatPrice", price => {
			return format.getFormatPrice(price);
		});
		const priceTemplate = document.querySelector("#priceTemplate").innerText,
			priceBindTemplate = Handlebars.compile(priceTemplate);
		const resultHTML = productPrices.reduce((prev, next) => {
			return prev + priceBindTemplate(next);
		}, "");
		const priceList = document.querySelector(".ticket_body");
		priceList.innerHTML = resultHTML;
		this.addPriceClickEvent();
	},
	addPriceClickEvent() {
		const tabList = document.querySelectorAll(".ticket_body > .qty");
		tabList.forEach(tab => {
			new CountControl(tab);
		});
	},
	addFormCheck() {
		const formTag = document.querySelector(".form_horizontal");
		formTag.addEventListener("change", evt => {
			this.validCheck(evt.target.name, evt.target, evt.target.parentNode.querySelector(".warning_msg"));
		});
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
		const value = input.value;
		let Valid = {
			name: inputValidCheck.getNameValid(value),
			email: inputValidCheck.getEmailValid(value),
			tel: inputValidCheck.getTelValid(value)
		}[type]
		if (!Valid) {
			warning.style.visibility = "visible";
			warning.style.opacity = 1;
			setTimeout(() => {
				warning.style.visibility = "hidden";
				warning.style.opacity = 0;
			}, 1000);
			return false;
		}
		return true;
	},
	addAgreeDetailClickEvent() {
		const agreementList = document.querySelectorAll(".agreement");
		agreementList.forEach((agreement) => {
			new AgreementControl(agreement);
		});
	},
	addSubmitBtnClickEvent(displayInfo) {
		const submitButton = document.querySelector(".bk_btn");

		submitButton.addEventListener("click", () => {
			this.submitForm(displayInfo);
		});
	},
	submitForm(displayInfo) {
		const checkBtn = document.querySelector("input.chk_agree");
		const checkedStatus = checkBtn.checked;
		const formValid = this.formValidCheck();

		if (checkedStatus && formValid) {
			const reservation = this.makeReservationObj(displayInfo);
			const json = JSON.stringify(reservation);
			API.postBookingForm(json);
			location.href = "./";
		}
	},
	makeReservationObj(displayInfo) {
		const form = document.querySelector(".section_booking_form form");
		const nameInput = form.querySelector("input.name");
		const telInput = form.querySelector("input.tel");
		const emailInput = form.querySelector("input.email");

		let object = new Object();
		object.reservationName = nameInput.value;
		object.reservationTelephone = telInput.value;
		object.reservationEmail = emailInput.value;
		object.displayInfoId = displayInfo.displayInfoId;
		object.productId = displayInfo.productId;
		object.reservationYearMonthDay = format.getFormatDate(new Date().toDateString());
		object.prices = this.makeReservationPriceArray();
		return object;
	},
	makeReservationPriceArray() {
		const quantityTagList = document.querySelectorAll(".qty");
		let arr = new Array();
		quantityTagList.forEach((qty) => {
			const countInput = qty.querySelector("input.count_control_input");
			if (countInput && countInput.value > 0) {
				let id = qty.dataset.productPriceId;
				let price = new Object();
				price.productPriceId = id;
				price.count = countInput.value;
				arr.push(price);
			}
		})
		return arr;
	},
	addCheckAgreeClickEvent() {
		const checkBtn = document.querySelector(".chk_agree");
		checkBtn.addEventListener("click", () => {
			const checkedStatus = checkBtn.checked;
			const btn_wrap = document.querySelector(".bk_btn_wrap");
			if (checkedStatus) {
				btn_wrap.classList.remove("disable")
			} else {
				btn_wrap.classList.add("disable")
			}
		})
	}
}
document.addEventListener("DOMContentLoaded", () => {
	reserve.initialize();
});