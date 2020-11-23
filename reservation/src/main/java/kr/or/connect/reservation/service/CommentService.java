package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

public interface CommentService {
	List<Comment> getCommentsUsingProductId(Integer productId);

	List<CommentImage> getCommentImagesUsingCommentId(Integer commentId);

	double getAverageScore(List<Comment> comments);
}
