package com.prac.jsf.controller;


import com.prac.jsf.dao.LoginDao;
import com.prac.jsf.model.LoginModel;
import com.prac.jsf.util.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Login{
	
	@ManagedProperty(value="#{loginModel}")
	private LoginModel loginModel;
	
	
	// validate login

	public LoginModel getLoginModel() {
		return loginModel;
	}

	public void setLoginModel(LoginModel loginModel) {
		this.loginModel = loginModel;
	}

	public String validateUsernamePassword() {
		boolean valid = LoginDao.validate(loginModel.getUserName(), loginModel.getPwd());

		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", loginModel.getUserName());
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Password",
							"Please enter correct username and password"));
			return "login";
		}

	}

	// logout event, invalidate session

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";

	}

}
