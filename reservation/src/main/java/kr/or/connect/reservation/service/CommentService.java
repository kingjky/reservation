package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

public interface CommentService {
	public List<Comment> getCommentsUsingProductId(Integer productId);
	public List<CommentImage> getCommentImagesUsingCommentId(Integer commentId);
	List<Comment> getCommentsWithPagingUsingProductId(Integer productId, Integer start, Integer limit);
	public double getAverageScore(List<Comment> comments);
}
