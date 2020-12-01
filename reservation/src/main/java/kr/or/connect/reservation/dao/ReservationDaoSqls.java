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

	public static final String SELECT_USING_ID = 
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
		"    id = :id";
	
	public static final String SELECT_TOTAL_PRICE_USING_EMAIL = 
		"SELECT " + 
		"    SUM(rip.count * pp.price) as sum " + 
		"FROM " + 
		"    reservation_info_price AS rip " + 
		"    join " + 
		"    product_price as pp on rip.product_price_id = pp.id " + 
		"WHERE " + 
		"    reservation_info_id = :id";
	
	public static final String UPDATE_CANCEL_USING_Id=
		"UPDATE reservation_info " + 
		"SET  " + 
		"    cancel_flag = 1 " + 
		"WHERE " + 
		"    id = :id ";
}
