package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection {
	
	static SessionFactory sessionFactory = null;
	static{
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static Session getConnection(){
		Session session = sessionFactory.openSession();
		System.out.println("In Session : "+session);
		return session;
	}

}
