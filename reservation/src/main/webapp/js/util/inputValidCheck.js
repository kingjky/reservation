const REGEX = {
	NAME: /[A-Za-z0-9가-힣]+/,
	EMAIL: /^[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]+$/,
	TEL: /^\d{2,3}-\d{3,4}-\d{4}$/,
	FILE: /^image\/(jpg|png|jpeg)$/,
	TEXT: /^(\w|\d|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{3}.*(\w|\d|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣])/,
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
	},
	getFileNameValid(filename = "") {
		return REGEX.FILE.test(filename);
	},
	getTextValid(text = "") {
		return REGEX.TEXT.test(text);
	},
}

export default inputValidCheck;