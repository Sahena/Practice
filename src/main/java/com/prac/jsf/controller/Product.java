package com.prac.jsf.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.prac.jsf.dao.ProductDao;
import com.prac.jsf.model.ProductModel;

@ManagedBean
@RequestScoped
public class Product {

	@ManagedProperty(value = "#{productModel}")
	private ProductModel productModel;

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public String validateSaveProduct() {
		ProductDao pdao = new ProductDao();
		boolean isSaved = pdao.saveProduct(productModel);
		if (isSaved) {
			return "displayProduct";
		} else {
			return "false";
		}		
	}
	
	public List<ProductModel> getValidateDisplayProduct(){
		ProductDao pdao = new ProductDao();
		return pdao.displayProduct();
	}
	
	public String validateDeleteProduct(int pid){
		ProductDao pdao = new ProductDao();
		boolean isDeleted = pdao.deleteProduct(pid);
		if (isDeleted) {
			return "displayProduct";
		} else {
			return "false";
		}	
	}
	
	public String validateEditProduct(int pid){
		ProductDao pdao = new ProductDao();
		productModel=pdao.editProduct(pid);
		return "editProduct";
	}
	
	public String validateUpdateProduct(ProductModel productModel){
		ProductDao pdao = new ProductDao();
		boolean isEdited = pdao.updateProduct(productModel);
		if (isEdited) {
			return "displayProduct";
		} else {
			return "false";
		}	
	}
	
}
