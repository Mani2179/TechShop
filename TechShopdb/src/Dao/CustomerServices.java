package Dao;

import java.sql.SQLException;

import Modal.Customers;

public interface CustomerServices {
	public void calculateTotalOrders(int customerID);
	 public Customers getCustomerDetails(int customerID) ;
	 public void updateCustomerInfo(int customerID,String newEmail,int newPhone, String newAddress);
	 public void createCustomer(Customers customer);

}
