package kr.or.connect.reservation.dto;

import java.util.List;

public class Comment {
	long commentId, productId, reservationInfoId;
	double score;
	String comment;
	String reservationName, reservationTelephone, reservationEmail;
	String reservationDate, createDate, modifyDate;
	List<CommentImage> commentImages;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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
	public List<CommentImage> getCommentImages() {
		return commentImages;
	}
	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", productId=" + productId + ", reservationInfoId="
			+ reservationInfoId + ", score=" + score + ", comment=" + comment + ", reservationName=" + reservationName
			+ ", reservationTelephone=" + reservationTelephone + ", reservationEmail=" + reservationEmail
			+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ ", commentImages=" + commentImages + "]";
	}
	
}
