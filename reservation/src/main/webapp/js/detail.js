const detail = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");
		document.addEventListener("DOMContentLoaded", function () {
			this.loadDisplayInfo(displayInfoId);
			this.addTabClickEvent();
			this.addMoreClickEvent();
		}.bind(this));
	},
	loadDisplayInfo(displayInfoId) {
		const HTTP_STATUS_OK = 200;
		const oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function () {
			if (oReq.status !== HTTP_STATUS_OK) {
				return;
			}
			const displayInfoWrapper = JSON.parse(oReq.responseText);
			this.updateProductDetail(displayInfoWrapper.averageScore,
				displayInfoWrapper.comments,
				displayInfoWrapper.displayInfo,
				displayInfoWrapper.displayInfoImage,
				displayInfoWrapper.productImages,
				displayInfoWrapper.productPrices);
		}.bind(this));
		oReq.open("GET", `./api/products/${displayInfoId}`);
		oReq.send();
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
		if (commentsNum > COMMENTS_LIMIT) {
			ReviewMoreTag.classList.remove("hide");
		}

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
			month = month >= 10 ? month : `0${month}`;
			let day = date.getDate();
			day = day >= 10 ? day : `0${day}`;
			return `${year}.${month}.${day}`;
		});
		const commentTemplate = document.querySelector("#commentTemplate").innerText,
			commentBindTemplate = Handlebars.compile(commentTemplate);
		let resultHTML = comments.reduce(function (prev, next) {
			return prev + commentBindTemplate(next);
		}, "");
		const commentList = document.querySelector(".list_short_review");
		commentList.innerHTML = resultHTML;
	},
	updateDisplayInfo(displayInfo, displayInfoImage) {
		const bookAnchor = document.querySelector(".section_btn > a");
		bookAnchor.href = `./reserve?id=${displayInfo.displayInfoId}`;

		const storeNameTag = document.querySelector(".store_name");
		storeNameTag.textContent = displayInfo.productDescription;

		const storeDetailTag = document.querySelector(".store_details > .dsc"),
			detailInfoTag = document.querySelector(".detail_info_lst > .in_dsc");

		storeDetailTag.textContent = displayInfo.productContent;
		detailInfoTag.textContent = displayInfo.productContent;

		const storeAddressBoldTag = document.querySelector(".store_addr_bold");
		storeAddressBoldTag.textContent = displayInfo.placeStreet;

		const addrOldDetailTag = document.querySelector(".addr_old_detail");
		addrOldDetailTag.textContent = displayInfo.placeLot;

		const addrDetailTag = document.querySelector(".addr_detail");
		addrDetailTag.textContent = displayInfo.placeName;

		const storeTelTag = document.querySelector(".store_tel");
		const TEL_LINK = "tel:";
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
		let resultHTML = productImages.reduce(function (prev, next) {
			return prev + imageBindTemplate(next);
		}, "");
		const imageList = document.querySelector(".visual_img");
		imageList.innerHTML = resultHTML;

		const visualTextList = document.querySelectorAll(".visual_txt_tit");
		visualTextList.forEach(function (visualText) {
			visualText.firstElementChild.textContent = displayInfo.productDescription;
		})
		const totalNumTag = document.querySelector(".figure_pagination").lastElementChild.firstElementChild;
		totalNumTag.textContent = productImages.length;

		const currentNumTag = document.querySelector(".figure_pagination").firstElementChild;
		const FIRST_IMAGE_NUMBER = 1;
		currentNumTag.textContent = FIRST_IMAGE_NUMBER;

		const MIN_IMAGE_NUM_TO_USE_BTN = 2
		if (totalImagesLength >= MIN_IMAGE_NUM_TO_USE_BTN) {
			this.addNavClickSlideEvent();
		} else {
			const prevBtn = document.querySelector(".prev");
			prevBtn.style.display = "none";
			const nextBtn = document.querySelector(".nxt");
			nextBtn.style.display = "none";
		}
	},
	addMoreClickEvent(){
		$(document).ready(function () {
			$("._open").on("click", function () {
				$("._open").attr("style", "display: none;");
				$("._close").attr("style", "display: block;");
				$(".store_details").removeClass("close3");
			});
			$("._close").on("click", function () {
				$("._close").attr("style", "display: none;");
				$("._open").attr("style", "display: block;");
				$(".store_details").addClass("close3");
			});
		});
	},
	addTabClickEvent() {
		const tabList = document.querySelector(".section_info_tab > .info_tab_lst");
		tabList.addEventListener("click", function (event) {
			let tab;
			if (event.target.tagName === "SPAN")
				tab = event.target.parentNode.parentNode;
			else if (event.target.tagName === "A")
				tab = event.target.parentNode;
			else if (event.target.tagName === "LI")
				tab = event.target;
			else
				return;

			this.clearTabs();
			tab.firstChild.classList.add("active");
			if (tab.classList.contains("_detail")) {
				const detailLocation = document.querySelector(".detail_location");
				detailLocation.classList.add("hide");
			} else {
				const detailAreaWrap = document.querySelector(".detail_area_wrap");
				detailAreaWrap.classList.add("hide");
			}
		}.bind(this));
	},
	clearTabs() {
		const tabList = document.querySelector(".section_info_tab > .info_tab_lst");
		tabList.childNodes.forEach(function (tab) {
			if (tab.firstChild)
				tab.firstChild.classList.remove("active");
		});
		const detailAreaWrap = document.querySelector(".detail_area_wrap");
		detailAreaWrap.classList.remove("hide");
		const detailLocation = document.querySelector(".detail_location");
		detailLocation.classList.remove("hide");
	},
	addNavClickSlideEvent() {
		const slideWrapper = document.querySelector(".container_visual"),
			slides = document.querySelectorAll(".container_visual ul li"),
			totalSlides = slides.length,
			sliderWidth = slideWrapper.clientWidth;
		slides.forEach(function (element) {
			element.style.width = sliderWidth + "px";
		})
		const slider = document.querySelector(".container_visual > ul.detail_swipe");
		slider.style.width = sliderWidth * totalSlides + "px";
		slider.style.left = 0 + "px";

		const nextBtn = document.querySelector(".nxt");
		const prevBtn = document.querySelector(".prev");
		const NEXT = 1;
		const PREV = -1;
		nextBtn.addEventListener("click", function () {
			plusSlides(NEXT);
		}, true);
		prevBtn.addEventListener("click", function () {
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
			const prevIcon = document.querySelector(".prev i");
			const nextIcon = document.querySelector(".nxt i");
			if (slideIndex == FIRST_IMAGE_INDEX) {
				prevIcon.classList.add("off");
				nextIcon.classList.remove("off");
			} else if (slideIndex == (totalSlides + PREV)) {
				prevIcon.classList.remove("off");
				nextIcon.classList.add("off");
			} else {
				nextIcon.classList.remove("off");
				prevIcon.classList.remove("off");
			}
			const currentNumTag = document.querySelector(".figure_pagination").firstElementChild;
			currentNumTag.textContent = (slideIndex + NEXT);

			slider.style.left = -(sliderWidth * slideIndex) + "px";
		}
	}
}

detail.initialize();