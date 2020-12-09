package kr.or.connect.reservation.dto;

public class Product {
	long productId;
	String productDescription, productContent;
	long displayInfoId;
	String placeName;
	String productImageUrl;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
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

	public long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(long displayInfoId) {
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
