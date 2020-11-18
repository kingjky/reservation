package kr.or.connect.reservation.dao;

/*
 * XXX: SQL에 Beautify 적용
 */
public class CommentDaoSqls {
	public static final String SELECT_ALL_USING_PRODUCT_ID =
		"SELECT  " + 
		"    c.id AS commentId, " + 
		"    c.product_id AS productId, " + 
		"    c.reservation_info_id AS reservationInfoId, " + 
		"    c.score, " + 
		"    c.comment, " + 
		"    r.reservation_name AS reservationName, " + 
		"    r.reservation_tel AS reservationTelephone, " + 
		"    r.reservation_email AS reservationEmail, " + 
		"    r.reservation_date AS reservationDate, " + 
		"    c.create_date AS createDate, " + 
		"    c.modify_date AS modifyDate " + 
		"FROM " + 
		"    reservation_user_comment AS c " + 
		"        JOIN " + 
		"    reservation_info AS r ON c.reservation_info_id = r.id " + 
		"WHERE " + 
		"    c.product_id = :productId " + 
		"ORDER BY c.modify_date DESC , c.create_date DESC , c.id DESC ";

}
