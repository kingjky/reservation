import Formatter from "./format.js";

const Bind = {
	registerReviewTemplate(productDescription) {
		Handlebars.registerHelper("getProductDescription", () => productDescription);
		Handlebars.registerHelper("getFormatScore", score => Formatter.formatScore(score));
		Handlebars.registerHelper("getFormatEmail", reservationEmail => Formatter.formatEmail(reservationEmail));
		Handlebars.registerHelper("getFormatDate", reservationDate => Formatter.formatDate(reservationDate));
		return Handlebars.compile(document.querySelector("#reviewTemplate").innerText)
	},
	registerInfoTabTemplate(displayInfoImage) {
		Handlebars.registerHelper("getSaveFileName", () => displayInfoImage.saveFileName);
		Handlebars.registerHelper("getContentType", () => displayInfoImage.contentType);
		return Handlebars.compile(document.querySelector("#infoTabTemplate").innerText);
	},
	registerImageTemplate() {
		return Handlebars.compile(document.querySelector("#imageTemplate").innerText);
	},
	registerPriceTemplate(priceTypeList) {
		Handlebars.registerHelper("getTypeName", priceTypeName => priceTypeList[priceTypeName].name);
		Handlebars.registerHelper("getFormatPrice", price => Formatter.formatPrice(price));
		return Handlebars.compile(document.querySelector("#priceTemplate").innerText);
	},
	registerReservationTemplate(){
		return Handlebars.compile(document.querySelector("#cardTemplaate").innerText);
	}
}
export default Bind;