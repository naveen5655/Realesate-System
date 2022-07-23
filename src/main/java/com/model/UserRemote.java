package com.model;


import javax.ejb.Remote;
import com.entity.User;

@Remote
public interface UserRemote
{
  public String insertData(User U) throws Exception;
  public User findData(User E) throws Exception;
}
