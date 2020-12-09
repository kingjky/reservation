class RatingPoint {
	constructor(ratingElement) {
		this.ratingElement = ratingElement;
		this.registerEvents();
	}
	registerEvents() {
		this.ratingElement.addEventListener("click", event => {
			const star = event.target;
			if (star.tagName !== "INPUT" || !event.currentTarget.contains(star))
				return;
			
			star.checked = false;
			let point = star.value;
			if(this.ratingElement.dataset.rating === point)
				point = 0;
			const inputList = this.ratingElement.querySelectorAll("input");
			for (const input of inputList) {
				if(input.value <= point)
					input.classList.add("checked");
				else
					input.classList.remove("checked");
			}
			const pointTag = this.ratingElement.querySelector(".star_rank");
			pointTag.textContent = point;
			this.ratingElement.dataset.rating = point;
		})
	}
	getRating() {
		return this.ratingElement.dataset.rating;
	}
}
export default RatingPoint;