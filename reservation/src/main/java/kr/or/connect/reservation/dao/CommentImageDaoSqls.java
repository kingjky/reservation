package kr.or.connect.reservation.dao;

public class CommentImageDaoSqls {
	public static final String SELECT_ALL_USING_COMMENT_ID =
		"SELECT  " + 
		"    i.id AS imageId, " + 
		"    i.reservation_info_id AS reservationInfoId, " + 
		"    i.reservation_user_comment_id AS reservationUserCommentId, " + 
		"    i.file_id AS fileId, " + 
		"    f.file_name AS fileName, " + 
		"    f.save_file_name AS saveFileName, " + 
		"    f.content_type AS contentType, " + 
		"    f.delete_flag AS deleteFlag, " + 
		"    f.create_date AS createDate, " + 
		"    f.modify_date AS modifyDate " + 
		"FROM " + 
		"    reservation_user_comment_image i " + 
		"        JOIN " + 
		"    file_info f ON i.file_id = f.id " + 
		"WHERE " + 
		"    reservation_user_comment_id = :commentId";

	public static final String SELECT_USING_IMAGE_ID =
		"SELECT  " + 
			"    i.id AS imageId, " + 
			"    i.reservation_info_id AS reservationInfoId, " + 
			"    i.reservation_user_comment_id AS reservationUserCommentId, " + 
			"    i.file_id AS fileId, " + 
			"    f.file_name AS fileName, " + 
			"    f.save_file_name AS saveFileName, " + 
			"    f.content_type AS contentType, " + 
			"    f.delete_flag AS deleteFlag, " + 
			"    f.create_date AS createDate, " + 
			"    f.modify_date AS modifyDate " + 
			"FROM " + 
			"    reservation_user_comment_image i " + 
			"        JOIN " + 
			"    file_info f ON i.file_id = f.id " + 
			"WHERE " + 
			"    i.id = :imageId";
}
