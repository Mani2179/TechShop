package Modal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Modal.Customers;
import Modal.OrderDetails;
public class Orders{
    private int orderID;
    private Customers customer;
    private String orderDate;
    private int totalAmount;
    private String status;
    private List<OrderDetails> orderDetails;
List<Orders>o=new ArrayList<>();
        public Orders(int orderID, Customers customer,String orderDate,int totalAmount,String status) {
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
}
	 
	