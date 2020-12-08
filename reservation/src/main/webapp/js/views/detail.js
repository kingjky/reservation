import Api from "../module/api.js";
import Bind from "../module/bindTemplate.js";


const
	REVIEWS_START = 0,
	REVIEWS_LIMIT = 3,
	TOTAL_POINT = 5,
	IMAGE_START = 1,
	IMAGE_LIMIT = 2,
	NEXT = 1,
	PREV = -1,
	FIRST_IMAGE_INDEX = 0;

const detail = {
	initialize() {
		const url = new URL(window.location);
		this.displayInfoId = url.searchParams.get("id");

		this.visualWrapper = document.querySelector(".ct.main > .section_visual");
		this.storeDetailsWrapper = document.querySelector(".ct.main > .section_store_details");
		this.eventWrapper = document.querySelector(".ct.main > .section_event");
		this.bookingButtonWrapper = document.querySelector(".ct.main > .section_btn");
		this.reviewListWrapper = document.querySelector(".ct.main > .section_review_list");
		this.infoTabWrapper = document.querySelector(".ct.main > .section_info_tab");

		Api.getDisplayInfo(this.displayInfoId).then(result => {
			this.updateAverageScore(result.averageScore);
			this.updateReviewsInfo(result.comments);
			this.bindReviews(result.displayInfo.productDescription, result.comments.slice(REVIEWS_START, REVIEWS_LIMIT));
			this.updateDisplayInfo(result.displayInfo, result.displayInfoImage);
			this.updateProductImages(result.displayInfo, result.productImages);
			this.registerEvents();
		});
	},
	registerEvents() {
		const tabList = this.infoTabWrapper.querySelector("ul.info_tab_lst");
		tabList.addEventListener("click", event => this.tabClick(event));
		this.addMoreClickEvent();
		this.addNavSlideEvent();
	},
	updateAverageScore(averageScore) {
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .text_value > span.average")
			.textContent = averageScore;
		const percentage = (averageScore / TOTAL_POINT) * 100;
		this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .graph_mask > em.graph_value")
			.style.width = percentage + "%";
	},
	updateReviewsInfo(reviews) {
		const joinCountEl = this.reviewListWrapper.querySelector(".review_box > .short_review_area > .grade_area > .join_count > em.green");
		joinCountEl.innerText = reviews.length;
		const moreReviewButton = this.reviewListWrapper.querySelector("a.btn_review_more");
		moreReviewButton.href = `./review?id=${this.displayInfoId}`;
		if (reviews.length > REVIEWS_LIMIT)
			moreReviewButton.classList.remove("hide");
	},
	bindReviews(productDescription, reviews) {
		reviews = reviews.slice(REVIEWS_START, REVIEWS_LIMIT);
		const bindReview = Bind.registerReviewTemplate(productDescription);
		const resultHTML = reviews.reduce((prev, next) => prev + bindReview(next), ""),
			reviewList = this.reviewListWrapper.querySelector(".review_box > .short_review_area > ul.list_short_review");
		reviewList.innerHTML = resultHTML;
	},
	updateDisplayInfo(displayInfo, displayInfoImage) {
		this.storeDetailsWrapper.querySelector(".store_details > p.dsc")
			.textContent = displayInfo.productContent;
		this.bookingButtonWrapper.querySelector("a")
			.href = `./reserve?id=${this.displayInfoId}`;
		
			const bindInfoTab = Bind.registerInfoTabTemplate(displayInfoImage);
		this.infoTabWrapper.innerHTML = bindInfoTab(displayInfo);
	},
	updateProductImages(displayInfo, productImages) {
		const bindImage = Bind.registerImageTemplate();
		let resultHTML = productImages.reduce((prev, next) => prev + bindImage(next), "");

		const imageList = this.visualWrapper.querySelector(".group_visual > .container_visual > ul.visual_img");
		imageList.innerHTML = resultHTML;

		const visualTextList = imageList.querySelectorAll("li.item > .visual_txt > .visual_txt_inn > .visual_txt_tit > span");
		visualTextList.forEach(visualText => visualText.textContent = displayInfo.productDescription);

		const totalNumEl = this.visualWrapper.querySelector(".pagination > .figure_pagination > .num.off > span");
		totalNumEl.textContent = productImages.length;

		const currentNumEl = this.visualWrapper.querySelector(".pagination > .figure_pagination > span.num");
		currentNumEl.textContent = IMAGE_START;

		if (productImages.length < IMAGE_LIMIT)
			this.visualWrapper.querySelectorAll(".group_visual div.btn").forEach(btn => btn.style.display = "none")
	},
	addMoreClickEvent() {
		$(document).ready(() => {
			$("._open").on("click", () => {
				$("._open").attr("style", "display: none;");
				$("._close").attr("style", "display: block;");
				$(".store_details").removeClass("close3");
			});
			$("._close").on("click", () => {
				$("._close").attr("style", "display: none;");
				$("._open").attr("style", "display: block;");
				$(".store_details").addClass("close3");
			});
		});
	},
	tabClick(event) {
		const tab = event.target.closest("li");
		if (!tab || !event.currentTarget.contains(tab) || tab.querySelector("a.active"))
			return;
		this.infoTabWrapper.querySelectorAll(".info_tab_lst > li > a").forEach(el => el.classList.toggle("active"));
		this.infoTabWrapper.querySelector(".detail_area_wrap").classList.toggle("hide");
		this.infoTabWrapper.querySelector(".detail_location").classList.toggle("hide");
	},
	addNavSlideEvent() {
		let slideIndex, totalSlides, sliderWidth;
		slideIndex = totalSlides = sliderWidth = 0;
		const slideWrapper = this.visualWrapper.querySelector(".group_visual > .container_visual"),
			slides = this.visualWrapper.querySelectorAll(".group_visual > .container_visual > .visual_img > li"),
			slider = document.querySelector(".group_visual > .container_visual > ul.detail_swipe");

		totalSlides = slides.length;
		sliderWidth = slideWrapper.clientWidth;
		slides.forEach(slide => slide.style.width = sliderWidth + "px")
		slider.style.width = sliderWidth * totalSlides + "px";
		slider.style.left = 0 + "px";

		const prevBtn = this.visualWrapper.querySelector(".group_visual > .prev"),
			nextBtn = this.visualWrapper.querySelector(".group_visual > .nxt"),
			pageNum = this.visualWrapper.querySelector(".pagination > .figure_pagination > span.num");

		prevBtn.addEventListener("click", () => showSlides(PREV), true);
		nextBtn.addEventListener("click", () => showSlides(NEXT), true);

		function showSlides(n) {
			slideIndex += n;
			if (slideIndex < FIRST_IMAGE_INDEX)
				slideIndex = totalSlides + PREV;
			if (slideIndex === totalSlides)
				slideIndex = FIRST_IMAGE_INDEX;
			prevBtn.querySelector("i").classList.toggle("off");
			nextBtn.querySelector("i").classList.toggle("off");
			pageNum.textContent = slideIndex + 1;
			slider.style.left = -(sliderWidth * slideIndex) + "px";
		}
	},
}
document.addEventListener("DOMContentLoaded", () => {
	detail.initialize();
});