package Manoj;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Manoj.Customers;
import Manoj.OrderDetails;
class Orders{
    private int orderID;
    private Customers customer;
    private String orderDate;
    private int totalAmount;
    private String status;
    private List<OrderDetails> orderDetails;
List<Orders>o=new ArrayList<>();
Conn c=new Conn();
        Orders(int orderID, Customers customer,String orderDate,int totalAmount,String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount =totalAmount;
        this.status=status;
        this.orderDetails = new ArrayList<>();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    } 
    

    public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		
		this.totalAmount = totalAmount;
	}
	 
	      
	public void calculateTotalAmount(int ordeID) throws SQLException {
        System.out.println("Calculating total amount for order ID: " + orderID);
        String q1="select sum(TotalAmount) AS Total from Orders Where OrderID='"+orderID+"'";
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
   public void newOrders(int OID,int CID,String ODate,int OPrice,String NStatus) throws SQLException
   {
	   String q2="Insert into Orders Values('"+OID+"','"+CID+"','"+ODate+"','"+OPrice+"','"+NStatus+"')";
	   c.s.executeUpdate(q2);
	   System.out.println("Record inserted successfully");
   }
    public void getOrderDetails(Orders o,Customers cust) throws SQLException {
        System.out.println("Order Details for Order ID: " + orderID);
       
        //Customers c=new Customers(101,"Manoj","Mithra","mani22manoj@gmail.com",939817045,"2-75,kakinada");
   	// Products p=new Products(1101,"keyboard","Input",300);
   	//Orders o=new Orders(1,c,"20232-12-24","pending");
        Scanner sc=new Scanner(System.in);
        int OrderDetailID=sc.nextInt();
   try
   {
        Conn c=new Conn();
        String query3="select * from OrderDetails Od INNER JOIN Products p ON Od.ProductID=p.ProductID where OrderDetailID='"+OrderDetailID+"'";
        ResultSet rs2=c.s.executeQuery(query3);
        while(rs2.next())
        {
        Products p=new Products(rs2.getInt("ProductID"),rs2.getString("ProductName"),rs2.getString("Description"),rs2.getInt("Price"));
   	    orderDetails.add(new OrderDetails(rs2.getInt("OrderDetailID"),o,p,5));
        }
        for (OrderDetails detail : orderDetails) 
       {
            detail.getOrderDetailInfo();
        }
   }
   catch(Exception e)
   {
	   System.out.println(e.getMessage());
   }
 
    }

    public void updateOrderStatus(String newStatus) throws SQLException {
        System.out.println("Updating order status...");
        
        String query="update Orders set status='"+newStatus+"' where OrderID='"+orderID+"'";
        c.s.executeUpdate(query);
        System.out.println("Order status updated to: " + newStatus);
    }
    public void SortedOrderDate() throws SQLException
    {
    	String query2="select orderdate from orders";
    	try 
    	(
    			ResultSet rs = c.s.executeQuery(query2)) {
            List<String> orderDates = new ArrayList<>();

            while (rs.next()) {
              
                String orderDate = rs.getString("orderdate");
                orderDates.add(orderDate);
            }
            Collections.sort(orderDates);
          
            for (String date : orderDates) {
                System.out.println(date);
            }
        }
    }


    public void cancelOrder(int Orderid) throws SQLException {
        System.out.println("Cancelling order...");
      
        String query1="DELETE FROM Orders where OrderID='"+orderID+"'";
        c.s.executeUpdate(query1);
        System.out.println("Order cancelled successfully.");
    }
    public void getOrderStatus(String Ostatus)
    {
    	System.out.println("order status is"+ Ostatus);
    }
   public static void main(String[] args) throws SQLException
    {
	   //Conn c=new Conn();
	   Conn c=new Conn();
	   ResultSet rs=null;
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter OrderID:");
	   int orderid=sc.nextInt();
	   try
	   {
		   String q="select * from orders o Inner Join Customers c ON o.CustomerID=c.CustomerID where o.orderID='"+orderid+"'";
		   rs=c.s.executeQuery(q);
		   if(rs.next())
		   {
			   int OrderID=rs.getInt("OrderID");
			   String customerName=rs.getString("FirstName");
			   String OrderDate=rs.getString("LastName");
			   String status=rs.getString("status");
			   String orderdate=rs.getString("OrderDate");
			   int TotalAmount=rs.getInt("TotalAmount");
		   }
		   else
		   {
			   System.out.println("no order found");
		   }
		   
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e.getMessage());
	   }
	   Customers cust=new Customers(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("email"),rs.getInt("phone"),rs.getString("address"));
    	//Customers cust=new Customers(101,"Manoj","Mithra","mani22manoj@gmail.com",939817045,"2-75,kakinada");
    	 //Products p=new Products(1101,"keyboard","Input",300);
    	Orders o=new Orders(rs.getInt("OrderID"),cust,rs.getString("OrderDate"),rs.getInt("TotalAmount"),rs.getString("status"));
    	System.out.println("1.Display Order Details");
    	System.out.println("2.Update Order Status");
    	System.out.println("3.cancel order");
    	System.out.println("4.Total Amount for Orders");
    	System.out.println("5.Place Order");
    	System.out.println("6.Sort the orders");
    	System.out.println("7.get Order Status");
    	int ch=sc.nextInt();
    	switch(ch)
    	{
    	case 1:
    		System.out.println("Order Details");
    		o.getOrderDetails(o, cust);
    		break;
    	case 2:
    		System.out.println("Update Order Status");
    		String newStatus=sc.next();
    		o.updateOrderStatus(newStatus);
    		//o.getOrderDetails(o, cust);
    		break;
    	case 3:
    		System.out.println("Cancelling Order");
    		int Orderid=rs.getInt("OrderID");
    		o.cancelOrder(Orderid);
    		break;
    	case 4:
    		System.out.println("Total amount");
    		int orderID=rs.getInt("OrderID");
    		o.calculateTotalAmount(orderID);
    		break;
    	case 5:
    		System.out.println("New Order");
    		System.out.println("ENTER ORDERID:");
    		int OID=sc.nextInt();
    		System.out.println("ENTER CustomerID:");
    		int CID=sc.nextInt();
    		System.out.println("Enter OrderDate:");
    		String ODate=sc.next();
    		System.out.println("Enter OrderPrice:");
    		int OPrice=sc.nextInt();
    		System.out.println("Enter OrderStatus:");
    		String NStatus=sc.next();
    		o.newOrders(OID,CID,ODate,OPrice,NStatus);
    		break;
    	case 6:
    		System.out.println("Sort the list in ascending order");
    		o.SortedOrderDate();
    		break;
    	case 7:
    		
    		String Ostatus=rs.getString("status");
    		System.out.println("");
    		o.getOrderStatus(Ostatus);
    		break;
    		default:
    			System.out.println("Invalid choice");	
    	}
    }

private void getOrderStatus() {
	// TODO Auto-generated method stub
	
}
    }
   


