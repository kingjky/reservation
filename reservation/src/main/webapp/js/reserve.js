import api from './api.js';
import format from './format.js';

const reserve = {
	priceTypeList: {
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
	},
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");

		api.getDisplayInfo(displayInfoId).then(function (result) {
			this.updateProductReserve(result.averageScore,
				result.comments,
				result.displayInfo,
				result.displayInfoImage,
				result.productImages,
				result.productPrices);
		}.bind(this));
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
		BackBtn.href = `./detail?id=${displayInfo.displayInfoId}`;

		const storeDetailsTagList = document.querySelectorAll(".store_details > p");
		const storePlaceTag = storeDetailsTagList[0];
		const storeTimeTag = storeDetailsTagList[1];
		const storePriceTag = storeDetailsTagList[2];

		storePlaceTag.textContent = displayInfo.placeStreet;
		storeTimeTag.textContent = displayInfo.openingHours;

		let priceInfoHTML = productPrices.reduce(function (prev, next) {
			let desc;
			let info = this.priceTypeList[next.priceTypeName];
			if (info.description) {
				desc = info.description;
			} else {
				desc = info.name;
			}
			desc += ": " + next.price + "원";
			return prev + desc + "<br>";
		}.bind(this), "");
		storePriceTag.innerHTML = priceInfoHTML;
	},
	updateProductImages(displayInfo, productImages, productPrices) {
		const visualImg = document.querySelector(".visual_img > li > img");
		visualImg.src = productImages[0].saveFileName;

		const priceTag = document.querySelector(".preview_txt").children[1];
		const minPrice = productPrices.reduce(function (prev, next) {
			return Math.min(prev, next.price);
		}, Number.MAX_VALUE);
		priceTag.textContent = `₩${minPrice} ~ `;
	},
	bindProductPrices(productPrices) {
		console.log(productPrices);

		Handlebars.registerHelper("getTypeName", function (priceTypeName) {
			const typeName = this.priceTypeList[priceTypeName].name;
			return typeName;
		}.bind(this));
		Handlebars.registerHelper("getFormatPrice", function (price) {
			return format.getFormatPrice(price);
		}.bind(this));
		const priceTemplate = document.querySelector("#priceTemplate").innerText,
			priceBindTemplate = Handlebars.compile(priceTemplate);
		const resultHTML = productPrices.reduce(function (prev, next) {
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

		formTag.addEventListener("change", function (evt) {
			this.validCheck(evt.target.name, evt.target, evt.target.nextSibling.nextSibling)();
		}.bind(this));
	},
	formValidCheck() {
		const form = document.querySelector(".section_booking_form form");

		const nameInput = form.querySelector("input.name");
		const nameValid = this.validCheck(nameInput.name, nameInput, nameInput.nextSibling.nextSibling)();

		const telInput = form.querySelector("input.tel");
		const telValid = this.validCheck(telInput.name, telInput, telInput.nextSibling.nextSibling)();

		const emailInput = form.querySelector("input.email");
		const emailValid = this.validCheck(emailInput.name, emailInput, emailInput.nextSibling.nextSibling)();

		return nameValid && telValid && emailValid;
	},
	validCheck(type, input, warning) {
		return {
			name() {
				let name = input.value;
				if (!name || name.length === 0) {
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
			tel() {
				let tel = input.value;
				const telValid = (/^\d{2,3}-\d{3,4}-\d{4}$/).test(tel);
				if (!telValid) {
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
			email() {
				let mail = input.value;
				const mailValid = (/^[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]+$/).test(mail);
				if (!mailValid) {
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
		}[type];
	},
	addAgreeDetailClickEvent() {
		const agreementList = document.querySelectorAll(".agreement");
		agreementList.forEach((agreement) => {
			new agreementControl(agreement);
		});
	},
	addSubmitBtnClickEvent(displayInfo) {
		const submitButton = document.querySelector(".bk_btn");

		submitButton.addEventListener("click", function () {
			this.submitForm(displayInfo);
		}.bind(this));
	},
	submitForm(displayInfo) {
		const checkBtn = document.querySelector("input.chk_agree");
		const checkedStatus = checkBtn.checked;

		if (checkedStatus && this.formValidCheck()) {
			const reservation = this.getReservationObj(displayInfo);
			const json = JSON.stringify(reservation);
			api.postBookingForm(json);
			location.href = "./";
		}
	},
	getReservationObj(displayInfo) {
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
		object.prices = this.getReservationPriceArray();
		return object;
	},
	getReservationPriceArray() {
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
		checkBtn.addEventListener("click", function () {
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
class agreementControl {
	constructor(agreeElement) {
		this.agreement = agreeElement;
		this.registerEvents();
	}
	registerEvents() {
		this.agreement.addEventListener("click", function (event) {
			this.toggle(event);
		}.bind(this));
	}
	toggle(event) {
		let target = event.target;
		if (target.tagName !== "A")
			target = target.parentNode;
		if (target.tagName === "A") {
			if (event.currentTarget.classList.contains("open")) {
				event.currentTarget.classList.remove("open");
			} else {
				event.currentTarget.classList.add("open");
			}
		}
	}
}
class CountControl {
	constructor(tabElement) {
		this.tabmenu = tabElement;
		this.registerEvents();
	}
	registerEvents() {
		this.tabmenu.addEventListener("click", function (event) {
			if (event.target.tagName === "A") {
				this.countControl(event.currentTarget, event.target.getAttribute("value"));
			}
		}.bind(this));
	}
	countControl(countBody, value) {
		const countControlInput = countBody.querySelector(".count_control_input");
		let count = Number(countControlInput.value);
		count += Number(value);
		const icoMinusBtn = countBody.querySelector(".ico_minus3");
		const icoPlusBtn = countBody.querySelector(".ico_plus3");
		const individualPriceTag = countBody.querySelector(".individual_price");

		if (count < 0 || count > 10) {
			return;
		} else if (count == 0) {
			icoMinusBtn.classList.add("disabled");
			countControlInput.classList.add("disabled");
			individualPriceTag.classList.remove("on_color");
		} else if (count == 10) {
			icoPlusBtn.classList.add("disabled");
		} else {
			icoMinusBtn.classList.remove("disabled");
			countControlInput.classList.remove("disabled");
			icoPlusBtn.classList.remove("disabled");
			individualPriceTag.classList.add("on_color");
		}

		countControlInput.value = count;

		const priceTag = countBody.querySelector(".price");
		const price = Number(priceTag.getAttribute("value"));

		const totalPriceTag = countBody.querySelector(".total_price");
		const totalPrice = price * count;
		totalPriceTag.textContent = format.getFormatPrice(totalPrice);

		const totalCountTag = document.querySelector("#totalCount");
		const totalCount = Number(totalCountTag.textContent) + Number(value);
		totalCountTag.textContent = totalCount;
	}
}
document.addEventListener("DOMContentLoaded", function () {
	reserve.initialize();
});