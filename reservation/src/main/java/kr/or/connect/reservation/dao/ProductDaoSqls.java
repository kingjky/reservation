package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "SELECT d.id AS display_info_id " +
		", p.id AS product_id " +
		", d.place_name AS place_name " +
		", p.content AS product_content " +
		", p.description AS product_description " +
		", (SELECT save_file_name FROM file_info WHERE id = " +
		"(SELECT file_id FROM product_image WHERE type = 'th' AND product_id = p.id)) AS product_image_url " +
		"FROM display_info d, product p " +
		"WHERE d.product_id = p.id; ";

	public static final String SELECT_PAGING_USING_CATEGORY = "SELECT d.id AS display_info_id " +
		", p.id AS product_id " +
		", d.place_name AS place_name " +
		", p.content AS product_content " +
		", p.description AS product_description " +
		", (SELECT save_file_name FROM file_info WHERE id = " +
		"(SELECT file_id FROM product_image WHERE type = 'th' AND product_id = p.id) " +
		") AS product_image_url " +
		"FROM display_info d, product p " +
		"WHERE d.product_id = p.id AND p.category_id = :categoryId " +
		"ORDER BY p.id ASC limit :start, :limit; ";

	public static final String SELECT_COUNT_USING_CATEGORY = "SELECT COUNT(*) " + 
		"FROM product " +
		"WHERE category_id = :categoryId " +
		"GROUP BY category_id ";
}
