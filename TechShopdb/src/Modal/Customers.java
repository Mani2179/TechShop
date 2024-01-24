package Modal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Exceptions.*;

import java.util.List;
import java.util.Scanner;
public class Customers{
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String address;
    // private List<Order> orders;
    public Customers()
    {
    	
    }
   public Customers(int customerID, String firstName, String lastName, String email,int phone, String address) 
   {
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

	public void setAddress(String address)
	{
		this.address = address;
	}

	       
    }
