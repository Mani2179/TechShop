package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.AuthenticationException;
import Exceptions.InsufficientStockException;
import Modal.*;
import util.Conn;

public class ProductsServicesImpl implements ProductsServices {

	@Override
	public Products getProductDetails(int ProductID) 
	{
		    Products po=new Products();
	        Conn c = new Conn();
	        System.out.println("Connected to database");
	        String query = "select * from Products where ProductID='" +ProductID+"'";
	        try {
	            ResultSet rs = c.s.executeQuery(query);
	            // TODO: Populate the 'co' object with customer details
	            if (rs.next()) 
	            {
	                po.setProductID(rs.getInt("ProductID"));
	                po.setProductName(rs.getString("ProductName"));
	                po.setDescription(rs.getString("Description"));
	                po.setPrice(rs.getInt("Price"));
	            }
	            else
	            {
	            	throw new AuthenticationException("Product not found");
	            }
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return po;
		
	}

	@Override
	public void updateProductInfo(int productID,int newPrice, String newDescription) 
	{
		 System.out.println("Updating product information...");
	     Conn c=new Conn();
	        String query1="UPDATE Products set price='"+newPrice+"' WHERE ProductID='"+productID+"'";
	        String query2="UPDATE Products set Description='"+newDescription+"' WHERE ProductID='"+productID+"'";
	        try
	        {
	        c.s.executeUpdate(query1);
	        c.s.executeUpdate(query2);
	        System.out.println("Product Updated Successfully");
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
		
	}

	@Override
	public boolean isProductInStock(int ProductID) {
		Conn c=new Conn();
        String query="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID where p.ProductID='"+ProductID+"'";
        try
        {
        	ResultSet rs=c.s.executeQuery(query);
        	int pavailable=0;
        	while(rs.next())
        	{
        		pavailable=rs.getInt("QuantityStock");
        	}
        	if(pavailable>0)
        	{
        		System.out.println(pavailable+ "Product Available to place order");
        	}
        	else
        	{
        		System.out.println("Unable to place order");
        		throw new InsufficientStockException("NO STOCK");
        	}
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
		return false;
	}

	@Override
	public void createProduct(Products product) {
		Conn c=new Conn();
		int NProductID=product.getProductID();
		String NProductName=product.getProductName();
		String NDescription=product.getDescription();
		int Nprice=product.getPrice();
		
		String query="Insert into products values('"+NProductID+"','"+NProductName+"','"+NDescription+"','"+Nprice+"')";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Product inserted succesfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public void deleteProduct(int ProductID)
	{
		Conn c=new Conn();
		String query="delete from Products where ProductID='"+ProductID+"'";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Deleted Successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
