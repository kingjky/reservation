const api = {
	sendAjax(method, url) {
		const HTTP_STATUS_OK = 200;
		const oReq = new XMLHttpRequest();
		return new Promise(function (resolve, reject) {
			oReq.addEventListener("load", function () {
				if (oReq.status !== HTTP_STATUS_OK)
					reject(oReq.responseText);

				resolve(JSON.parse(oReq.responseText));
			}.bind(this))
			oReq.open(method, url);
			oReq.send();
		})
	},
	async getDisplayInfo(id) {
		var result = await this.sendAjax("GET", `./api/products/${id}`);
		return result;
	},
}

export default api;