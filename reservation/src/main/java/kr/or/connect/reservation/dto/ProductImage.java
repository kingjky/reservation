package kr.or.connect.reservation.dto;

public class ProductImage {
	long productId;
	long productImageId;
	String type;
	long fileInfoId;
	String fileName;
	String saveFileName;
	String contentType;
	boolean deleteFlag;
	String createDate;
	String modifyDate;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(long productImageId) {
		this.productImageId = productImageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(long fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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
		return "ProductImage [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
			+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", saveFileName=" + saveFileName
			+ ", contentType=" + contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}

}
