import api from './api.js';

const reserve = {
	initialize() {
		const url = new URL(window.location);
		const displayInfoId = url.searchParams.get("id");

		api.getDisplayInfo(displayInfoId).then(function (result) {
			this.updateProductReserve(result.averageScore,
				result.comments,
				result.displayInfo,
				result.displayInfoImage,
				result.productImages,
				result.productPrices);
		}.bind(this));
	},
	updateProductReserve(averageScore, comments, displayInfo, displayInfoImage, productImages, productPrices) {
		this.updateProductImages(displayInfo, productImages, productPrices);
		this.updateDisplayInfo(displayInfo);
	},
	updateDisplayInfo(displayInfo) {
		console.log(displayInfo);

		const BackBtn = document.querySelector(".btn_back");
		BackBtn.href = `./detail?id=${displayInfo.displayInfoId}`;

		const storeDetailsTag = document.querySelector(".store_details");
		storeDetailsTag.children[3].textContent = displayInfo.openingHours;
	},
	updateProductImages(displayInfo, productImages, productPrices) {
		console.log(productPrices);

		const visualImg = document.querySelector(".visual_img > li > img");
		visualImg.src = productImages[0].saveFileName;
		
		const priceTag = document.querySelector(".preview_txt").children[1];
		const minPrice = productPrices.reduce(function(prev, next){
			return Math.min(prev, next.price);
		}, Number.MAX_VALUE);
		priceTag.textContent = `₩${minPrice} ~ `;

	}
}

document.addEventListener("DOMContentLoaded", function () {
	reserve.initialize();
});