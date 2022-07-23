package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.User;
import com.model.UserRemote;

@ManagedBean(name = "user", eager = true)
public class UserBean
{
   int  userid;
   String name;
   String email;
   String password;
   String phonenumber;
   String ack;
   
   @EJB(lookup = "java:global/Realestate/UserModel!com.model.UserRemote")
   UserRemote ER;
   
   public void callinsert()
   {
	   try
	   {
		   User E = new User();
		   E.setUserid(userid);
		   E.setName(name);
		   E.setEmail(email);
		   E.setPassword(password);
		   E.setPhonenumber(phonenumber);
		   
		   ack = ER.insertData(E);
		   
	   }
	   catch(Exception e)
	   {
		   ack = e.getMessage();
	   }
   }
   
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public String getAck() {
	return ack;
}
public void setAck(String ack) {
	this.ack = ack;
}
}
