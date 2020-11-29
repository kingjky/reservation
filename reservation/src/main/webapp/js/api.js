const api = {
	sendAjax(method, url, body) {
		const HTTP_STATUS_OK = 200;
		const oReq = new XMLHttpRequest();
		return new Promise(function (resolve, reject) {
			oReq.addEventListener("load", function () {
				if (oReq.status !== HTTP_STATUS_OK)
					reject(oReq.responseText);

				resolve(JSON.parse(oReq.responseText));
			}.bind(this))
			oReq.open(method, url);
			if(body && method === "POST"){
				oReq.setRequestHeader('Content-type', 'application/json');
				oReq.send(body);
			} else {
				oReq.send();
			}
		})
	},
	async getDisplayInfo(id) {
		var result = await this.sendAjax("GET", `./api/products/${id}`);
		return result;
	},
	async postBookingForm(jsonFormData) {
		 var result = await this.sendAjax("POST", `./api/reservations`, jsonFormData);
		return result;
	},
}

export default api;