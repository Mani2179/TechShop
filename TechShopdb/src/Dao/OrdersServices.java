package Dao;
import Modal.*;
public interface OrdersServices {
	public void calculateTotalAmount(int OrderID);
	 public void newOrders(int OID,int CID,String ODate,int OPrice,String NStatus);
	 public void getOrderDetails(int OrderID);
	 public void updateOrderStatus(int OrderID) ;
	 public void SortedOrderDate();
	 public void cancelOrder(int OrderID);
	 public void getOrderStatus(int OrderID);
}
