const format = {
	getFormatPrice(price) {
		let formatPrice = "";
		while (price > 0) {
			let mod = (price % 1000);
			if (mod === 0)
				mod = "000";
			formatPrice = mod + formatPrice;
			price = Math.round(price / 1000);
			if (price > 0)
				formatPrice = "," + formatPrice;
		}
		return formatPrice;
	},
	getFormatDate(dateString) {
		const date = new Date(dateString);
		let year = date.getFullYear();
		let month = (1 + date.getMonth());
		month = month >= 10 ? month : `0${month}`;
		let day = date.getDate();
		day = day >= 10 ? day : `0${day}`;
		return `${year}.${month}.${day}`;
	},
	getFormatScore(score) {
		let formatScore = Math.round(score * 10) / 10;
		if (formatScore % 1 == 0)
			formatScore += ".0";
		return formatScore;
	},
	getFormatEmail(email) {
		const SHOW_EMAIL_START = 0;
		const SHOW_EMAIL_LIMIT = 4;
		const MASAIC_STR = "****";
		return email.slice(SHOW_EMAIL_START, SHOW_EMAIL_LIMIT) + MASAIC_STR;
	}
}
export default format;