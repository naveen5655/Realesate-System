package com.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.User;

import java.util.List;

import javax.ejb.*;

@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class UserModel implements UserRemote {

	@Override
	public String insertData(User E) throws Exception 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(E);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Registered successfully..!";
	}
	
	@Override
	public User findData(User username) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User E = em.find(User.class,username);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return E;
	}

}
