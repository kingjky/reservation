package kr.or.connect.reservation.dto;

import java.util.Date;

public class DisplayInfoImage {
	int displayInfoImageId;
	int displayInfoId;
	int fileId;
	String fileName;
	String saveFileName;
	String contentType;
	boolean deleteFlag;
	String createDate;
	String modifyDate;
	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}
	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
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