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
	private RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.reservationInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
		this.reservationPriceInsertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price")
			.usingGeneratedKeyColumns("id");
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
		params.put("cancel_flag", form.isCancelYn());
		params.put("create_date", form.getCreateDate());
		params.put("modify_date", form.getModifyDate());
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
}