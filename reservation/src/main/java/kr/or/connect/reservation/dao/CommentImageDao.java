package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CommentImageDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.CommentImage;

@Repository
public class CommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	
	public CommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<CommentImage> selectAllUsingCommentId(Integer commentId) {
		Map<String, ?> params = Collections.singletonMap("commentId", commentId);
		return jdbc.query(SELECT_ALL_USING_COMMENT_ID, params, rowMapper);
	}
}
