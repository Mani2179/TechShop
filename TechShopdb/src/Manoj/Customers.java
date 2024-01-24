package Manoj;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Exceptions.*;
import Manoj.Conn;

import java.util.List;
import java.util.Scanner;
class Customers{
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String address;
   static // private List<Order> orders;
   Conn c;
    public Customers(int customerID, String firstName, String lastName, String email,int phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        //this.orders = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void calculateTotalOrders() throws SQLException {
        System.out.println("Calculating total orders for customer " + firstName + " " + lastName);
    
        String q="select count(*) AS TotalOrders from Orders where CustomerID='"+customerID+"'";
        ResultSet count=c.s.executeQuery(q);
        if(count.next())
        {
        	int Total=count.getInt("TotalOrders");
        	System.out.println(Total);
        }
        else
        {
        	System.out.println("NO ORDERS PLACED");
        }
        
    }

    public void getCustomerDetails() {
        System.out.println("Customer Details:");
        System.out.println("ID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    
    }
   
    public void updateCustomerInfo(String newEmail,int newPhone, String newAddress) throws SQLException 
    {
    
        System.out.println("Updating customer information...");
        this.email = newEmail;
        this.phone = newPhone;
        this.address = newAddress;
        String query1="UPDATE Customers set email='"+newEmail+"' WHERE customerID='"+customerID+"'";
        String query2="UPDATE Customers set phone='"+newPhone+"' WHERE customerID='"+customerID+"'";
        String query3="UPDATE Customers set address='"+newAddress+"' WHERE customerID='"+customerID+"'";
        c.s.executeUpdate(query1);
        c.s.executeUpdate(query2);
        c.s.executeUpdate(query3);

        //String query2="UPDATE Products set Description='"+newDescription+"' WHERE ProductID='"+productID+"'";
        System.out.println("Customer information updated successfully.");
       // throw new InvalidDataException("Already existing email");
       
    }
    public static void main(String[] args) throws java.sql.SQLException
    {
    	Scanner sc=new Scanner(System.in);
	List<Customers> cust=new ArrayList<>();
	try
      {
		  c=new Conn();
	      System.out.println("Connected successfully");
          System.out.println("Enter CustomerID");
          int CustomerID=sc.nextInt();
          String query="select * from Customers where CustomerID='"+CustomerID+"'";
          ResultSet rs=c.s.executeQuery(query);
          if(rs.next())
          {
        	  int customerID=rs.getInt("CustomerID");
        	  String CustomerName=rs.getString("FirstName")+" "+rs.getString("LastName");
        	  String email=rs.getString("email");
        	  int phone=rs.getInt("phone");
        	  String address=rs.getString("address");
        	  cust.add(new Customers(customerID,rs.getString("FirstName"),rs.getString("LastName"),email,phone,address));
        	  System.out.println("1.To display the product");
        	  System.out.println("2.To update the products");
        	  System.out.println("3.Caluculate Total Orders");
        	  System.out.println("4.Enter new Customer");
        	  System.out.println("5.Delete the Customer");
        	  System.out.println("Enter your choice");
        	  int ch=sc.nextInt();
        	  switch(ch)
        	  {
        	  case 1:
        	         for(Customers co:cust)
                     {
      	                co.getCustomerDetails();
                     }
        	         break;
        	  case 2:
        		  System.out.println("Update details");
                 
                  if (!cust.isEmpty()) {
                      Customers productToUpdate = cust.get(0);
                    // int customerID=rs.getInt(CustomerID);
                      System.out.println("Enter new Email:");
                      String newEmail = sc.next();
                      System.out.println("Enter new Phone:");
                      int newPhone = sc.nextInt();
                      System.out.println("Enter new Address:");
                      String newaddress = sc.next();
                     productToUpdate.updateCustomerInfo(newEmail, newPhone,newaddress);
                     for(Customers co:cust)
                     {
      	                co.getCustomerDetails();
                     }
                  }
                 else
                 {
        	  System.out.println("Invalid Customer");
                 }
                  break;
        	  case 3:
        		  Customers totalorders=cust.get(0);
        		  totalorders.calculateTotalOrders();
        		  break;
        	  case 4:
        		  int CID=sc.nextInt();
        		  String FName=sc.next();
        		  String LName=sc.next();
        		  String Email=sc.next();
        		  int Phone=sc.nextInt();
        		  String Address=sc.next();
        		  String q="Insert Into Customers values('"+CID+"','"+FName+"','"+LName+"','"+Email+"','"+Phone+"','"+Address+"')";
        		  c.s.executeUpdate(q);
        		  System.out.println("Customer Registered successfully");
        		  break;
        	  case 5:
        		  String q1="delete from Customers where CustomerID='"+rs.getInt("CustomerID")+"'";
        		  c.s.executeUpdate(q1);
        		  System.out.println("Deleted Successsfully");
        		  
               default:
                	  System.out.println("invalid choice");
             }
          }
          else
          {
        	  System.out.println("Invalid Customer");
          }
      }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		}
}
}


    