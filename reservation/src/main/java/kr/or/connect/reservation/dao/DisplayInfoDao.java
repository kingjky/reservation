package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DisplayInfoDaoSqls.*;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectUsingDisplayInfoId(Long displayInfoId) {
		Map<String, ?> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_USING_DISPLAY_INFO_ID, params, rowMapper);
	}
}