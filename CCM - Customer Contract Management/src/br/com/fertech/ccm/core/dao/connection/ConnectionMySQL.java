package br.com.fertech.ccm.core.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMySQL {

	private static final String urlDb = "jdbc:mysql://localhost:3306/ccm_db";
	private static final String userDb = "root";
	private static final String passDb = "root";
	
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			if(connection == null) {
				connection = DriverManager.getConnection(urlDb, userDb, passDb);
				return connection;
			}else {
				return connection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	//MÉTODO PARA TESTE DE CONEXÃO
//	public static void main(String[] args) {
//		
//		try {
//			Connection con = DriverManager.getConnection(urlDb, userDb, passDb);
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT NM_CLIENTE FROM CLIENTE");
//			while (rs.next()) {
//				System.out.println(rs.getString("NM_CLIENTE"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
}
