import Api from '../module/api.js';
import Bind from '../module/bindTemplate.js';

const
	TOTAL_POINT = 5;

const review = {
	initialize() {
		const url = new URL(window.location);
		this.displayInfoId = url.searchParams.get("id");
		document.querySelector(".btn_back").href = "detail?id=" + this.displayInfoId;

		this.reviewHeader = document.querySelector(".ct > .wrap_review_list > .review_header");
		this.reviewListWrapper = document.querySelector(".ct > .wrap_review_list > .section_review_list");
		Api.getDisplayInfo(this.displayInfoId).then(result => {
			this.updateAverageScore(result.averageScore);
			this.updateReviews(result.displayInfo, result.comments);
		});
	},
	updateAverageScore(averageScore) {
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .text_value > span.average")
			.textContent = averageScore;
		const percentage = (averageScore / TOTAL_POINT) * 100;
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .graph_mask > em.graph_value")
			.style.width = percentage + "%";
	},
	updateReviews(displayInfo, comments) {
		const titleTag = this.reviewHeader.querySelector(".top_title > h2 > a.title");
		titleTag.textContent = displayInfo.productDescription;
		titleTag.href = "detail?id=" + displayInfo.displayInfoId;
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .join_count > .green")
			.innerText = comments.length + "건";
		this.bindReviews(displayInfo, comments);
	},
	bindReviews(displayInfo, comments) {
		const bindReview = Bind.registerReviewTemplate(displayInfo.productDescription);
		let resultHTML = comments.reduce((prev, next) => prev + bindReview(next), "");
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > ul.list_short_review")
			.innerHTML = resultHTML;
	},
}
document.addEventListener("DOMContentLoaded", () => {
	review.initialize();
});