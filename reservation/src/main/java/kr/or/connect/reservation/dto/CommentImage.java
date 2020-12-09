package kr.or.connect.reservation.dto;

public class CommentImage {
	long imageId;
	long reservationInfoId;
	long reservationUserCommentId;
	long fileId;
	String fileName;
	String saveFileName;
	String contentType;
	boolean deleteFlag;
	String createDate;
	String modifyDate;
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public long getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
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
		return "CommentImage [imageId=" + imageId + ", reservationInfoId=" + reservationInfoId
			+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + ", fileName=" + fileName
			+ ", saveFileName=" + saveFileName + ", contentType=" + contentType + ", deleteFlag=" + deleteFlag
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
