package Modal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDetails{
    private int orderDetailID;
    private Orders order;
    private Products p;
    private int quantity;
    int ProductID=0;
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
}

	