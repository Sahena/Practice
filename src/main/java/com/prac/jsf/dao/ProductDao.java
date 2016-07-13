package com.prac.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prac.jsf.common.DataConnect;
import com.prac.jsf.model.CategoryModel;
import com.prac.jsf.model.ProductModel;

public class ProductDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ProductDao() {
		con = DataConnect.getConnection();
	}

	public boolean saveProduct(ProductModel productModel) {
		boolean isSaved = false;
		String query = "Insert into product (pname,price,quantity,cid) values (?,?,?,?)";

		try {
			ps = con.prepareStatement(query);

			ps.setString(1, productModel.getPname());
			ps.setInt(2, productModel.getPrice());
			ps.setInt(3, productModel.getQuantity());
			ps.setInt(4, productModel.getCategoryModel().getCid());

			int save = ps.executeUpdate();
			if (save == 1) {
				isSaved = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);

		}
		return isSaved;
	}

	public List<ProductModel> displayProduct() {
		List<ProductModel> plist = new ArrayList<ProductModel>();
		String query = "Select * from product";

		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductModel pmodel = new ProductModel();
				pmodel.setPid(rs.getInt("pid"));
				pmodel.setPname(rs.getString("pname"));
				pmodel.setPrice(rs.getInt("price"));
				pmodel.setQuantity(rs.getInt("quantity"));
				pmodel.setCategoryModel(getCategoryById(rs.getInt("cid")));
				plist.add(pmodel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);

		}
		return plist;
	}

	private CategoryModel getCategoryById(int cid) {
		CategoryModel cmodel = new CategoryModel();
		PreparedStatement psmt = null;
		ResultSet rst = null;
		String query = "Select * from category where cid=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, cid);
			rst = psmt.executeQuery();

			while (rst.next()) {
				cmodel.setCid(rst.getInt("cid"));
				cmodel.setCname(rst.getString("cname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmodel;
	}

	public boolean deleteProduct(int pid) {
	 boolean isDeleted = false;
	 String query = "Delete from product where pid = ?";
	 try {
			ps = con.prepareStatement(query);
			ps.setInt(1, pid);
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

	public ProductModel editProduct(int pid) {
		ProductModel pmodel = new ProductModel();
		String query = "select * from product where pid=?";
		try {
			ps = con.prepareStatement(query);

			ps.setInt(1, pid);

			rs = ps.executeQuery();

			while (rs.next()) {

				pmodel.setPid(rs.getInt("pid"));
				pmodel.setPname(rs.getString("pname"));
				pmodel.setPrice(rs.getInt("price"));
				pmodel.setQuantity(rs.getInt("quantity"));
				pmodel.setCategoryModel(getCategoryById(rs.getInt("cid")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return pmodel;
	}

	public boolean updateProduct(ProductModel productModel) {
		boolean isEdited = false;
		String query = "UPDATE product SET pname=?, price=?, quantity=?, cid=? where pid =?";
		
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, productModel.getPname());
			ps.setInt(2, productModel.getPrice());
			ps.setInt(3, productModel.getQuantity());
			ps.setInt(4, productModel.getCategoryModel().getCid());
			ps.setInt(5, productModel.getPid());
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
