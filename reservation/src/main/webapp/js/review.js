import API from './util//api.js';
import format from './util//format.js';

const review = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");
		const backTag = document.querySelector(".btn_back");
		backTag.href = "detail?id=" + displayInfoId;
		this.loadDisplayInfo(displayInfoId);
	},
	loadDisplayInfo(displayInfoId) {
		API.getDisplayInfo(displayInfoId).then(result => {
			this.updateAverageScore(result.averageScore);
			this.updateComments(result.displayInfo, result.comments);
		});
	},
	updateAverageScore(averageScore) {
		const averagePointTag = document.querySelector(".text_value");
		averagePointTag.firstElementChild.textContent = averageScore;

		const TO_PERCENT = 100;
		const totalPointTag = document.querySelector(".total"),
			totalPoint = Number(totalPointTag?.textContent),
			percentage = (averageScore / totalPoint) * TO_PERCENT + "%";

		const graphValueTag = document.querySelector(".graph_value");
		graphValueTag.style.width = percentage;
	},
	updateComments(displayInfo = {}, comments = []) {
		const titleTag = document.querySelector(".title");
		titleTag.textContent = displayInfo.productDescription;
		titleTag.href = "detail?id=" + displayInfo.displayInfoId;

		const commentsNum = comments.length,
			joinCountTag = document.querySelector(".join_count > .green");
		joinCountTag.innerText = commentsNum + "건";

		this.bindComments(displayInfo, comments);
	},
	bindComments(displayInfo, comments){
		Handlebars.registerHelper("getProductDescription", () => {
			return displayInfo.productDescription;
		});
		Handlebars.registerHelper("getFormatScore", score => {
			return format.getFormatScore(score);
		});
		Handlebars.registerHelper("getFormatEmail", reservationEmail => {
			return format.getFormatEmail(reservationEmail);
		});
		Handlebars.registerHelper("getFormatDate", reservationDate => {
			return format.getFormatDate(reservationDate);
		});
		const commentTemplate = document.querySelector("#commentTemplate").innerText,
			commentBindTemplate = Handlebars.compile(commentTemplate);
		let resultHTML = comments.reduce((prev, next) => {
			return prev + commentBindTemplate(next);
		}, "");
		const commentList = document.querySelector(".list_short_review");
		commentList.innerHTML = resultHTML;
	},
}
document.addEventListener("DOMContentLoaded", () => {
	review.initialize();
});