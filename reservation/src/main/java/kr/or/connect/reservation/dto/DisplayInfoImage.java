package kr.or.connect.reservation.dto;

public class DisplayInfoImage {
	long displayInfoImageId;
	long displayInfoId;
	long fileId;
	String fileName;
	String saveFileName;
	String contentType;
	boolean deleteFlag;
	String createDate;
	String modifyDate;
	public long getDisplayInfoImageId() {
		return displayInfoImageId;
	}
	public void setDisplayInfoImageId(long displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}
	public long getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
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
		return "DisplayInfoImage [displayInfoImageId=" + displayInfoImageId + ", displayInfoId=" + displayInfoId
			+ ", fileId=" + fileId + ", fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType="
			+ contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ "]";
	}
	
}
