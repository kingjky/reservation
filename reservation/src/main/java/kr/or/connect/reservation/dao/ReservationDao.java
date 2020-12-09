package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.PriceForm;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationForm;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.*;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert reservationInsertAction;
	private SimpleJdbcInsert reservationPriceInsertAction;
	private SimpleJdbcInsert reservationUserCommentInsertAction;
	private SimpleJdbcInsert reservationUserCommentImageInsertAction;
	private SimpleJdbcInsert reservationFileInfoInsertAction;
	private RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.reservationInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id")
			.usingColumns("product_id", "display_info_id", "reservation_name", "reservation_tel", "reservation_email",
				"reservation_date");
		this.reservationPriceInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price")
			.usingGeneratedKeyColumns("id");
		this.reservationUserCommentInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment")
			.usingGeneratedKeyColumns("id")
			.usingColumns("product_id", "reservation_info_id", "score", "comment");
		this.reservationUserCommentImageInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment_image")
			.usingGeneratedKeyColumns("id");
		this.reservationFileInfoInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("file_info")
			.usingGeneratedKeyColumns("id")
			.usingColumns("file_name", "save_file_name", "content_type");
	}

	public List<Reservation> selectAllUsingEmail(String email) {
		Map<String, ?> params = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_ALL_USING_EMAIL, params, reservationRowMapper);
	};

	public Reservation selectUsingId(Long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_USING_ID, params, reservationRowMapper);
	};

	public Long insert(ReservationForm form) {
		Map<String, Object> params = new HashMap<>();
		params.put("product_id", form.getProductId());
		params.put("display_info_id", form.getDisplayInfoId());
		params.put("reservation_name", form.getReservationName());
		params.put("reservation_tel", form.getReservationTelephone());
		params.put("reservation_email", form.getReservationEmail());
		params.put("reservation_date", form.getReservationYearMonthDay());
		return reservationInsertAction.executeAndReturnKey(params).longValue();
	};

	public Integer updateCancel(Long reservationInfoId) {
		Map<String, ?> params = Collections.singletonMap("id", reservationInfoId);
		return jdbc.update(UPDATE_CANCEL_USING_Id, params);
	};

	public Long insertPrice(PriceForm form) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(form);
		return reservationPriceInsertAction.executeAndReturnKey(params).longValue();
	}

	public Integer selectTotalPrice(Long reservationInfoId) {
		Map<String, ?> params = Collections.singletonMap("id", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE_USING_EMAIL, params, Integer.class);
	}

	public Long insertComment(Long reservationInfoId, String productId, String score, String comment) {
		Map<String, Object> params = new HashMap<>();
		params.put("product_id", productId);
		params.put("reservation_info_id", reservationInfoId);
		params.put("score", score);
		params.put("comment", comment);
		return reservationUserCommentInsertAction.executeAndReturnKey(params).longValue();
	}

	public Long insertCommentImage(Long reservationInfoId, Long reservationUserCommentId, String originalFilename,
		String contentType) {
		Map<String, Object> params = new HashMap<>();
		params.put("file_name", originalFilename);
		params.put("save_file_name", "img/" + originalFilename);
		params.put("content_type", contentType);
		Long fileId = reservationFileInfoInsertAction.executeAndReturnKey(params).longValue();
		params = new HashMap<>();
		params.put("reservation_info_id", reservationInfoId);
		params.put("reservation_user_comment_id", reservationUserCommentId);
		params.put("file_id", fileId);
		return reservationUserCommentImageInsertAction.executeAndReturnKey(params).longValue();
		
	};
}
