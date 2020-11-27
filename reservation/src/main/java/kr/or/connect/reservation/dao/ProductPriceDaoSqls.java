package kr.or.connect.reservation.dao;

public class ProductPriceDaoSqls {
	public static final String SELECT_ALL_USING_PRODUCT_ID =
		"SELECT  " + 
		"    id AS productPriceId, " + 
		"    product_id AS productId, " + 
		"    price_type_name AS priceTypeName, " + 
		"    price, " + 
		"    discount_rate AS discountRate, " + 
		"    create_date AS createDate, " + 
		"    modify_date AS modifyDate " + 
		"FROM " + 
		"    product_price " + 
		"WHERE " + 
		"    product_id = :productId";
}
