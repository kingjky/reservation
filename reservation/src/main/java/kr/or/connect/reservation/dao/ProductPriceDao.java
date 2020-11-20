package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductPriceDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductPrice;

//XXX: Dao는 Dto Domain 별로 생성
@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public ProductPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductPrice> selectAllUsingProductId(Integer productId) {
		Map<String, ?> params = Collections.singletonMap("productId", productId);
		return jdbc.query(SELECT_ALL_USING_PRODUCT_ID, params, rowMapper);
	}
}
