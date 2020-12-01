const REGEX = {
	NAME: /[A-Za-z0-9가-힣]+/,
	EMAIL: /^[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]+$/,
	TEL: /^\d{2,3}-\d{3,4}-\d{4}$/,
}

const inputValidCheck = {
	getNameValid(name = "") {
		return REGEX.NAME.test(name);
	},
	getEmailValid(email = "") {
		return REGEX.EMAIL.test(email);
	},
	getTelValid(tel = "") {
		return REGEX.TEL.test(tel);
	}
}

export default inputValidCheck;