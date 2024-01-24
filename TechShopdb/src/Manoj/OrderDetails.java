package Manoj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class OrderDetails{
    private int orderDetailID;
    private Orders order;
    private Products p;
    private int quantity;
    int ProductID=0;
    Conn c=new Conn();
    Scanner sc=new Scanner(System.in);

    public OrderDetails(int orderDetailID, Orders order, Products p, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.p = p;
        this.quantity = quantity;
    }
    List<Orders>ord=new ArrayList<>();
    List<Products>pro=new ArrayList<>();

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }
    

    public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Products getProduct() {
		return p;
	}

	public void setProduct(Products p) {
		this.p = p;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void calculateSubtotal() throws SQLException {
        System.out.println("Calculating subtotal for order detail ID: " + orderDetailID);
        String query1="select TotalAmount*Quantity AS Subtotal from OrderDetails Od inner join Orders o  ON Od.OrderID=o.OrderID where OrderDetailID='"+orderDetailID+"'";
        try
        {
        ResultSet rs2=c.s.executeQuery(query1);
        while(rs2.next())
        {
        	int subTotal=rs2.getInt("Subtotal");
        System.out.println(subTotal);
        }
        System.out.println("Calculated Successfully");
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
    }

    public void getOrderDetailInfo() 
    {
        System.out.println("Order Detail Information:");
        
        System.out.println("ID: " + orderDetailID);
        System.out.println("CustomerID:"+order.getCustomer().getCustomerID());
        System.out.println("CustomerName:"+order.getCustomer().getFirstName()+""+order.getCustomer().getLastName());
        System.out.println("CustomerID:"+order.getCustomer().getEmail());
        System.out.println("CustomerID:"+order.getCustomer().getPhone());
        System.out.println("CustomerID:"+order.getCustomer().getAddress());
        System.out.println("OrderID:"+order.getOrderID());
        System.out.println("OrderDate:"+order.getOrderDate());
        System.out.println("OrderStatus:"+order.getStatus());
        System.out.println("OrderTotalAmount:"+order.getTotalAmount());
        System.out.println("Product: " + p.getProductID());
        System.out.println("ProductName"+ " - " + p.getProductName());
        System.out.println("ProductDescription"+p.getDescription());
       // System.out.println("Quantity: " + quantity);
        //calculateSubtotal();
    }

    public void updateQuantity(int newQuantity) throws SQLException
    {
        System.out.println("Updating quantity for order detail ID: " + orderDetailID);
       
       // System.out.println(ProductID);
       String query3="update OrderDetails set Quantity='"+newQuantity+"' WHERE OrderDetailID='"+orderDetailID+"'";
       c.s.executeUpdate(query3);
       System.out.println("updated successfully");
    }
    public void addDiscount(int totalamount,double discount,int OrderID) throws SQLException
    {
        System.out.println("Applying discount to order detail ID: " + orderDetailID);
       String query4="update Orders set TotalAmount='"+totalamount*(1-(discount/100))+"' Where orderID='"+OrderID+"'";
        c.s.executeUpdate(query4);
        System.out.println("OrderID" +OrderID+ "Updated");
        System.out.println("Discount applied successfully.");
    }
    public static void main(String[] args) throws SQLException
    {
    	ResultSet rs=null;
    	Scanner sc=new Scanner(System.in);
    	Conn c=new Conn();
    try
   	{
    //Conn c=new Conn();
    System.out.println("Connected successfully");
    
    System.out.println("Enter OrderDetailID");
    int OrderDetailID=sc.nextInt();
    //String query="select * from OrderDetails OD INNER JOIN Products P ON od.ProductID=p.ProductID INNER JOIN Orders o ON od.OrderID=o.OrderID where OrderDetailID='"+OrderDetailID+"'";
    String query="select * from OrderDetails OD INNER JOIN Products P ON od.ProductID=p.ProductID INNER JOIN Orders o ON od.OrderID=o.OrderID Inner join Customers c ON o.CustomerID=c.CustomerID where OrderDetailID='"+OrderDetailID+"'";

    rs=c.s.executeQuery(query);
   // System.out.println(rs.getInt("CustomerID"));
    if(rs.next())
    {
  	  OrderDetailID=rs.getInt("OrderDetailID");
  	  int OrderID=rs.getInt("OrderID");
  	  int ProductID=rs.getInt("ProductID");
  	  int StockQuantity=rs.getInt("Quantity");
  	 //Customers customer=new Customers(rs.getInt("customerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("email"),rs.getInt("phone"),rs.getString("address"))
    }
    else
    {
    	System.out.println("Invalid OrderDetailID");
    }
   	}
    
    /*Customers customer=new Customers(111,"Manoj","Mithra","mani22manoj@gmail.com",939817942,"2-90,ion di9gital,kakinada");

  	  Orders order=new Orders(rs.getInt("OrderID"),customer,rs.getString("OrderDate"),rs.getString("status"));
	    Products p= new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("price"));
    	OrderDetails Od=new OrderDetails(rs.getInt("OrderDetailID"),order,p,rs.getInt("Quantity"));
    }*/
   catch(SQLException e)
  	{
   		System.out.println(e.getMessage());
  	}
    Customers customer=new Customers(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("email"),rs.getInt("phone"),rs.getString("address"));

	  Orders order=new Orders(rs.getInt("OrderID"),customer,rs.getString("OrderDate"),rs.getInt("TotalAmount"),rs.getString("status"));
	    Products p= new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("price"));
  	OrderDetails Od=new OrderDetails(rs.getInt("OrderDetailID"),order,p,rs.getInt("Quantity"));
  	System.out.println("1.Get Order Details Information:");
  	System.out.println("2.Add Discount");
  	System.out.println("3.Update Quantity");
  	System.out.println("4.Calculate Subtotal");
    System.out.println("Enter your choice");
    int ch=sc.nextInt();
    switch(ch)
    {
    case 1:
    	Od.getOrderDetailInfo();
    	break;
    case 2:
    	int totalamount=rs.getInt("TotalAmount");
    	System.out.println("Enter discount");
    	double discount=sc.nextDouble();
    	int OrderID=rs.getInt("OrderID");
    	Od.addDiscount(totalamount,discount,OrderID);
    	break;
    case 3:
    	System.out.println("Enter new Quantity");
    	int newQuantity=sc.nextInt();
    	Od.updateQuantity(newQuantity);
    	break;
    case 4:
    	System.out.println("Calculate sub Total");
    	Od.calculateSubtotal();
    	default:
    		break;
    }
   	
}
}

