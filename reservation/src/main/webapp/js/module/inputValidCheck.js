const REGEX = {
	NAME: /[A-Za-z0-9가-힣]+/,
	EMAIL: /^[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]+$/,
	TEL: /^\d{2,3}-\d{3,4}-\d{4}$/,
	FILE: /^image\/(jpg|png|jpeg)$/,
	TEXT: /^(\w|\d|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{3}.*(\w|\d|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣])/,
}
const InputValidCheck = {
	checkName(name = "") {
		return REGEX.NAME.test(name);
	},
	checkEmail(email = "") {
		return REGEX.EMAIL.test(email);
	},
	checkTel(tel = "") {
		return REGEX.TEL.test(tel);
	},
	checkFileName(filename = "") {
		return REGEX.FILE.test(filename);
	},
	checkText(text = "") {
		return REGEX.TEXT.test(text);
	},
}
export default InputValidCheck;