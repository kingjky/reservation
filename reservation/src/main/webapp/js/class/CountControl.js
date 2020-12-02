import format from "../util/format.js";

class CountControl {
	constructor(tabElement) {
		this.tabmenu = tabElement;
		this.registerEvents();
	}
	registerEvents() {
		this.tabmenu.addEventListener("click", event => {
			if (event.target.tagName === "A") {
				this.countControl(event.currentTarget, event.target.dataset?.value);
			}
		});
	}
	countControl(countBody, value) {
		const countControlInput = countBody?.querySelector(".count_control_input");
		let count = Number(countControlInput?.value);
		count += Number(value);
		const icoMinusBtn = countBody?.querySelector(".ico_minus3");
		const icoPlusBtn = countBody?.querySelector(".ico_plus3");
		const individualPriceTag = countBody?.querySelector(".individual_price");

		if (count < 0 || count > 10) {
			return;
		} else if (count == 0) {
			icoMinusBtn?.classList.add("disabled");
			countControlInput?.classList.add("disabled");
			individualPriceTag?.classList.remove("on_color");
		} else if (count == 10) {
			icoPlusBtn.classList.add("disabled");
		} else {
			icoMinusBtn?.classList.remove("disabled");
			countControlInput?.classList.remove("disabled");
			icoPlusBtn?.classList.remove("disabled");
			individualPriceTag?.classList.add("on_color");
		}

		countControlInput.value = count;

		const priceTag = countBody?.querySelector(".price");
		const price = Number(priceTag?.dataset.price);

		const totalPriceTag = countBody?.querySelector(".total_price");
		const totalPrice = price * count;
		totalPriceTag.textContent = format.getFormatPrice(totalPrice);

		const totalCountTag = document.querySelector("#totalCount");
		const totalCount = Number(totalCountTag?.textContent) + Number(value);
		totalCountTag.textContent = totalCount;
	}
}

export default CountControl;