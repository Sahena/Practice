package com.practice.hello;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HelloWorld {

	private String printMessage;

	public String getPrintMessage() {
		
		return printMessage;
	}

	public void setPrintMessage(String printMessage) {
		this.printMessage = printMessage;
	}
	
}
