package Dao;

import Modal.*;

public interface ProductsServices {
	public Products getProductDetails(int ProductID);
	public void updateProductInfo(int ProductID,int newPrice, String newDescription);
	 public boolean isProductInStock(int ProductID) ;
	 public void createProduct(Products product);
}
