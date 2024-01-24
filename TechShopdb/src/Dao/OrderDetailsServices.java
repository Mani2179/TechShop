package Dao;

public interface OrderDetailsServices 
{
	public void calculateSubtotal(int OrderDetailID) ;
	 public void getOrderDetailInfo(int OrderDetailID);
	 public void updateQuantity(int OrderDetailID,int newQuantity);
	 public void addDiscount(double discount,int OrderID);
}
