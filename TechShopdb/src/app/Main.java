package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dao.*;
import Modal.*;

public class Main {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		//Scanner sc=new Scanner(System.in);
     System.out.println("1.Customers");
   	  System.out.println("2.Products");
   	  System.out.println("3.Orders");
   	  System.out.println("4.OrderDetails");
   	  System.out.println("5.Inventory");
   	  System.out.println("6.Exit");
   	  System.out.println("Enter your choice");
   	  int ch=sc.nextInt();
   	  switch(ch)
   	  {
   	  case 1:
   		  customerManagement();
   		  break;
   	  case 2:
   		productsManagement();
   		break;
   	  case 3:
   		  ordersManagement();
   		  break;
   	  case 4:
   		  ordersDetailsManagement();
   		  break;
   	  case 5:
   		  inventoryManagement();
   		  break;
   	  case 6:
   		  System.out.println("Exiting....");
   		  break;
   		  default :
   			  System.out.println("Invalid choice");
   			  break;
   		  
   		  
   	  }

	}
	public static void customerManagement()
	{
		
		CustomerServicesImpl csi=new CustomerServicesImpl();
		System.out.println("Enter CustomerID");
		int customerID=sc.nextInt();
	    //Customers customer=csi.getCustomerDetails(customerID);
	 System.out.println("1.To display the customer");
   	  System.out.println("2.To update the customers");
   	  System.out.println("3.Caluculate Total Orders");
   	  System.out.println("4.Enter new Customer");
   	  System.out.println("5.Delete the Customer");
   	  System.out.println("6.Exit");
   	  System.out.println("Enter your choice");
   	  int ch=sc.nextInt();
   	  
   	  switch(ch)
   	  {
   	  case 1:
   		    Customers customer=csi.getCustomerDetails(customerID);
   		     System.out.println(customer.getCustomerID());
   		     System.out.println(customer.getFirstName());
   		     System.out.println(customer.getLastName());
   		     System.out.println(customer.getEmail());
   		     System.out.println(customer.getPhone());
   		     System.out.println(customer.getAddress());
   	         break;
   	  case 2:
   		  System.out.println("Update details");
   		  System.out.println("Enter new Email:");
   		  String newEmail=sc.next();
   		  System.out.println("Enter new Phone Number");
   		  int newPhone=sc.nextInt();
   		  System.out.println("Enter new Address");
   		  String newAddres=sc.next();
   		  csi.updateCustomerInfo(customerID,newEmail, newPhone, newAddres);
          break;
   	  case 3:
   		  System.out.println("Total orders");
   		  csi.calculateTotalOrders(customerID);
   		  break;
   	  case 4:
   		  int CID=sc.nextInt();
   		  String FName=sc.next();
   		  String LName=sc.next();
   		  String Email=sc.next();
   		  int Phone=sc.nextInt();
   		  String Address=sc.next();
   		  customer=new Customers();
   		  customer.setCustomerID(CID);
   		  customer.setFirstName(FName);
   		  customer.setLastName(LName);
   		  customer.setEmail(Email);
   		  customer.setPhone(Phone);
   		  customer.setAddress(Address);
   		  csi.createCustomer(customer);
   		  System.out.println("Customer Registered successfully");
   		  break;
   	  case 5:
   	       csi.deleteCustomer(customerID);
   	       break;
   	  case 6:
   		  System.out.println("Exiting....");
   		  break;
          default:
           	  System.out.println("invalid choice");
        }
	
}

      public static void productsManagement()
      {
    	  ProductsServicesImpl psi=new ProductsServicesImpl();
    	  Scanner sc=new Scanner(System.in);
    	  System.out.println("Enter ProductID");
    	  int ProductID=sc.nextInt();
		  //Products product=psi.getProductDetails(ProductID);
    	  System.out.println("1.To display the product");
    	  System.out.println("2.To update the products");
    	  System.out.println("3.Product Availability");
    	  System.out.println("4.Insert new product");
    	  System.out.println("5.delete the product");
    	  System.out.println("6.Exit");
    	  System.out.println("Enter your choice");
    	  int ch=sc.nextInt();
    	  switch(ch)
    	  {
    	  case 1:
    		     Products product=psi.getProductDetails(ProductID);
    		     System.out.println(product.getProductID());
    		     System.out.println(product.getProductName());
    		     System.out.println(product.getDescription());
    		     System.out.println(product.getPrice());
                
                break;
    	  case 2:
    		  System.out.println("Update details");
                  System.out.println("Enter new price:");
                  int newPrice = sc.nextInt();
                  System.out.println("Enter new Description");
                  String newDescription = sc.next();
                 psi.updateProductInfo(ProductID,newPrice, newDescription);
                // System.out.println("cannot code");
                 //String query1="UPDATE Products set price='"+newPrice+"' AND Description='"+newDescription+"' WHERE ProductID='"+rs.getInt(ProductID)+"'";
                //1 c.s.executeUpdate(query1);
                // productToUpdate.updateProductInfo(newPrice, newDescription);
            
              break;
    	  case 3:
    		  System.out.println("checking the availability");
    		  psi.isProductInStock(ProductID);
    		  break;
    	  case 4:
    		  System.out.println("Enter new Product");
    		  System.out.println("Enter  ProductID");
    		  int NProductID=sc.nextInt();
    		  System.out.println("Enter ProductName");
       		  String NProductName=sc.next();
       		  System.out.println("Enter  Description");
       		  String NDescription=sc.next();
       		  System.out.println("Enter price");
       		  int NPrice=sc.nextInt();
       		  product=new Products();
       		  product.setProductID(NProductID);
       		  product.setProductName(NProductName);
       		  product.setDescription(NDescription);
       		  product.setPrice(NPrice);
       		  psi.createProduct(product);
    		  break;
    	  case 5:
    		  psi.deleteProduct(ProductID);
    		  break;
    	  case 6:
    		  System.out.println("Exiting....");
    		  break;
         default:
        System.out.println("Invalid choice");
        break;
      }

	}


