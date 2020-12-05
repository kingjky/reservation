package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

public interface CommentService {
	List<Comment> getCommentsUsingProductId(Long productId);

	List<CommentImage> getCommentImagesUsingCommentId(Long commentId);
	
	CommentImage getCommentImageUsingImageId(Long imageId);

	double getAverageScore(List<Comment> comments);
}
