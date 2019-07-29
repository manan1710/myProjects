package com.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.ProductBean;
import com.util.DbConnection;

public class ProductDao {

	public boolean insertProduct(ProductBean prodBean) {
		Session session = DbConnection.getConnection();
		Transaction tx = session.beginTransaction();

		try {
			
			int id = (Integer)session.save(prodBean);
			System.out.println("id : "+id);
			tx.commit();

		} catch (Exception e) {
			System.out.println("Exception Occurs !!");
			tx.rollback();
		}
		session.close();

		return true;
	}
	
	public void deleteProduct(int id)
	{
		Session session = DbConnection.getConnection();
		Transaction tx = session.beginTransaction();
		
		ProductBean pbean = session.get(ProductBean.class, id);
		
		if(pbean == null)
		{
			System.out.println("Invalid id...");
		}
		else{
			session.delete(pbean);
			tx.commit();
		}
		
		session.close();
	}
	
	/*public ProductBean getProductById(int id)
	{
		Session session = DbConnection.getConnection();
		ProductBean pbean = session.get(ProductBean.class, id);
		session.close();
		return pbean;
	}
	
	public void updateProduct(ProductBean pbean)
	{
		Session session = DbConnection.getConnection();
		session.update(pbean);
		session.close();
	}*/
	
	public void updateProduct(int id, String name)
	{
		Session session = DbConnection.getConnection();
		Transaction tx = session.beginTransaction();
		ProductBean pbean = session.load(ProductBean.class, id);
		pbean.setProductName(name);
		session.update(pbean);
		tx.commit();
		session.close();
	}
	
	public List<ProductBean> getAllProducts()
	{
		Session session = DbConnection.getConnection();
		List<ProductBean> list = session.createQuery("from ProductBean").list();
		session.close();
		return list;
	}
	
}
