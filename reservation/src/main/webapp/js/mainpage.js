import API from "./util/api.js";

const mainpage = {
	initialize() {
		this.loadProducts();
		this.addTabClickEvent();
		this.addMoreClickEvent();
		this.startPromotionsSlide();
	},
	loadProducts(categoryId = 0, start = 0) {
		API.getProducts(categoryId, start).then(result => {
			this.updateProducts(result.totalCount, result.items);
		});
	},
	updateProducts(totalCount = 0, products = []) {
		const count = document.querySelector(".event_lst_txt > .pink");
		count.innerText = totalCount + "개";
		const leftProductList = document.querySelector(".lst_event_box.left");
		const rightProductList = document.querySelector(".lst_event_box.right");
		let leftResultHTML = "", rightResultHTML = "";
		const item = document.querySelector("#itemList").innerHTML;
		products.forEach((product, index) => {
			if (index % 2 == 0)
				leftResultHTML += this.getReplacedItem(item, product);
			else
				rightResultHTML += this.getReplacedItem(item, product);
		})
		rightProductList.innerHTML += rightResultHTML;
		leftProductList.innerHTML += leftResultHTML;
		const moreButton = document.querySelector(".more > button");
		const currentCount = moreButton.value = (parseInt(moreButton.value) + products.length);
		if (currentCount >= totalCount) {
			const moreButtonWrapper = document.querySelector(".more");
			moreButtonWrapper.style.display = "none";
		}
	},
	getReplacedItem(item = "", product = {}) {
		return item.replace("{id}", product.displayInfoId)
			.replace(/{description}/g, product.productDescription)
			.replace("{productImageUrl}", product.productImageUrl)
			.replace("{placeName}", product.placeName)
			.replace("{content}", product.productContent);
	},
	addTabClickEvent() {
		const tabList = document.querySelector(".section_event_tab > ul");
		tabList.addEventListener("click", event => {
			const tab = event.target.closest("li");
			if(!tab || !event.currentTarget.contains(tab))
				return;
			this.clearTabs();
			const category = tab.dataset.category;
			tab.querySelector("a").classList.add("active");
			this.loadProducts(category);
		});
	},
	addMoreClickEvent() {
		const moreButton = document.querySelector(".more > button");
		moreButton.addEventListener("click", () => {
			const tabItems = document.querySelectorAll(".section_event_tab > ul > li");
			let activeTab = tabItems[0];
			for (let tab of tabItems) {
				if (tab.querySelector("a").classList.contains("active")) {
					activeTab = tab;
					break;
				}
			}
			this.loadProducts(activeTab.dataset.category, moreButton.value);
		})
	},
	clearTabs() {
		const leftProductList = document.querySelector(".lst_event_box.left");
		const rightProductList = document.querySelector(".lst_event_box.right");
		leftProductList.innerHTML = rightProductList.innerHTML = "";
		const tabItems = document.querySelectorAll(".section_event_tab > ul > li");
		tabItems.forEach(tab => {
			tab.querySelector("a").classList.remove("active");
		})
		const moreButton = document.querySelector(".more > button");
		moreButton.value = 0;
		const moreButtonWrapper = document.querySelector(".more");
		moreButtonWrapper.style.display = "block";
	},
	startPromotionsSlide() {
		const slideWrapper = document.querySelector(".container_visual");
		let slideIndex = 0;
		const slides = document.querySelectorAll('.visual_img li');
		const totalSlides = slides.length;
		const sliderWidth = slideWrapper.clientWidth;
		slides.forEach(slide => {
			slide.style.width = sliderWidth + 'px';
		})
		const slider = document.querySelector('.visual_img');
		slider.style.width = sliderWidth * totalSlides + 'px';
		function plusSlides(n) {
			showSlides(slideIndex += n);
		}
		function showSlides(n) {
			slideIndex = n;
			if (slideIndex === totalSlides) {
				slideIndex = 0;
			}
			slider.style.transform = "translate(" + (-(sliderWidth * slideIndex) + 'px') + ")";
		}
		setInterval(() => {
			plusSlides(1);
		}, 3000);
	}
}
document.addEventListener("DOMContentLoaded", () => {
	mainpage.initialize();
});