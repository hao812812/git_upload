package com.cathaybk.practice.nt50331.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Practice7 {

	public static final String QUERY_CARS_SQL = "select MANUFACTURER,TYPE,MIN_PRICE,PRICE from STUDENT.CARS where MANUFACTURER=? and TYPE=?";

	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE=?,PRICE=? where MANUFACTURER=? and TYPE=?";

	public static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER=? and TYPE=?";

	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";

	public static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";

	public static final String ACCOUNT = "student";

	public static final String PASSWORD = "student123456";

	public static void main(String[] args) {
		
		 //doQuery();
		doMethod();

	}

	public static void doMethod() {

		System.out.println("請選擇以下指令輸入:select、insert、update、delete");
		Scanner sc = new Scanner(System.in);
		String order = sc.next();

		if ("insert".equals(order)) {
			insert();
		} else if ("select".equals(order)) {
			select();
		} else if ("update".equals(order)) {
			update();
		} else if ("delete".equals(order)) {
			delete();
		} else {
			System.out.println("指令輸入錯誤!");
		}
		sc.close();
	}

//insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)
	public static void insert() {

		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				Scanner sc = new Scanner(System.in);
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL);) {
			try {
				conn.setAutoCommit(false);

				System.out.println("請輸入製造商:");
				String manufacture = sc.next();
				System.out.println("請輸入型號:");
				String type = sc.next();
				System.out.println("請輸入底價:");
				String minPrice = sc.next();
				System.out.println("請輸入售價:");
				String price = sc.next();
				pstmt.setString(1, manufacture);
				pstmt.setString(2, type);
				pstmt.setString(3, minPrice);
				pstmt.setString(4, price);
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("新增成功");

			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				conn.rollback();

			}
		} catch (SQLException e) {
			System.out.println("失敗，原因：" + e.getMessage());
		}
	}

//select MANUFACTURER,TYPE,MIN_PRICE,PRICE from STUDENT.CARS where MANUFACTURER=? and TYPE=?
	public static void select() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				Scanner sc = new Scanner(System.in);
				PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL);) {

			System.out.println("請輸入製造商:");
			String manufacture = sc.next();
			System.out.println("請輸入型號:");
			String type = sc.next();
			pstmt.setString(1, manufacture);
			pstmt.setString(2, type);

			// ResultSet物件儲存查詢結果
			ResultSet rs = pstmt.executeQuery();

			// 使用StringBuilder做字串串接
			StringBuilder sb = new StringBuilder();
			sb.append("查詢結果： " + "\n");

			while (rs.next()) {
				sb.append("製造商： ").append(rs.getString("MANUFACTURER") + "\n").append("型號：")
						.append(rs.getString("TYPE") + "\n").append("售價：").append(rs.getString("PRICE") + "\n")
						.append("底價：").append(rs.getString("MIN_PRICE") + "\n");
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//update STUDENT.CARS set MIN_PRICE=?,PRICE=? where MANUFACTURER=? and TYPE=?
	public static void update() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				Scanner sc = new Scanner(System.in);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL);) {
			try {

				conn.setAutoCommit(false);

				System.out.println("請輸入製造商:");
				String manufacture = sc.next();
				System.out.println("請輸入型號:");
				String type = sc.next();
				System.out.println("請輸入更改的最低價格:");
				String minPrice = sc.next();
				System.out.println("請輸入更改的售價:");
				String price = sc.next();

				pstmt.setString(1, minPrice);
				pstmt.setString(2, price);
				pstmt.setString(3, manufacture);
				pstmt.setString(4, type);
				pstmt.executeQuery(); // 執行SQL指令

				conn.commit();
				System.out.println("更改成功");

			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println("失敗，原因：" + e.getMessage());
		}
	}

	// delete from STUDENT.CARS where MANUFACTURER=? and TYPE=?
	public static void delete() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				Scanner sc = new Scanner(System.in);
				PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL);) {
			try {

				conn.setAutoCommit(false);
				System.out.println("請輸入要刪除的製造商:");
				String manufacture = sc.next();
				System.out.println("請輸入要刪除的型號:");
				String type = sc.next();

				pstmt.setString(1, manufacture);
				pstmt.setString(2, type);
				pstmt.executeUpdate();
				System.out.println("刪除一筆資料成功");
				conn.commit();

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}

		} catch (SQLException e1) {
			System.out.println("失敗，原因：" + e1.getMessage());
		}
	}

	public static void doQuery() {
		List<Map<String, String>> carList = new ArrayList<Map<String, String>>();
		Map<String, String> carMap = new HashMap<String, String>();

		try (Connection conn = DriverManager.getConnection(CONN_URL, ACCOUNT, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement("select * from STUDENT.CARS");) {

			// ResultSet物件儲存查詢結果
			ResultSet rs = pstmt.executeQuery();

			// 讀取cars table資料加入list，再存成map後輸出
			while (rs.next()) {
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carList.add(carMap);
				System.out.println(carList.get(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
