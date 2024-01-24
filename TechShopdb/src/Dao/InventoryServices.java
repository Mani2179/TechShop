package Dao;

public interface InventoryServices
{
	 public void addToInventory(int InventoryID,int quantity);
	 public void removeFromInventory(int InventoryID,int quantity);
	 public void updateStockQuantity(int InventoryID,int newQuantity);
	 public void isProductAvailable(int InventoryID,int quantityToCheck);
	 public void getInventoryValue(int InventoryID);
	  public void listLowStockProducts(int threshold);
	  public void listOutOfStockProducts() ;
	  public void listAllProducts() ;
}
