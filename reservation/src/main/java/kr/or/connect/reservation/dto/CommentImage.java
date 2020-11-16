package kr.or.connect.reservation.dto;

public class CommentImage {
	int imageId;
	int reservationInfoId;
	int reservationUserCommentId;
	int fileId;
	String fileName;
	String saveFileName;
	String contentType;
	boolean deleteFlag;
	String createDate;
	String modifyDate;
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
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
		return "CommentImage [imageId=" + imageId + ", reservationInfoId=" + reservationInfoId
			+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + ", fileName=" + fileName
			+ ", saveFileName=" + saveFileName + ", contentType=" + contentType + ", deleteFlag=" + deleteFlag
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
