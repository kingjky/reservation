import inputValidCheck from "./util/inputValidCheck.js";

const bookinglogin = {
	initialize() {
		this.addSubmitBtnClickEvent();
	},
	addSubmitBtnClickEvent() {
		const submitButton = document.querySelector(".login_btn");
		submitButton.addEventListener("click", () => {
			this.submitForm();
		});
	},
	submitForm() {
		if (this.formValidCheck()) {
			const form = document.querySelector(".login_form");
			const emailInput = form?.querySelector("input#resrv_id");
			const email = emailInput?.value;
			this.login(email);
		}
	},
	login(email = "") {
		location.href = `./login?reservationEmail=${email}`;
	},
	formValidCheck() {
		const form = document.querySelector(".login_form");
		const emailInput = form?.querySelector("input#resrv_id");
		const emailValid = this.validCheck(emailInput);
		return emailValid;
	},
	validCheck(emailInput = "") {
		let email = emailInput?.value;
		const emailValid = inputValidCheck.getEmailValid(email);
		if (!emailValid) {
			emailInput.style.color = "red";
			emailInput.value = "형식이 틀렸거나 너무 짧아요";
			setTimeout(() => {
				emailInput.value = "";
				emailInput.style.color = "black";
			}, 1000);
			return false;
		}
		return true;
	},
}
document.addEventListener("DOMContentLoaded", () => {
	bookinglogin.initialize();
});