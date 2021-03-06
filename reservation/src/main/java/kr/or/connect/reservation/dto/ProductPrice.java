package kr.or.connect.reservation.dto;

public class ProductPrice {
	long productPriceId;
	long productId;
	String priceTypeName;
	int price;
	int discountRate;
	String createDate;
	String modifyDate;
	public long getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
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
		return "ProductPrice [productPriceId=" + productPriceId + ", productId=" + productId + ", priceTypeName="
			+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}
	
}
