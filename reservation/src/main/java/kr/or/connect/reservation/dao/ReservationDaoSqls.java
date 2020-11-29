package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_ALL_USING_EMAIL = 
		"SELECT  " + 
		"    id as reservationInfoId, " + 
		"    product_id as productId, " + 
		"    display_info_id as displayInfoId, " + 
		"    reservation_name as reservationName, " + 
		"    reservation_tel as reservationTelephone, " + 
		"    reservation_email as reservationEmail, " + 
		"    cancel_flag as cancelYn, " + 
		"    reservation_date as reservationDate, " + 
		"    create_date as createDate, " + 
		"    modify_date as modifyDate " + 
		"FROM " + 
		"    reservation_info " + 
		"WHERE " + 
		"    reservation_email = :email";
}
