document.addEventListener("DOMContentLoaded", function() {
	initialize();
});

function initialize(){
	loadProducts();
	addTabClickEvent();
	addMoreClickEvent();
	startPromotionsSlide();
}

function loadProducts(categoryId = 0, start = 0) {
	
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load", function() {
		if(this.status !== 200) {
			const emptyCount = 0;
			const emptyProducts = [];
			updateProducts(emptyCount, emptyProducts);
			return;
		}
		var productsWrapper = JSON.parse(this.responseText);
		updateProducts(productsWrapper.totalCount, productsWrapper.items);
	});
	oReq.open("GET", `./api/products?categoryId=${categoryId}&start=${start}`);
	oReq.send();
}

function updateProducts(totalCount, products) {	
	var count = document.querySelector(".pink");
	count.innerText = totalCount + "개";
	
	var unorderedLists = document.querySelectorAll(".lst_event_box");
	
	var leftResultHTML = "";
	var rightResultHTML = "";
	
	var item = document.querySelector("#itemList").innerHTML;
	products.forEach((product, index) => {
		if(index % 2 == 0)
			leftResultHTML += getReplacedItem(item, product);
		else
			rightResultHTML += getReplacedItem(item, product);
		
	})
	unorderedLists[0].innerHTML += leftResultHTML;
	unorderedLists[1].innerHTML += rightResultHTML;
	
	var moreButton = document.querySelector(".more > button");
	var currentCount = moreButton.value = ( parseInt(moreButton.value) + products.length );
	if(currentCount >= totalCount){
		var moreButtonWrapper = document.querySelector(".more");
		moreButtonWrapper.style.display = "none";
	}
}

function getReplacedItem(item, product) {
	return item.replace("{id}", product.displayInfoId)
				.replace(/{description}/g, product.productDescription)
				.replace("{productImageUrl}", product.productImageUrl)
				.replace("{placeName}", product.placeName)
				.replace("{content}", product.productContent);
}

function addTabClickEvent() {
	var ul = document.querySelector(".section_event_tab > ul");
	ul.addEventListener("click",function(event) {
		var tab;
		if(event.target.tagName === "SPAN")
			tab = event.target.parentNode.parentNode;
		else if(event.target.tagName === "A")
			tab = event.target.parentNode;
		else if (event.target.tagName === "LI")
			tab = event.target;
		else
			return;

		clearTabs();
		var category = tab.dataset.category;
		tab.firstChild.classList.add("active");
		loadProducts(category);
	});
	
}

function addMoreClickEvent() {
	var moreButton = document.querySelector(".more > button");
	moreButton.addEventListener("click", function() {
		var tabItems = document.querySelectorAll(".section_event_tab > ul > li");
		var activeTab;
		for (var tab of tabItems) {
			if(tab.firstChild.classList.contains("active")){
				activeTab = tab;
				break;
			}
		}
		loadProducts(activeTab.getAttribute("data-category"), moreButton.value);
	})
}

function clearTabs() {
	var unorderedLists = document.querySelectorAll(".lst_event_box");
	var leftunorderedList = unorderedLists[0];
	var rightunorderedList = unorderedLists[1];
	leftunorderedList.innerHTML = rightunorderedList.innerHTML = "";
	
	var tabItems = document.querySelectorAll(".section_event_tab > ul > li");
	tabItems.forEach(li => {
		li.firstChild.classList.remove("active");
	})

	var moreButton = document.querySelector(".more > button");
	moreButton.value = 0;
	
	var moreButtonWrapper = document.querySelector(".more");
	moreButtonWrapper.style.display = "block";
}

function startPromotionsSlide() {
	var slideWrapper = document.querySelector(".container_visual");
	var slideIndex = 0;
	var slides = document.querySelectorAll('.visual_img li');
	var totalSlides = slides.length;
	var sliderWidth = slideWrapper.clientWidth;
	slides.forEach(function (element) {
		element.style.width = sliderWidth + 'px';
	})
	var slider = document.querySelector('.visual_img');
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

	var autoSlider = setInterval(function () {
		plusSlides(1);
	}, 3000);
}