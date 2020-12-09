import Api from "../module/api.js";

const mainpage = {
	initialize() {
		this.slideWrapper = document.querySelector("#container > .event > .section_visual > .group_visual > .container_visual > div.container_visual");
		this.tabList = document.querySelector("#container > .event > .section_event_tab > ul.event_tab_lst.tab_lst_min");
		this.productCountEl = document.querySelector("#container > .event > .section_event_lst > .event_lst_txt > span.pink");
		this.leftProductList = document.querySelector("#container > .event > .section_event_lst > .wrap_event_box > ul.lst_event_box.left");
		this.rightProductList = document.querySelector("#container > .event > .section_event_lst > .wrap_event_box > ul.lst_event_box.right");
		this.moreButton = document.querySelector("#container > .event > .section_event_lst > .wrap_event_box > .more > button.btn");
		Api.getProducts().then(result => {
			this.updateProducts(result)
			this.registerEvents();
		});
	},
	registerEvents() {
		this.tabList.addEventListener("click", evt => this.tabClick(evt));
		this.moreButton.addEventListener("click", evt => this.moreClick(evt));
		this.startPromotionsSlide();
	},
	updateProducts(result) {
		const totalCount = result.totalCount, products = result.items;
		let leftHTML = "", rightHTML = "";
		products.forEach((product, idx) => {
			if (idx % 2 == 0)
				leftHTML += this.getProductHTML(product);
			else
				rightHTML += this.getProductHTML(product);
		});
		this.leftProductList.innerHTML += leftHTML;
		this.rightProductList.innerHTML += rightHTML;
		this.productCountEl.innerText = totalCount;
		this.moreButton.value = Number(this.moreButton.value) + products.length;
		if (this.moreButton.value >= totalCount)
			this.moreButton.parentNode.style.display = "none";
	},
	getProductHTML(product) {
		const item = document.querySelector("#productItem").innerHTML ?? "";
		return item.replace("{id}", product.displayInfoId)
			.replace(/{description}/g, product.productDescription)
			.replace("{productImageUrl}", product.productImageUrl)
			.replace("{placeName}", product.placeName)
			.replace("{content}", product.productContent);
	},
	tabClick(event) {
		const tab = event.target.closest("li");
		if (!tab || !event.currentTarget.contains(tab))
			return;
		this.clearTabs();
		const categoryId = tab.dataset.category;
		tab.querySelector("a").classList.add("active");
		Api.getProducts(categoryId).then(result => this.updateProducts(result));
	},
	moreClick(event) {
		const activeTab = this.tabList.querySelector("li a.active").parentNode;
		Api.getProducts(activeTab.dataset.category, event.currentTarget.value).then(result => this.updateProducts(result));
	},
	clearTabs() {
		this.leftProductList.innerHTML = this.rightProductList.innerHTML = "";
		this.tabList.querySelectorAll("li").forEach(tab => tab.querySelector("a").classList.remove("active"));
		this.moreButton.value = 0;
		this.moreButton.parentNode.style.display = "block";
	},
	startPromotionsSlide() {
		const slider = this.slideWrapper.querySelector('.visual_img'),
			slides = slider.querySelectorAll('li'),
			totalSlides = slides.length,
			sliderWidth = this.slideWrapper.clientWidth;
		let slideIndex = 0;

		slider.style.width = sliderWidth * totalSlides + 'px';
		slides.forEach(slide => slide.style.width = sliderWidth + 'px');
		function showSlides(n) {
			slideIndex += n;
			if (slideIndex === totalSlides)
				slideIndex = 0;
			slider.style.transform = "translate(" + (-(sliderWidth * slideIndex) + 'px') + ")";
		}
		setInterval(() => showSlides(1), 3000);
	},
}
document.addEventListener("DOMContentLoaded", () => {
	mainpage.initialize();
});