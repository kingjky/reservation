const review = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");
		const backTag = document.querySelector(".btn_back");
		backTag.href = "detail?id=" + displayInfoId;

		document.addEventListener("DOMContentLoaded", function () {
			this.loadDisplayInfo(displayInfoId);
		}.bind(this));
	},
	loadDisplayInfo(displayInfoId) {
		const HTTP_STATUS_OK = 200;
		const oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function () {
			if (oReq.status !== HTTP_STATUS_OK)
				return;
			
			const displayInfoWrapper = JSON.parse(oReq.responseText);
			this.updateAverageScore(displayInfoWrapper.averageScore);
			this.updateComments(displayInfoWrapper.displayInfo, displayInfoWrapper.comments);
		}.bind(this));
		oReq.open("GET", `./api/products/${displayInfoId}`);
		oReq.send();
	},
	updateAverageScore(averageScore) {
		const averagePointTag = document.querySelector(".text_value");
		averagePointTag.firstElementChild.textContent = averageScore;

		const TO_PERCENT = 100;
		const totalPointTag = document.querySelector(".total"),
			totalPoint = Number(totalPointTag.textContent),
			percentage = (averageScore / totalPoint) * TO_PERCENT + "%";

		const graphValueTag = document.querySelector(".graph_value");
		graphValueTag.style.width = percentage;
	},
	updateComments(displayInfo, comments) {
		const titleTag = document.querySelector(".title");
		titleTag.textContent = displayInfo.productDescription;
		titleTag.href = "detail?id=" + displayInfo.displayInfoId;

		const commentsNum = comments.length,
			joinCountTag = document.querySelector(".join_count > .green");
		joinCountTag.innerText = commentsNum + "건";

		this.bindComments(displayInfo, comments);
	},
	bindComments(displayInfo, comments){
		Handlebars.registerHelper("getProductDescription", function () {
			return displayInfo.productDescription;
		});
		Handlebars.registerHelper("getFormatScore", function (score) {
			const DECIMAL_STR = ".0";
			return score + DECIMAL_STR;
		});
		Handlebars.registerHelper("getFormatEmail", function (reservationEmail) {
			const SHOW_EMAIL_START = 0;
			const SHOW_EMAIL_LIMIT = 4;
			const MASAIC_STR = "****";
			return reservationEmail.slice(SHOW_EMAIL_START, SHOW_EMAIL_LIMIT) + MASAIC_STR;
		});
		Handlebars.registerHelper("getFormatDate", function (reservationDate) {
			const date = new Date(reservationDate);
			let year = date.getFullYear();
			let month = (1 + date.getMonth());
			month = month >= 10 ? month : '0' + month;
			let day = date.getDate();
			day = day >= 10 ? day : '0' + day;
			return year + '.' + month + '.' + day;
		});
		const commentTemplate = document.querySelector("#commentTemplate").innerText,
			commentBindTemplate = Handlebars.compile(commentTemplate);
		let resultHTML = comments.reduce(function (prev, next) {
			return prev + commentBindTemplate(next);
		}, "");
		const commentList = document.querySelector(".list_short_review");
		commentList.innerHTML = resultHTML;
	},
}
review.initialize();