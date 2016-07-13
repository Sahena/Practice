package com.prac.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prac.jsf.common.DataConnect;
import com.prac.jsf.model.CategoryModel;

public class CategoryDao {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static boolean saveCategory(String cname) {
		boolean isSaved = false;
		String query = "Insert into category (cname) values (?)";

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, cname);

			int save = ps.executeUpdate();
			if (save == 1) {
				isSaved = true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			DataConnect.close(con);

		}
		return isSaved;
	}

	public static List<CategoryModel> displayCategory() {
		List<CategoryModel> clist = new ArrayList<CategoryModel>();
		String query = "Select * from Category";

		con = DataConnect.getConnection();

		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				CategoryModel cmodel = new CategoryModel();
				cmodel.setCid(rs.getInt("cid"));
				cmodel.setCname(rs.getString("cname"));
				clist.add(cmodel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataConnect.close(con);

		}
		return clist;
	}

	public static boolean deleteCategory(int cid) {
		boolean isDeleted = false;
		String query = "Delete from category where cid = ?";
		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, cid);

			int delete = ps.executeUpdate();

			if (delete == 1) {
				isDeleted = true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			DataConnect.close(con);

		}
		return isDeleted;

	}

	public static CategoryModel editCategory(int cid) {
		CategoryModel cmodel = new CategoryModel();
		String query = "select * from category where cid=?";
		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, cid);

			rs = ps.executeQuery();

			while (rs.next()) {

				cmodel.setCid(rs.getInt("cid"));
				cmodel.setCname(rs.getString("cname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return cmodel;
	}

	public static boolean updateCategory(int cid, String cname) {
		boolean isEdited = false;
		String query = "UPDATE category SET cname=? where cid =?";
		
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, cname);
			ps.setInt(2, cid);

			int edit = ps.executeUpdate();
			if (edit == 1) {
				isEdited = true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);

		}
		return isEdited;
	}

}
