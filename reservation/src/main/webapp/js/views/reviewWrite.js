import Api from "../module/api.js";
import InputValidCheck from "../module/inputValidCheck.js";
import RatingPoint from "../component/RatingPoint.js";

const reviewWrite = {
	initialize() {
		const url = new URL(window.location);
		const reservationInfoId = url.searchParams.get("reservationInfoId");
		const productId = url.searchParams.get("productId");
		this.addRatingClickEvent();
		this.addReviewWriteClickEvent();
		this.addImageChangeEvent();
		this.addReviewChangeEvent();
		this.addSubmitClickEvent(reservationInfoId, productId);
	},
	addRatingClickEvent() {
		const ratingTag = document.querySelector(".review_rating.rating_point .rating");
		this.ratingPoint = new RatingPoint(ratingTag);
	},
	addReviewWriteClickEvent() {
		const reviewWriteInfo = document.querySelector(".review_write_info");
		const reviewTextArea = document.querySelector(".review_textarea");
		reviewWriteInfo.addEventListener("click", evt => {
			reviewWriteInfo.style.display = "none";
			reviewTextArea.focus();
		})
		reviewTextArea.addEventListener("blur", evt => {
			if (reviewTextArea.value.length === 0)
				reviewWriteInfo.style.display = "block";
		})
	},
	addImageChangeEvent() {
		const elImage = document.querySelector("#reviewImageFileOpenInput");
		elImage.addEventListener("change", evt => {
			const image = evt.target.files[0];
			if (!InputValidCheck.checkFileName(image.type)) {
				console.warn("invalid image file type");
				return;
			}
			const thumbnailImage = document.querySelector(".item_thumb");
			thumbnailImage.src = window.URL.createObjectURL(image);
			const thumbnailItem = document.querySelector(".lst_thumb li");
			thumbnailItem.style.display = "block";
		})
	},
	addReviewChangeEvent() {
		const reviewTextArea = document.querySelector(".review_textarea");
		const commentLengthTag = document.querySelector(".guide_review .length");
		const submitButton = document.querySelector(".box_bk_btn");
		reviewTextArea.addEventListener("keyup", () => {
			if (!InputValidCheck.checkText(reviewTextArea.value))
				submitButton.classList.add("disable");
			else
				submitButton.classList.remove("disable");

			if (reviewTextArea.value.length > 400)
				reviewTextArea.value = reviewTextArea.value.slice(0, 400);
			commentLengthTag.textContent = reviewTextArea.value.length;
		});
	},
	addSubmitClickEvent(reservationInfoId, productId) {
		var submitButton = document.querySelector(".bk_btn");
		submitButton.addEventListener("click", evt => {
			const reviewTextArea = document.querySelector(".review_textarea");
			const myFile = document.querySelector("#reviewImageFileOpenInput");
			const ratingTag = document.querySelector(".review_rating.rating_point .rating");

			var formData = new FormData();
			formData.append("attachedImage", myFile.files[0]);
			if (!InputValidCheck.checkText(reviewTextArea.value)) {
				alert("최소 5자 이상 입력하세요.");
				return;
			}
			if (this.ratingPoint.getRating() < 1) {
				alert("평점을 입력하세요.");
				return;
			}
			Api.postReview(reservationInfoId, productId, reviewTextArea.value, ratingTag.dataset.rating, formData).then(result => {
				console.log(result);
			})
			location.href = "./myreservation";
		});
	}
}
document.addEventListener("DOMContentLoaded", () => {
	reviewWrite.initialize();
});