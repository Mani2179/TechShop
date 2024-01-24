package Dao;
import Exceptions.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modal.*;
import util.Conn;

public class InventoryImpl implements InventoryServices{

	@Override
	public void addToInventory(int InventoryID, int quantity)
	{
		Conn c=new Conn();
		String query1="Update Inventory set QuantityStock=(QuantityStock+"+quantity+") where InventoryID='"+InventoryID+"'";
		try
		{
			c.s.executeUpdate(query1);
			System.out.println(quantity+ "added to invetory");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeFromInventory(int InventoryID, int quantity)
	{
		Conn c=new Conn();
		String query="select * from Inventory Where InventoryID='"+InventoryID+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			int Stock=0;
			if(rs.next())
			{
				Stock=rs.getInt("QuantityStock");
			if (Stock >= quantity) 
			{
	            String query3="update Inventory set QuantityStock=(QuantityStock-"+quantity+") where InventoryID='"+InventoryID+"'";
	            c.s.executeUpdate(query3);
	            System.out.println(quantity + " units removed from inventory.");
	        } 
			else 
			{
	            System.out.println("Insufficient quantity in stock.");
	            throw new InsufficientStockException("Insufficient stock");
		    }
			}
			else
			{
				throw new AuthenticationException("Invalid InventoryID");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateStockQuantity(int InventoryID, int newQuantity)
	{ 
		Conn c=new Conn();
		String query="Update Inventory set QuantityStock='"+newQuantity+"' WHERE InventoryID='"+InventoryID+"'";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("quantity updated successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void isProductAvailable(int InventoryID, int quantityToCheck) {
		// TODO Auto-generated method stub
		Conn c=new Conn();
		String query="select * from Inventory where InventoryID='"+InventoryID+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			if(rs.next())
			{
				int Stock=rs.getInt("QuantityStock");
				if(Stock>quantityToCheck)
				{
					System.out.println(Stock+"AVAILABLE");
				}
				else
				{
					//System.out.println("Insufficient Stock");
					//int diff=quantityToCheck-Stock;
					//System.out.println("ADD" +diff+ "to fulfill requiements");
					throw new InsufficientStockException("Insufficient stock to place order");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void getInventoryValue(int InventoryID) 
	{
		Conn c=new Conn();
	    String query="select * from Inventory i INNER JOIN Products p ON i.ProductID=p.ProductID where InventoryID='"+InventoryID+"'";
	    try
	    {
	    	ResultSet rs=c.s.executeQuery(query);
	    	if(rs.next())
	    	{
	    		int price=rs.getInt("Price");
	    		int quantity=rs.getInt("QuantityStock");
	    		int Total=price*quantity;
	    		System.out.println(Total);
	    	}
	    	else
	    	{
	    		throw new AuthenticationException("No inventory Found with this id");
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
		
	}
	@Override
	public void listLowStockProducts(int threshold) 
	{
		
		Conn c=new Conn();
        System.out.println("Listing low stock products...");
        
        try
        {
        String q1="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID";
        ResultSet rs2=c.s.executeQuery(q1);
        //int quantityInStock=rs2.getInt("QuantityStock");
        while(rs2.next())
        {
            int quantityInStock=rs2.getInt("QuantityStock");
        	List<String>ProductName=new ArrayList<>();
        
        if (quantityInStock <=threshold) 
        {
            ProductName.add(rs2.getString("ProductName"));
        }
        else
        {
        	throw new InsufficientStockException("No product below stock");
        }
            for (String name :ProductName) 
            {
               System.out.println(name+" "+rs2.getInt("ProductID"));
               //System.out.println(rs2.getInt("ProductID"));
             }
        }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}

	@Override
	public void listOutOfStockProducts() {
		// TODO Auto-generated method stub
		Conn c=new Conn();
        System.out.println("Listing out-of-stock products...");
     
        
        try
        {
        String q="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID";
        ResultSet rs2=c.s.executeQuery(q);
        //int quantityInStock=rs2.getInt("QuantityStock");
        while(rs2.next())
        {
            int quantityInStock=rs2.getInt("QuantityStock");
        	List<String>ProductName=new ArrayList<>();
        
        if (quantityInStock == 0) 
        {
            ProductName.add(rs2.getString("ProductName"));
        }
        else
        {
        	System.out.println("no out of stock");
        }
            for (String name :ProductName) 
            {
               System.out.println(name+" "+rs2.getInt("ProductID"));
               //System.out.println(rs2.getInt("ProductID"));
             }
        }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
		
	}

	@Override
	public void listAllProducts() {
		// TODO Auto-generated method stub
		Conn c=new Conn();
    	System.out.println("Listing all products");
    	String query1="select * from products p INNER JOIN Inventory i on p.productid=i.productid";
    	try 
    	(
    			ResultSet rs1= c.s.executeQuery(query1)) {
            List<String> addProducts= new ArrayList<>();

            while (rs1.next()) {
                addProducts.add(rs1.getString("ProductName"));
            }
            // Now, you can print or process the sorted order dates
            for (String name : addProducts) 
            {
                System.out.println(name);
            }
        }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
	}

}
