package com.prac.jsf.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.prac.jsf.dao.CategoryDao;
import com.prac.jsf.model.CategoryModel;

@ManagedBean
@SessionScoped
public class Category {

	@ManagedProperty(value = "#{categoryModel}")
	private CategoryModel categoryModel;

	public CategoryModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public String validateSaveCategory() {

		boolean isSaved = CategoryDao.saveCategory(categoryModel.getCname());
		if (isSaved) {
			return "saved";
		} else {
			return "notSaved";
		}
	}

	public List<CategoryModel> getValidateDisplayCategory() {
		List<CategoryModel> clist = CategoryDao.displayCategory();
		return clist;
	}

	public String validateDeleteCategory(int cid) {
		boolean isDeleted = CategoryDao.deleteCategory(cid);

		if (isDeleted) {
			return "deleted";
		} else {
			return "not deleted";
		}

	}

	public String validateEditCategory(int cid) {

		categoryModel = CategoryDao.editCategory(cid);
		return "editCategory";

	}
	
	public String validateUpdateCategory(int cid){
		
		boolean isEdited = CategoryDao.updateCategory(cid,categoryModel.getCname());
		if(isEdited){
			return "displayCategory";
		}else 
			return "not edited";
	}
	
	
}
