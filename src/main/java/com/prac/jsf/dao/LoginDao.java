package com.prac.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prac.jsf.common.DataConnect;

public class LoginDao {

	private static Connection con = null;
	private static PreparedStatement ps = null;

	public static boolean validate(String user, String password) {

		String query = "Select uname, password from USERS where UNAME=? and PASSWORD=?";
		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement(query);

			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Login error -->" + e.getMessage());
			return false;

		} finally {
			DataConnect.close(con);

		}
		return false;
	}
}
