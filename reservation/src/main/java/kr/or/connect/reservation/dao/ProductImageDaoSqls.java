package kr.or.connect.reservation.dao;

public class ProductImageDaoSqls {
	public static final String SELECT_ALL_USING_PRODUCT_ID =
		"SELECT  " + 
		"    i.product_id AS productId, " + 
		"    i.id AS productImageId, " + 
		"    i.type AS type, " + 
		"    f.id AS fileInfoId, " + 
		"    f.file_name AS fileName, " + 
		"    f.save_file_name AS saveFileName, " + 
		"    f.content_type AS contentType, " + 
		"    f.delete_flag AS deleteFlag, " + 
		"    f.create_date AS createDate, " + 
		"    f.modify_date AS modifyDate " + 
		"FROM " + 
		"    product_image AS i " + 
		"        JOIN " + 
		"    file_info AS f ON i.file_id = f.id " + 
		"WHERE " + 
		"    i.type = 'ma' AND product_id = :productId";
}
