package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Exceptions.AuthenticationException;
import Modal.Customers;
import Modal.OrderDetails;
import Modal.Orders;
import Modal.Products;
import util.Conn;

public class OrdersDetailsServicesImpl implements OrderDetailsServices{

	@Override
	public void calculateSubtotal(int OrderDetailID) {
		// TODO Auto-generated method stub
		Conn c=new Conn();
        String query1="select TotalAmount*Quantity AS Subtotal from OrderDetails Od inner join Orders o  ON Od.OrderID=o.OrderID where OrderDetailID='"+OrderDetailID+"'";
        try
        {
          ResultSet rs=c.s.executeQuery(query1);
          if(rs.next())
          {
        	  int subtotal=rs.getInt("Subtotal");
        	  System.out.println(subtotal);
          }
          else
          {
        	  throw new AuthenticationException("Not Found any OrderDetail");
          }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
		
	}

	@Override
	public void getOrderDetailInfo(int OrderDetailID)
	{
		  Conn c=new Conn();
	       String query="select * from OrderDetails Od INNER JOIN Products p ON Od.ProductID=p.ProductID Inner Join Orders o ON Od.OrderID=o.OrderID Inner Join Customers c ON o.CustomerID=c.CustomerID where Od.OrderDetailID='"+OrderDetailID+"'";
		// TODO Auto-generated method stub
			List<OrderDetails>Order=new ArrayList<>();

	       try
	       {
	    	   ResultSet rs=c.s.executeQuery(query);
	    	   while(rs.next())
	           {
	    	   Customers customer=new Customers(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getInt("Phone"),rs.getString("Address"));	   
	           Products product=new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("Price"));
	           Orders o=new Orders(rs.getInt("OrderID"),customer,rs.getString("OrderDate"),rs.getInt("TotalAmount"),rs.getString("status"));
	      	   Order.add(new OrderDetails(rs.getInt("OrderDetailID"),o,product,5));
	           }
	           for (OrderDetails detail : Order) 
	          {
	             System.out.println("OrderDetailID: "+detail.getOrderDetailID());
	             System.out.println("OrderID: "+ detail.getOrder().getOrderID());
	             System.out.println("CustomerID: "+detail.getOrder().getCustomer().getCustomerID());
	             System.out.println("CustomerName: "+detail.getOrder().getCustomer().getFirstName()+" "+detail.getOrder().getCustomer().getLastName());
	             System.out.println("Customer Email: "+detail.getOrder().getCustomer().getEmail());
	             System.out.println("Customer Phone: "+detail.getOrder().getCustomer().getPhone());
	             System.out.println("Custo0mer Address: "+detail.getOrder().getCustomer().getAddress());
	             System.out.println("OrderDate: "+detail.getOrder().getOrderDate());
	             System.out.println("TotalAmount: "+detail.getOrder().getTotalAmount());
	             System.out.println("OrderStatus: "+detail.getOrder().getStatus());
	             System.out.println(detail.getProduct().getProductName());
	             System.out.println(detail.getQuantity());
	             System.out.println("-----------------------");
	           }
	           }
	       catch(Exception e)
	       {
	    	   System.out.println(e.getMessage());
	       }
		
	}

	@Override
	public void updateQuantity(int OrderDetailID,int newQuantity)
	{
		// TODO Auto-generated method stub
		Conn c=new Conn();
		  System.out.println("Updating quantity for order detail ID: " + OrderDetailID);
	       String query="update OrderDetails set Quantity='"+newQuantity+"' WHERE OrderDetailID='"+OrderDetailID+"'";
	       try
	       {
	    	   c.s.executeUpdate(query);
	    	   System.out.println("Quantity for Order Details updated Successfully");
	       }
	       catch(Exception e)
	       {
	    	   System.out.println(e.getMessage());
	       }
		
	}

	@Override
	public void addDiscount(double discount, int OrderDetailID) {
		// TODO Auto-generated method stub System.out.println("Applying discount to order detail ID: " + orderDetailID);
		   Conn c=new Conn();
		   System.out.println("Connected");
		    //String query="select * from OrderDetails OD INNER JOIN Products P ON od.ProductID=p.ProductID INNER JOIN Orders o ON od.OrderID=o.OrderID Inner join Customers c ON o.CustomerID=c.CustomerID where OrderDetailID='"+OrderDetailID+"'";
	       //String query1="update Orders set TotalAmount='"+totalamount*(1-(discount/100))+"' Where orderID='"+OrderID+"'";
		  String query1="update Orders o Inner Join OrderDetails od ON o.OrderID=od.OrderID set o.TotalAmount=o.TotalAmount*(1-"+(discount/100)+")"+"  Where od.orderDetailID='"+OrderDetailID+"'";

	       try
	       {
	    	//ResultSet rs=c.s.executeQ(query);
	    	//int TotalAmount=0;
	    	//int OrderID=0;
		      //String query1="update Orders o set o.TotalAmount=o.TotalAmount*(1-"+(discount/100)+"' Inner Join OrderDetails od ON o.OrderID=od.OrderID Where o.orderID='"+OrderID+"'";
	    	/*String query1 = "UPDATE Orders o " +
	                "INNER JOIN OrderDetails od ON o.OrderID = od.OrderID " +
	                "SET o.TotalAmount = o.TotalAmount * (1 - " + (discount / 100) + ") " +
	                "WHERE o.OrderID = '" + OrderDetailID + "'";*/
	        c.s.executeUpdate(query1);
	        System.out.println("OrderID" +OrderDetailID+ "Updated");
	        System.out.println("Discount applied successfully.");
	       }
	       catch(Exception e)
	       {
	    	   System.out.println(e.getMessage());
	       }
		
	}

}
