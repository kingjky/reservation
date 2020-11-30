const bookinglogin = {
	initialize() {
		this.addSubmitBtnClickEvent();
	},
	addSubmitBtnClickEvent() {
		const submitButton = document.querySelector(".login_btn");

		submitButton.addEventListener("click", function () {
			this.submitForm();
		}.bind(this));
	},
	submitForm() {
		if (this.formValidCheck()) {
			const form = document.querySelector(".login_form");
			const emailInput = form.querySelector("input#resrv_id");
			const email = emailInput.value;
			this.login(email);
		}
	},
	login(email) {
		location.href = `./login?reservationEmail=${email}`;
	},
	formValidCheck() {
		const form = document.querySelector(".login_form");
		const emailInput = form.querySelector("input#resrv_id");
		const emailValid = this.validCheck(emailInput.name, emailInput)();
		return emailValid;
	},
	validCheck(type, input, warning) {
		return {
			resrv_email() {
				let mail = input.value;
				const mailValid = (/^[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]+$/).test(mail);
				if (!mailValid) {
					input.style.color = "red";
					input.value = "형식이 틀렸거나 너무 짧아요";
					setTimeout(() => {
						input.value = "";
						input.style.color = "black";
					}, 1000);
					return false;
				}
				return true;
			}
		}[type];
	},
}
document.addEventListener("DOMContentLoaded", function () {
	bookinglogin.initialize();
});