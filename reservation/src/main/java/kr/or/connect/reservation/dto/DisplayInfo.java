package kr.or.connect.reservation.dto;

public class DisplayInfo {
	long productId;
	long categoryId;
	long displayInfoId;
	String categoryName;
	String productDescription;
	String productContent;
	String productEvent;
	String openingHours;
	String placeName;
	String placeLot;
	String placeStreet;
	String telephone;
	String homepage;
	String email;
	String createDate;
	String modifyDate;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getProductEvent() {
		return productEvent;
	}
	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public String getPlaceStreet() {
		return placeStreet;
	}
	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "DisplayInfo [productId=" + productId + ", categoryId=" + categoryId + ", displayInfoId=" + displayInfoId
			+ ", categoryName=" + categoryName + ", productDescription=" + productDescription + ", productContent="
			+ productContent + ", productEvent=" + productEvent + ", openingHours=" + openingHours + ", placeName="
			+ placeName + ", placeLot=" + placeLot + ", placeStreet=" + placeStreet + ", telephone=" + telephone
			+ ", homepage=" + homepage + ", email=" + email + ", createDate=" + createDate + ", modifyDate="
			+ modifyDate + "]";
	}
	
}
