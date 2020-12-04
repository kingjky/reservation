class AgreementControl {
	constructor(agreeElement) {
		this.agreement = agreeElement;
		this.registerEvents();
	}
	registerEvents() {
		this.agreement.addEventListener("click", event => {
			this.toggle(event);
		});
	}
	toggle(event) {
		let target = event.target;
		if (target.tagName !== "A")
			target = target.parentNode;
		if (target.tagName === "A") {
			if (event.currentTarget.classList.contains("open")) {
				event.currentTarget.classList.remove("open");
			} else {
				event.currentTarget.classList.add("open");
			}
		}
	}
}
export default AgreementControl;