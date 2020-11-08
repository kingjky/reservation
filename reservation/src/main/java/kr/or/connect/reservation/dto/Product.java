package kr.or.connect.reservation.dto;

public class Product {
	int productId;
	String productDescription, productContent;

	int displayInfoId;
	String placeName;

	String productImageUrl;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + "\n\t, productDescription=" + productDescription
			+ "\n\t, productContent="
			+ productContent + "\n\t, displayInfoId=" + displayInfoId + "\n\t, placeName=" + placeName
			+ "\n\t, productImageUrl="
			+ productImageUrl + "\n]";
	}
}
