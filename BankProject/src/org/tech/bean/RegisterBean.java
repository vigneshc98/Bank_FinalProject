package org.tech.bean;

public class RegisterBean {
	
	private String name,email,pass,phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		System.out.println(email);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
		System.out.println(pass);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		System.out.println(phone);
	}



}
