const HTTP_REQUEST = {
	getDisplayInfo(displayInfoId) {
		return {
			method: "GET",
			url: `./api/products/${displayInfoId}`
		}
	},
	postBookingForm(jsonFormData) {
		return {
			method: "POST",
			url: `./api/reservations`,
			body: jsonFormData,
			bodyType: "json",
		}
	},
	getReservations(email) {
		return {
			method: "GET",
			url: `./api/reservations?reservationEmail=${email}`,
		}
	},
	cancelReservation(reservationInfoId) {
		return {
			method: "PUT",
			url: `./api/reservations/${reservationInfoId}`,
		}
	},
	getProducts(categoryId, start) {
		return {
			method: "GET",
			url: `./api/products?categoryId=${categoryId}&start=${start}`,
		}
	},
	postReview(reservationInfoId, productId, comment, score, formData) {
		return {
			method: "POST",
			url: `./api/reservations/${reservationInfoId}/comments?comment=${comment}&productId=${productId}&score=${score}`,
			body: formData,
		}
	}
}
const API = {
	sendAjax(request) {
		const HTTP_STATUS_OK = 200;
		const oReq = new XMLHttpRequest();
		return new Promise((resolve, reject) => {
			oReq.addEventListener("load", () => {
				if (oReq.status !== HTTP_STATUS_OK)
					reject(oReq.responseText);

				resolve(JSON.parse(oReq.responseText));
			})
			oReq.open(request.method, request.url);
			if (request.method === "POST") {
				if(request.bodyType === "json")
					oReq.setRequestHeader('Content-type', 'application/json');
				oReq.send(request.body);
			} else {
				oReq.send();
			}
		})
	},
	async getDisplayInfo(displayInfoId) {
		var result = await this.sendAjax(HTTP_REQUEST.getDisplayInfo(displayInfoId));
		return result;
	},
	async postBookingForm(jsonFormData) {
		var result = await this.sendAjax(HTTP_REQUEST.postBookingForm(jsonFormData));
		return result;
	},
	async getReservations(email) {
		var result = await this.sendAjax(HTTP_REQUEST.getReservations(email));
		return result;
	},
	async cancelReservation(reservationInfoId) {
		var result = await this.sendAjax(HTTP_REQUEST.cancelReservation(reservationInfoId));
		return result;
	},
	async getProducts(categoryId, start) {
		var result = await this.sendAjax(HTTP_REQUEST.getProducts(categoryId, start));
		return result;
	},
	async postReview(reservationInfoId, productId, comment, score, formData) {
		var result = await this.sendAjax(HTTP_REQUEST.postReview(reservationInfoId, productId, comment, score, formData));
		return result;
	},
}

export default API;