public static void ordersManagement()
{
	Scanner sc=new Scanner(System.in);
	OrdersServicesImpl osi=new OrdersServicesImpl();
	System.out.println("Enter OrderID");
	int OrderID=sc.nextInt();
	System.out.println("1.Display Order Details");
	System.out.println("2.Update Order Status");
	System.out.println("3.cancel order");
	System.out.println("4.Total Amount for Orders");
	System.out.println("5.Place Order");
	System.out.println("6.Sort the orders");
	System.out.println("7.get Order Status");
	System.out.println("8.exit");
	int ch=sc.nextInt();
	switch(ch)
	{
	case 1:
	    osi.getOrderDetails(OrderID);
		System.out.println("Order Details");
		break;
	case 2:
		System.out.println("Update Order Status");
		osi.updateOrderStatus(OrderID);
		//o.getOrderDetails(o, cust);
		break;
	case 3:
		System.out.println("Cancelling Order");
		//int Orderid=rs.getInt("OrderID");
		osi.cancelOrder(OrderID);
		break;
	case 4:
		System.out.println("Total amount");
		//int orderID=rs.getInt("OrderID");
		osi.calculateTotalAmount(OrderID);
		break;
	case 5:
		System.out.println("New Order");
		System.out.println("ENTER ORDERID:");
		int OID=sc.nextInt();
		System.out.println("ENTER CustomerID:");
		int CID=sc.nextInt();
		System.out.println("Enter OrderDate:");
		String ODate=sc.next();
		System.out.println("Enter OrderPrice:");
		int OPrice=sc.nextInt();
		System.out.println("Enter OrderStatus:");
		String NStatus=sc.next();
		osi.newOrders(OID,CID,ODate,OPrice,NStatus);
		break;
	case 6:
		System.out.println("Sorting the Order dates  in ascending order");
		osi.SortedOrderDate();
		break;
	case 7:
		
		//String Ostatus=rs.getString("status");
		System.out.println("");
		osi.getOrderStatus(OrderID);
		break;
	case 8:
		System.out.println("Exiting....");
		break;
		default:
			System.out.println("Invalid choice");	
			break;
	}
}

