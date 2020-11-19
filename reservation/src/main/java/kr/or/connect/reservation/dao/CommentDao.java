package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CommentDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectAllWithPagingUsingProductId(Integer productId, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PAGING_USING_PRODUCT_ID, params, rowMapper);
	}
	
	public List<Comment> selectAllUsingProductId(Integer productId) {
		Map<String, ?> params = Collections.singletonMap("productId", productId);
		return jdbc.query(SELECT_ALL_USING_PRODUCT_ID, params, rowMapper);
	}

}
