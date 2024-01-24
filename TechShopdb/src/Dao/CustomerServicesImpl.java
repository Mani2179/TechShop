package Dao;
import Modal.Customers;
import util.Conn;
import java.sql.*;

import Exceptions.AuthenticationException;
import Exceptions.IOException;
import Exceptions.IncompleteOrderException;

public class CustomerServicesImpl implements CustomerServices
{
	Statement s;
	@Override
	public void calculateTotalOrders(int customerID) {
		Conn c=new Conn();
		// TODO Auto-generated method stub
		System.out.println("Calculating total orders for customer ");
	    
        String q="select count(*) AS TotalOrders from Orders where CustomerID='"+customerID+"'";
        try
        {
        ResultSet count=c.s.executeQuery(q);
        if(count.next())
        {
        	int Total=count.getInt("TotalOrders");
        	System.out.println(Total);
        }
        else
        {
            throw new IncompleteOrderException("No customer found");

        }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}

	@Override
	public Customers getCustomerDetails(int customerID) {
        Customers co = new Customers();
        Conn c = new Conn();
        String query = "select * from Customers where CustomerID='" + customerID + "'";
        try {
            ResultSet rs = c.s.executeQuery(query);
            // TODO: Populate the 'co' object with customer details
            if (rs.next()) {
                co.setCustomerID(rs.getInt("CustomerID"));
                co.setFirstName(rs.getString("FirstName"));
                co.setLastName(rs.getString("LastName"));
                co.setEmail(rs.getString("email"));
                co.setPhone(rs.getInt("Phone"));
                co.setAddress(rs.getString("address"));
            }
            else
            {
               System.out.println("No customer found");

            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return co;
    }

	@Override
	public void updateCustomerInfo(int customerID,String newEmail, int newPhone, String newAddress) {
		// TODO Auto-generated method stub
		Conn c=new Conn();
		String query1="UPDATE Customers set email='"+newEmail+"' WHERE customerID='"+customerID+"'";
        String query2="UPDATE Customers set phone='"+newPhone+"' WHERE customerID='"+customerID+"'";
        String query3="UPDATE Customers set address='"+newAddress+"' WHERE customerID='"+customerID+"'";
        try
        {
        c.s.executeUpdate(query1);
        c.s.executeUpdate(query2);
        c.s.executeUpdate(query3);
        System.out.println("Updation Success");
        	
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}

	@Override
	public void createCustomer(Customers customer)
	{
		// TODO Auto-generated method stub
		Conn c=new Conn();
		int id=customer.getCustomerID();
		String fname=customer.getFirstName();
		String lname=customer.getLastName();
		String email=customer.getEmail();
		int phone=customer.getPhone();
		String address=customer.getAddress();
		
		String query="insert into Customers values('"+id+"','"+fname+"','"+lname+"','"+email+"','"+phone+"','"+address+"')";
		try {
			c.s.executeUpdate(query);
			System.out.println("customer created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteCustomer(int customerID)
	{
		Conn c=new Conn();
		String query="DELETE from Customers where CustomerID='"+customerID+"'";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("deleted successfully");
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	



}
