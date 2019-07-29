package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.bean.ProductBean;
import com.dao.ProductDao;

public class App {

	private ProductDao dao;
	private ProductBean prodBean;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		boolean isContinue = true;

		Scanner scanner = new Scanner(System.in);
		
		App app = null;

		while (isContinue) {
			System.out.println("<--------MENU-------->");
			System.out.println("1.Insert Product Data");
			System.out.println("2.Delete Product Data");
			System.out.println("3.Update Product Data");
			System.out.println("4.Get All Product Data");
			System.out.println("5.Exit");
			System.out.print("\nChoose any : ");

			int chosen = scanner.nextInt();
			int id = 0, price = 0; //initially
			String pname = "";

			switch (chosen) {
			case 1:
				app = new App();
				System.out.println("Enter name of product : ");
				pname = scanner.next();
				System.out.println("Enter price of product : ");
				price = scanner.nextInt();
				app.insertData(pname, price);
				break;
				
			case 2:
				app = new App();
				System.out.println("Enter id of product you want to delete : ");
				id = scanner.nextInt();
				app.deleteData(id);
				break;
				
			case 3:
				app = new App();
				System.out.println("Enter id of product you want to update : ");
				id = scanner.nextInt();
				System.out.println("Enter new name for product : ");
				pname = scanner.next();
				/*System.out.println("Enter new price for product : ");
				price = scanner.nextInt();*/
				app.updateData(id,pname);
				break;
				
			case 4:
				app = new App();
				System.out.println("Listing all Products : ");
				app.listProducts();
				break;
				

			case 5:
				System.out.println("Exiting Bye Bye !!");
				isContinue = false;
				break;

			default:
				System.out.println("Default block");
				break;
			}
		}

		// derby database connection
		/*
		 * String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		 * Class.forName(DRIVER); String dbURL1 =
		 * "jdbc:derby:webdb1;create=true"; Connection conn1 =
		 * DriverManager.getConnection(dbURL1); if (conn1 != null) {
		 * System.out.println("Connected to database #1"); }
		 */

	}

	public void listProducts() {
		dao = new ProductDao();
		List<ProductBean> list = dao.getAllProducts();
		for (ProductBean productBean : list) {
			System.out.println(productBean.getPid() + "," + productBean.getProductName() + "," + productBean.getPrice());
		}
	}

	public void updateData(int id, String pname) {
		/*dao = new ProductDao();
		prodBean = dao.getProductById(id);
		System.out.println(prodBean.getProductName()+" "+prodBean.getPrice());
		prodBean.setPid(id);
		prodBean.setProductName(pname);
		prodBean.setPrice(price);
		dao.updateProduct(prodBean);*/
		
		
		dao = new ProductDao();
		dao.updateProduct(id, pname);
		
	}

	public void deleteData(int id) {
		dao = new ProductDao();
		dao.deleteProduct(id);
	}

	public void insertData(String pname, int price) {
		dao = new ProductDao();
		prodBean = new ProductBean();

		prodBean.setProductName(pname);
		prodBean.setPrice(price);

		dao.insertProduct(prodBean);
	}

}
