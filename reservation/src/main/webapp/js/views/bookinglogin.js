import ValidCheck from "../module/inputValidCheck.js";

const bookinglogin = {
	initialize() {
		this.form = document.querySelector("app > #container > booked-confirm > .booking_login > form#form1");
		this.registerEvent();
	},
	registerEvent() {
		const submitBtn = this.form.querySelector("button.login_btn");
		submitBtn.addEventListener("click", evt => this.submitForm(evt));
	},
	submitForm() {
		const emailInput = this.form.querySelector(".login_form > input#resrv_id");
		if (!ValidCheck.checkEmail(emailInput.value)) {
			emailInput.style.color = "red";
			emailInput.value = "형식이 틀렸거나 너무 짧아요";
			setTimeout(() => {
				emailInput.style.color = "black";
				emailInput.value = "";
			}, 1000);
			return;
		}
		location.href = `./login?reservationEmail=${emailInput.value}`;
	},
}
document.addEventListener("DOMContentLoaded", () => {
	bookinglogin.initialize();
});