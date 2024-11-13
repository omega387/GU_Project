package com.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.Product;
public class ProductDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/pims";
	private String jdbcUserName="root";
	private String jdbcPassword="2003";

	private static final String INSERT_PRODUCT_SQL="INSERT INTO product"+"(pname,quantity,price) VALUES "+"(?,?,?,?);";
	private static final String SELECT_PRODUCT_BY_ID="SELECT * FROM product where id=?;";
	private static final String SELECT_ALL_PRODUCT="select * from product;";
	private static final String DELETE_PRODUCT_SQL="delete from product where id=?;";
	private static final String UPDATE_PRODUCT_SQL="update user set pname=?, quantity=?, price=? where id=?;";
	public ProductDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Connection getConnection()
	{
		Connection connection=null;
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	public void insertProduct(Product pro)
	{
		ProductDAO dao=new ProductDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_PRODUCT_SQL);
			preparedStatement.setString(1, pro.getName());
			preparedStatement.setInt(2, pro.getQuantity());
			preparedStatement.setInt(3, pro.getPrice());
			
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Product selectUser(int id)
	{
		Product pro=new Product();
		ProductDAO dao=new ProductDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_PRODUCT_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			pro.setId(id);	
			pro.setName(resultSet.getString("pname"));
			pro.setQuantity(resultSet.getInt("quantity"));
			pro.setPrice(resultSet.getInt("price"));
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return pro;
		
	}
	
	public List<Product> selectAllUsers()
	{
		List<Product> pro=new ArrayList<Product>();
		ProductDAO dao=new ProductDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_PRODUCT);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String pname=resultSet.getString("pname");
				int quantity=resultSet.getInt("quantity");
				int price=resultSet.getInt("price");
				
				
				pro.add(new Product(id,pname,quantity,price));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return pro;
	}
	public boolean deleteUser(int id)
	{
		boolean status=false;
		ProductDAO dao=new ProductDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_PRODUCT_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public boolean updateUser(Product pro)
	{
		boolean status=false;
		ProductDAO dao=new ProductDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_PRODUCT_SQL);
			preparedStatement.setString(1, pro.getName());
			preparedStatement.setInt(2, pro.getQuantity());
			preparedStatement.setInt(3, pro.getPrice());
			
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public static void main(String args[])
	   {
		   ProductDAO dao=new ProductDAO();
		   if(dao.getConnection()!=null)
		   {
			   System.out.println("Successfully connected to the database!!");
		   }
		   else
		   {   
			   System.out.println("Problem in database connection!!");
		   }
		   
		   //Data insertion
		   Product pro=new Product(0,"P1",13,300);
		   
		   //dao.insertUser(user);
		   
		   //select data by user id
		   Product pro1=dao.selectUser(1);
		   System.out.println(pro1);
		   
		   //select all users data
		   List<Product> prod=dao.selectAllUsers();
		   
		  for(Product u:prod)
		  {
			  System.out.println(u);
		  }
		  
		  //Update user
		  
		  pro=new Product(1,"test1@abc.com",34,400);
		  Boolean status=dao.updateUser(pro);
		  System.out.println(status);
			   
	   }
}
