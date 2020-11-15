package kr.or.connect.reservation.dao;

public class DisplayInfoDaoSqls {
	public static final String SELECT_USING_DISPLAY_INFO_ID = 
		"SELECT " + 
		"    p.id AS productId, " + 
		"    c.id AS categoryId, " + 
		"    d.id AS displayInfoId, " + 
		"    c.name AS categoryName, " + 
		"    p.description AS productDescription, " + 
		"    p.content AS productContent, " + 
		"    p.event AS productEvent, " + 
		"    d.opening_hours AS openingHours, " + 
		"    d.place_name AS placeName, " + 
		"    d.place_lot AS placeLot, " + 
		"    d.place_street AS placeStreet, " + 
		"    d.tel AS telephone, " + 
		"    d.homepage AS homepage, " + 
		"    d.email AS email, " + 
		"    d.create_date AS createDate, " + 
		"    d.modify_date AS modifyDate " + 
		"FROM " + 
		"    display_info AS d " + 
		"        JOIN " + 
		"    product AS p ON d.product_id = p.id " + 
		"        JOIN " + 
		"    category AS c ON p.category_id = c.id " + 
		"WHERE " + 
		"    d.id = :displayInfoId";
}
