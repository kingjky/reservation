package kr.or.connect.reservation.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.CommentImageDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentDao commentDao;
	private final CommentImageDao commentImageDao;

	public CommentServiceImpl(CommentDao commentDao, CommentImageDao commentImageDao) {
		this.commentDao = commentDao;
		this.commentImageDao = commentImageDao;
	}

	@Override
	public List<Comment> getCommentsUsingProductId(Integer productId) {
		List<Comment> comments = commentDao.selectAllUsingProductId(productId);
		return comments;
	}

	@Override
	public List<CommentImage> getCommentImagesUsingCommentId(Integer commentId) {
		List<CommentImage> commentImages = commentImageDao.selectAllUsingCommentId(commentId);
		return commentImages;
	}

	@Override
	public double getAverageScore(List<Comment> comments) {
		double result = 0;

		int size;
		if (comments == null || (size = comments.size()) == 0)
			return result;

		for (Comment comment : comments) {
			result += comment.getScore();
		}
		result /= size;
		result = (double)Math.round(result * 10) / 10;
		return result;
	}

}
