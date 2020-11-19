package kr.or.connect.reservation.dao;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_USING_DISPLAY_INFO_ID = 
		"SELECT  " + 
		"    i.id AS displayInfoImageId, " + 
		"    i.display_info_id AS displayInfoId, " + 
		"    f.id AS fileId, " + 
		"    f.file_name AS fileName, " + 
		"    f.save_file_name AS saveFileName, " + 
		"    f.content_type AS contentType, " + 
		"    f.delete_flag AS deleteFlag, " + 
		"    f.create_date AS createDate, " + 
		"    f.modify_date AS modifyDate " + 
		"FROM " + 
		"    display_info_image AS i " + 
		"        JOIN " + 
		"    file_info AS f ON i.file_id = f.id " + 
		"WHERE " + 
		"    display_info_id = :displayInfoId";
}