public static void ordersDetailsManagement()
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter OrderDetailID");
	int OrderDetailID=sc.nextInt();
	OrdersDetailsServicesImpl odsi=new OrdersDetailsServicesImpl();
	System.out.println("1.Get Order Details Information:");
  	System.out.println("2.Add Discount");
  	System.out.println("3.Update Quantity");
  	System.out.println("4.Calculate Subtotal");
  	System.out.println("5.Exit");
    System.out.println("Enter your choice");
    int ch=sc.nextInt();
    switch(ch)
    {
    case 1:
    	System.out.println("Enter orderDetailID");
    	//int OrderDetailID=sc.nextInt();
    	odsi.getOrderDetailInfo(OrderDetailID);
    	break;
    case 2:
    	System.out.println("Adding discount");
    	System.out.println("Enter discount");
    	double discount=sc.nextDouble();
    	odsi.addDiscount(discount,OrderDetailID);
    	break;
    case 3:
    	System.out.println("Enter new Quantity");
    	int newQuantity=sc.nextInt();
    	odsi.updateQuantity(OrderDetailID,newQuantity);
    	break;
    case 4:
    	System.out.println("Calculating sub Total");
    	odsi.calculateSubtotal(OrderDetailID);
    case 5:
    	System.out.println("Exiting...");
    	break;
    	default:
    		System.out.println("Invalid Choice");
    		break;
    }
}
public static void inventoryManagement()
{
	System.out.println("Enter InventoryID");
	int InventoryID=sc.nextInt();
	InventoryImpl ipl=new InventoryImpl();
	Inventory inventory;
	System.out.println("1.GET INVENTORY VALUE");
	System.out.println("2.List all Products");
	System.out.println("3.ADD TO INVENTORY");
	System.out.println("4.REMOVE FROM INVENTORY");
	System.out.println("5.Product Availability");
	System.out.println("6.Update Stock Availability");
	System.out.println("7.Listing LOW STOCK Products");
	System.out.println("8.Listing Out Of Stock Products");
	System.out.println("9.Exit");
	System.out.println("ENTER YOUR CHOICE:");
	int ch=sc.nextInt();
	switch(ch)
	{
	case 1:
		System.out.println("Enter inventory value");
		ipl.getInventoryValue(InventoryID);
	        break;
	case 2:
		 
         System.out.println("Listing all Products");
         ipl.listAllProducts();
         break;
	case 3:
		System.out.println("ADD quantity to stock");
		int quantity=sc.nextInt();
		ipl.addToInventory(InventoryID, quantity);
		break;
	
	case 4:
		System.out.println("Remove quantity in stock");
		quantity=sc.nextInt();
		ipl.removeFromInventory(InventoryID,quantity);
		
 		break;
	case 5:
		System.out.println("Enter requirement");
		int quantityToCheck=sc.nextInt();
		ipl.isProductAvailable(InventoryID, quantityToCheck);
		
		break;
	case 6:
		System.out.println("Enter quantity to update");
		int newQuantity=sc.nextInt();
		ipl.updateStockQuantity(InventoryID, newQuantity);
 		break;
	case 7:
		System.out.println("Products in low stock");
		System.out.println("Enter threshold");
		int threshold=sc.nextInt();
		ipl.listLowStockProducts(threshold);
		break;
	case 8:
		System.out.println("Listing low stock Products");
		ipl.listOutOfStockProducts();
		break;
	case 9:
		System.out.println("Exiting....");
		break;
		default:
			System.out.println("Invalid choice");
			break;
		
		
	}
}
}
