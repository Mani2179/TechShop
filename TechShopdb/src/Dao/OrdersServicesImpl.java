package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Exceptions.AuthenticationException;
import Exceptions.IncompleteOrderException;
import Modal.*;
import util.Conn;

public class OrdersServicesImpl implements OrdersServices{

	@Override
	public void calculateTotalAmount(int OrderID) {
		// TODO Auto-generated method stub
		Conn c=new Conn();
		 System.out.println("Calculating total amount for order ID: " + OrderID);
	        String q1="select sum(TotalAmount) AS Total from Orders Where OrderID='"+OrderID+"'";
	        try
	        {
	        ResultSet rs1=c.s.executeQuery(q1);
	        if(rs1.next())
	        {
	        	int Total=rs1.getInt("Total");
	        	System.out.println(Total);
	        }
	        else
	        {
	        	System.out.println("No orders placed yet");
	        }
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
		
	}

	@Override
	public void newOrders(int OID, int CID, String ODate, int OPrice, String NStatus) 
	{
		Conn c=new Conn();
		String query="insert into Orders values('"+OID+"','"+CID+"','"+ODate+"','"+OPrice+"','"+NStatus+"')";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Order placed successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void getOrderDetails(int OrderID) {
		// TODO Auto-generated method stub
		List<OrderDetails>order=new ArrayList<>();
		Conn c=new Conn();
       String query="select * from OrderDetails Od INNER JOIN Products p ON Od.ProductID=p.ProductID Inner Join Orders o ON Od.OrderID=o.OrderID Inner Join Customers c ON o.CustomerID=c.CustomerID where o.OrderID='"+OrderID+"'";
       try
       {
    	   ResultSet rs=c.s.executeQuery(query);
    	   while(rs.next())
           {
    	   Customers customer=new Customers(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getInt("Phone"),rs.getString("Address"));	   
           Products product=new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("Price"));
           Orders o=new Orders(rs.getInt("OrderID"),customer,rs.getString("OrderDate"),rs.getInt("TotalAmount"),rs.getString("status"));
      	   order.add(new OrderDetails(rs.getInt("OrderDetailID"),o,product,5));
           }
           for (OrderDetails detail : order) 
          {
             System.out.println(detail.getOrderDetailID());
             System.out.println(detail.getOrder().getOrderID());
             System.out.println(detail.getOrder().getCustomer().getCustomerID());
             System.out.println(detail.getOrder().getCustomer().getFirstName()+" "+detail.getOrder().getCustomer().getLastName());
             System.out.println(detail.getOrder().getCustomer().getEmail());
             System.out.println(detail.getOrder().getCustomer().getPhone());
             System.out.println(detail.getOrder().getCustomer().getAddress());
             System.out.println(detail.getOrder().getOrderDate());
             System.out.println(detail.getOrder().getTotalAmount());
             System.out.println(detail.getOrder().getStatus());
             System.out.println(detail.getProduct().getProductName());
             System.out.println("-----------------------");
           }
    	   
    	  
   
       }
       catch(Exception e)
       {
    	   System.out.println(e.getMessage());
       }

	}

	@Override
	public void updateOrderStatus(int OrderID) 
	{
	  Scanner sc=new Scanner(System.in);
      Conn c=new Conn();
      System.out.println("Enter new Status");
      String newstatus=sc.next();
      String query="update Orders set status='"+newstatus+"' WHERE OrderID='"+OrderID+"'";
      try
      {
    	  c.s.executeUpdate(query);
    	  System.out.println("status updated");
    	  
      }
      catch(Exception e)
      {
    	  System.out.println(e.getMessage());
      }
		
	}

	@Override
	public void SortedOrderDate() {
		Conn c=new Conn();
		String query="select orderDate from orders";
		 List<String> orderDates = new ArrayList<>();
    	try 
    	{
              ResultSet rs=c.s.executeQuery(query);
            if(rs.next())
            {
            	while(rs.next())
            	{
              
                String orderDate = rs.getString("orderdate");
                orderDates.add(orderDate);
            	}
            }
            else
            {
            	throw new AuthenticationException("NO ORDERID");
            }
            Collections.sort(orderDates);
          
            for (String date : orderDates)
            {
                System.out.println(date);
            }
            
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	}

	@Override
	public void cancelOrder(int OrderID) {
		Conn c=new Conn();
		String query="delete from orders where OrderID='"+OrderID+"'";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Order Cancelled successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void getOrderStatus(int OrderID) {
		// TODO Auto-generated method stub
		Conn c=new Conn();
		String query="select * from Orders where OrderID='"+OrderID+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			if(rs.next())
			{
			String status=rs.getString("status");
			System.out.println(status);
			}
			else
			{
				System.out.println("No status");
				throw new AuthenticationException("No Order found");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
