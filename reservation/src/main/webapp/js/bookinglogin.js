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
		const form = document.querySelector(".login_form"),
			emailInput = form?.querySelector("input#resrv_id"),
			emailValid = this.validCheck(emailInput);
		if (emailValid) {
			const email = emailInput.value;
			this.login(email);
		}
	},
	login(email = "") {
		location.href = `./login?reservationEmail=${email}`;
	},
	validCheck(emailInput = "") {
		let email = emailInput.value;
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