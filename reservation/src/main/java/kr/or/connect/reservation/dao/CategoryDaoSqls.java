package kr.or.connect.reservation.dao;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = 
		"SELECT  " + 
		"    id, " + 
		"    name, " + 
		"    (SELECT  " + 
		"            COUNT(*) " + 
		"        FROM " + 
		"            product p " + 
		"        WHERE " + 
		"            p.category_id = c.id " + 
		"        GROUP BY category_id) AS count " + 
		"FROM " + 
		"    category c";
}