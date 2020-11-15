package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DisplayInfoImageDaoSqls.SELECT_USING_DISPLAY_INFO_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImage;
@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DisplayInfoImage selectUsingDisplayInfoId(Integer displayInfoId) {
		Map<String, ?> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_USING_DISPLAY_INFO_ID, params, rowMapper);
	}
}
