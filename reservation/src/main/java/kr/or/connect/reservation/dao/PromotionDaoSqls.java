package kr.or.connect.reservation.dao;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = 
		"SELECT id, product_id, " +
		"(SELECT save_file_name FROM file_info WHERE id = " +
		"(SELECT file_id FROM product_image WHERE type = 'th' AND product_id = p.product_id)) AS product_image_url " +
		"FROM promotion p ";
}
