import API from "./util/api.js";
import format from "./util/format.js";

const detail = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");
		this.loadDisplayInfo(displayInfoId);
		this.addTabClickEvent();
		this.addMoreClickEvent();
	},
	loadDisplayInfo(displayInfoId) {
		API.getDisplayInfo(displayInfoId).then(result => {
			this.updateProductDetail(result.averageScore,
				result.comments,
				result.displayInfo,
				result.displayInfoImage,
				result.productImages,
				result.productPrices);
		});
	},
	updateProductDetail(averageScore, comments, displayInfo, displayInfoImage, productImages, productPrices) {
		this.updateAverageScore(averageScore);
		this.updateComments(displayInfo, comments);
		this.updateDisplayInfo(displayInfo, displayInfoImage);
		this.updateProductImages(displayInfo, productImages);
	},
	updateAverageScore(averageScore) {
		const averagePointTag = document.querySelector(".average");
		averagePointTag.textContent = averageScore;
		const TO_PERCENT = 100;
		const totalPointTag = document.querySelector(".total"),
			totalPoint = Number(totalPointTag.textContent),
			percentage = (averageScore / totalPoint) * TO_PERCENT + "%";

		const graphValueTag = document.querySelector(".graph_value");
		graphValueTag.style.width = percentage;
	},
	updateComments(displayInfo, comments) {
		const commentsNum = comments.length,
			joinCountTag = document.querySelector(".join_count > .green");
		joinCountTag.innerText = commentsNum + "건";

		const COMMENTS_START = 0;
		const COMMENTS_LIMIT = 3;
		comments = comments.slice(COMMENTS_START, COMMENTS_LIMIT);

		const ReviewMoreTag = document.querySelector(".btn_review_more");
		ReviewMoreTag.href = `./review?id=${displayInfo.displayInfoId}`;
		if (commentsNum > COMMENTS_LIMIT)
			ReviewMoreTag.classList.remove("hide");

		this.bindComments(displayInfo, comments);
	},
	bindComments(displayInfo, comments) {
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
	updateDisplayInfo(displayInfo, displayInfoImage) {
		const TEL_LINK = "tel:";

		const bookAnchor = document.querySelector(".section_btn > a"),
			storeNameTag = document.querySelector(".store_name"),
			storeDetailTag = document.querySelector(".store_details > .dsc"),
			detailInfoTag = document.querySelector(".detail_info_lst > .in_dsc"),
			storeAddressBoldTag = document.querySelector(".store_addr_bold"),
			addrOldDetailTag = document.querySelector(".addr_old_detail"),
			addrDetailTag = document.querySelector(".addr_detail"),
			storeTelTag = document.querySelector(".store_tel");

		bookAnchor.href = `./reserve?id=${displayInfo.displayInfoId}`;
		storeNameTag.textContent = displayInfo.productDescription;
		storeDetailTag.textContent = displayInfo.productContent;
		detailInfoTag.textContent = displayInfo.productContent;
		storeAddressBoldTag.textContent = displayInfo.placeStreet;
		addrOldDetailTag.textContent = displayInfo.placeLot;
		addrDetailTag.textContent = displayInfo.placeName;
		storeTelTag.href = TEL_LINK + displayInfo.telephone;
		storeTelTag.textContent = displayInfo.telephone;

		this.updateDisplayInfoImage(displayInfoImage);
	},
	updateDisplayInfoImage(displayInfoImage) {
		const storeMapTag = document.querySelector(".store_map");
		storeMapTag.src = displayInfoImage.saveFileName;
	},
	updateProductImages(displayInfo, productImages) {
		const totalImagesLength = productImages.length,
			imageTemplate = document.querySelector("#imageTemplate").innerText,
			imageBindTemplate = Handlebars.compile(imageTemplate);
		let resultHTML = productImages.reduce((prev, next) => {
			return prev + imageBindTemplate(next);
		}, "");
		const imageList = document.querySelector(".visual_img");
		imageList.innerHTML = resultHTML;

		const visualTextList = document.querySelectorAll(".visual_txt_tit");
		visualTextList.forEach(visualText => {
			visualText.firstElementChild.textContent = displayInfo.productDescription;
		})
		const totalNumTag = document.querySelector(".figure_pagination").lastElementChild.firstElementChild;
		totalNumTag.textContent = productImages.length;

		const currentNumTag = document.querySelector(".figure_pagination").firstElementChild;
		const FIRST_IMAGE_NUMBER = 1;
		currentNumTag.textContent = FIRST_IMAGE_NUMBER;

		const MIN_IMAGE_NUM_TO_USE_BTN = 2;
		if (totalImagesLength >= MIN_IMAGE_NUM_TO_USE_BTN) {
			this.addNavClickSlideEvent();
		} else {
			const prevBtn = document.querySelector(".prev");
			prevBtn.style.display = "none";
			const nextBtn = document.querySelector(".nxt");
			nextBtn.style.display = "none";
		}
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
	addTabClickEvent() {
		const tabList = document.querySelector(".section_info_tab > ul.info_tab_lst"),
			detailTab = tabList.querySelector("._detail"),
			pathTab = tabList.querySelector("._path"),
			detailAreaWrap = document.querySelector(".detail_area_wrap"),
			detailLocation = document.querySelector(".detail_location");
		tabList.addEventListener("click", event => {
			const tab = event.target.closest("li");
			if (!tab || !event.currentTarget.contains(tab) || tab.querySelector("a")?.classList.contains("active"))
				return;

			tab.querySelector("a").classList.add("active");
			if (tab.classList.contains("_detail")) {
				pathTab.querySelector("a").classList.remove("active");
				detailAreaWrap.classList.remove("hide");
				detailLocation.classList.add("hide");
			} else {
				detailTab.querySelector("a").classList.remove("active");
				detailAreaWrap.classList.add("hide");
				detailLocation.classList.remove("hide");
			}
		});
	},
	addNavClickSlideEvent() {
		const slideWrapper = document.querySelector(".container_visual"),
			slides = document.querySelectorAll(".container_visual ul li"),
			totalSlides = slides.length,
			sliderWidth = slideWrapper.clientWidth;
		slides.forEach(element => {
			element.style.width = sliderWidth + "px";
		})
		const slider = document.querySelector(".container_visual > ul.detail_swipe");
		slider.style.width = sliderWidth * totalSlides + "px";
		slider.style.left = 0 + "px";

		const nextBtn = document.querySelector(".nxt"),
			prevBtn = document.querySelector(".prev");
		const NEXT = 1,
			PREV = -1;
		nextBtn.addEventListener("click", () => {
			plusSlides(NEXT);
		}, true);
		prevBtn.addEventListener("click", () => {
			plusSlides(PREV);
		}, true);

		const FIRST_IMAGE_INDEX = 0;
		let slideIndex = FIRST_IMAGE_INDEX;
		function plusSlides(n) {
			showSlides(slideIndex += n);
		}

		function showSlides(n) {
			slideIndex = n;
			if (slideIndex < FIRST_IMAGE_INDEX) {
				slideIndex = totalSlides + PREV;
			} else if (slideIndex === totalSlides) {
				slideIndex = FIRST_IMAGE_INDEX;
			}
			const prevIcon = document.querySelector(".prev i"),
				nextIcon = document.querySelector(".nxt i");
			if (slideIndex === FIRST_IMAGE_INDEX) {
				prevIcon.classList.add("off");
				nextIcon.classList.remove("off");
			} else if (slideIndex === (totalSlides + PREV)) {
				prevIcon.classList.remove("off");
				nextIcon.classList.add("off");
			} else {
				nextIcon.classList.remove("off");
				prevIcon.classList.remove("off");
			}
			const currentNumTag = document.querySelector(".figure_pagination")?.firstElementChild;
			currentNumTag.textContent = (slideIndex + NEXT);

			slider.style.left = -(sliderWidth * slideIndex) + "px";
		}
	}
}
document.addEventListener("DOMContentLoaded", () => {
	detail.initialize();
});