package Manoj;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
class Inventory {
    private int inventoryID;
    private Products product;
    private int quantityInStock;
    private String lastStockUpdate;
    int ProductID=0;
    public Inventory(int inventoryID, Products product, int quantityInStock, String lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getLastStockUpdate() {
        return lastStockUpdate;
    }

    public void setLastStockUpdate(String lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    public void addToInventory(int InventoryID,int quantity) throws SQLException
    { 
    	Conn c=new Conn();
        System.out.println("Adding to inventory...");
      
        String query1="Update Inventory set QuantityStock=(QuantityStock+"+quantity+") where InventoryID='"+InventoryID+"'";
        c.s.executeUpdate(query1);
        System.out.println(quantity + " units added to inventory.");
    }
        public void removeFromInventory(int InventoryID,int Stock,int quantity) throws SQLException {
        	Conn c=new Conn();
        System.out.println("Removing from inventory...");
    
        if (Stock >= quantity) {
            String query3="update Inventory set QuantityStock=(QuantityStock-"+quantity+") where InventoryID='"+InventoryID+"'";
            c.s.executeUpdate(query3);
            System.out.println(quantity + " units removed from inventory.");
        } else {
            System.out.println("Insufficient quantity in stock.");
        }
    }

    public void updateStockQuantity(int InventoryID,int newQuantity) throws SQLException {
        System.out.println("Updating stock quantity...");
        Conn c=new Conn();
     
        String query5="Update Inventory set QuantityStock='"+newQuantity+"' Where InventoryID='"+InventoryID+"' ";
        c.s.executeUpdate(query5);
        this.quantityInStock = newQuantity;
        System.out.println("Stock quantity updated to: " + newQuantity);
    }

    public void isProductAvailable(int InventoryID,int quantityToCheck) throws SQLException {
    	Conn c=new Conn();
        System.out.println("Checking product availability...");
    
        String query4="select * from Inventory where InventoryID='"+	InventoryID+"'";
        ResultSet rs1=c.s.executeQuery(query4);
        if(rs1.next())
        {
        	int quantityInStock = rs1.getInt("QuantityStock");
        if(quantityInStock>=quantityToCheck)
        {
        		System.out.println(quantityInStock+ " Available");
        }
        else
        {
        	System.out.println("Unavailable");
        }
        }
        else
        {
        	System.out.println("Invalid record from the inventory table");
        }
        //return quantityInStock >= quantityToCheck;
    }

    public void getInventoryValue(int quantityInstock,int price) {
        System.out.println("Calculating inventory value...");
       
       int total=quantityInStock*price;
	System.out.println(total);
       //return total;
    }

    public void listLowStockProducts(int threshold) {
    	Conn c=new Conn();
        System.out.println("Listing low stock products...");
        
        try
        {
        String q1="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID";
        ResultSet rs2=c.s.executeQuery(q1);
        //int quantityInStock=rs2.getInt("QuantityStock");
        while(rs2.next())
        {
            int quantityInStock=rs2.getInt("QuantityStock");
        	List<String>ProductName=new ArrayList<>();
        
        if (quantityInStock <=threshold) 
        {
            ProductName.add(rs2.getString("ProductName"));
        }
            for (String name :ProductName) 
            {
               System.out.println(name+" "+rs2.getInt("ProductID"));
               //System.out.println(rs2.getInt("ProductID"));
             }
        }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        //return lowStockProducts;
    }

    public void listOutOfStockProducts() throws SQLException {
    	Conn c=new Conn();
        System.out.println("Listing out-of-stock products...");
     
        
        try
        {
        String q="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID";
        ResultSet rs2=c.s.executeQuery(q);
        //int quantityInStock=rs2.getInt("QuantityStock");
        while(rs2.next())
        {
            int quantityInStock=rs2.getInt("QuantityStock");
        	List<String>ProductName=new ArrayList<>();
        
        if (quantityInStock == 0) 
        {
            ProductName.add(rs2.getString("ProductName"));
        }
            for (String name :ProductName) 
            {
               System.out.println(name+" "+rs2.getInt("ProductID"));
               //System.out.println(rs2.getInt("ProductID"));
             }
        }
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
       // return outOfStockProducts;
    }
    
    

    /*public void listAllProducts() {
        System.out.println("Listing all products in the inventory...");
        Conn c=new Conn();
        // Logic to list all products in the inventory along with their quantities
        String query1="select * from inventory";
        try
        {
        	 ResultSet rs1=c.s.execute(query1);
             while(rs1.next())
             {
             	
             }
        }
        catch(SQLException e)
        List<Products> allProducts = new ArrayList<>();
        allProducts.add(product);
        System.out.println("Product " + product.getProductID() + " - " + product.getProductName() +
                " Quantity: " + quantityInStock);
    }*/
    public void listAllProducts() throws SQLException
    { 
    	Conn c=new Conn();
    	System.out.println("Listing all products");
    	System.out.println(ProductID);
    	String query1="select * from products p INNER JOIN Inventory i on p.productid=i.productid";
    	try 
    	(
    			ResultSet rs1= c.s.executeQuery(query1)) {
            List<String> addProducts= new ArrayList<>();

            while (rs1.next()) {
                addProducts.add(rs1.getString("ProductName"));
            }
            // Now, you can print or process the sorted order dates
            for (String name : addProducts) {
                System.out.println(name);
            }
        }
    }
    public static void main(String[] args) throws SQLException
    { 
    	Scanner sc=new Scanner(System.in);
    	List<Inventory>i=new ArrayList<>();
    	
    	int price=0;
    	int quantityInStock=0;
    	try
    	{
    	Conn c=new Conn();
    	System.out.println("connected to database");
    	System.out.println("Enter InventoryID");
    	int InventoryID=sc.nextInt();
    	String query="select * from inventory i INNER JOIN Products p ON i.ProductID=p.ProductID WHERE i.InventoryID='"+InventoryID+"'";
    	ResultSet rs=c.s.executeQuery(query);
    	if(rs.next())
    	{
    		InventoryID=rs.getInt("InventoryID");
    		int ProductID=rs.getInt("ProductID");
    		quantityInStock=rs.getInt("QuantityStock");
    		String LastStock=rs.getString("LastStockUpdate");
    		price=rs.getInt("price");
        	Products  p=new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("Price"));
    		i.add(new Inventory(rs.getInt("InventoryID"),p,rs.getInt("QuantityStock"),rs.getString("LastStockUpdate")));	
    	}
    	else
    	{
    		System.out.println("Invalid Inventory ID");
    	}
    	}
    	catch(SQLException e)
    	{
    		System.out.println("e.getMessage()");
    	}
    	//Products  p=new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("Price"));
		//i.add(new Inventory(rs.getInt("InventoryID"),p,rs.getInt("QuantityStock"),rs.getString("LastStockUpdate")));
    	System.out.println("1.GET INVENTORY VALUE");
    	System.out.println("2.List all Products");
    	System.out.println("3.ADD TO INVENTORY");
    	System.out.println("4.REMOVE FROM INVENTORY");
    	System.out.println("5.Product Availability");
    	System.out.println("6.Update Stock Availability");
    	System.out.println("7.Listing OUT OF STOCK Products");
    	System.out.println("8.Listing Low Stock Products");
    	System.out.println("ENTER YOUR CHOICE:");
    	int ch=sc.nextInt();
    	switch(ch)
    	{
    	case 1:
    	        for(Inventory inventory:i)
    	        {
    		        inventory.getInventoryValue(inventory.getQuantityInStock(), price);
    	        }
    	        break;
    	case 2:
    		 for (Inventory inventory : i) 
    			 
    		 {
                 inventory.listAllProducts();
             }
             
             break;
    	case 3:
    		System.out.println("Update quantity in stock");
    		int quantity=sc.nextInt();
    		//int InventoryID=inventory.getInventoryID();
    		for (Inventory inventory : i) 
   		   {
        		int InventoryID=inventory.getInventoryID();
        		//int QuantityInStock=inventory.getQuantityInStock();

                inventory.addToInventory(InventoryID,quantity);
            }
    		break;
    	
    	case 4:
    		System.out.println("Remove quantity in stock");
    		quantity=sc.nextInt();
    		for (Inventory inventory : i) 
    		   {
         		int InventoryID=inventory.getInventoryID();
         		int Stock=inventory.getQuantityInStock();

                 inventory.removeFromInventory(InventoryID,Stock,quantity);
             }
     		break;
    	case 5:
    		System.out.println("Enter requirement");
    		int quantityToCheck=sc.nextInt();
    		for (Inventory inventory : i) 
 		   {
      		int InventoryID=inventory.getInventoryID();

              inventory.isProductAvailable(InventoryID,quantityToCheck);
          }
    		break;
    	case 6:
    		System.out.println("Enter quantity to update");
    		int newQuantity=sc.nextInt();
    		for (Inventory inventory : i) 
  		   {
       		int InventoryID=inventory.getInventoryID();

               inventory.updateStockQuantity(InventoryID,newQuantity);
           }
     		break;
    	case 7:
    		System.out.println("Products in low stock");
    		for (Inventory inventory : i) 
   		   {
        		int InventoryID=inventory.getInventoryID();

                inventory.listOutOfStockProducts();
            }
    		break;
    	case 8:
    		System.out.println("Listing low stock Products");
    		System.out.println("Enter the threshold");
    		int threshold=sc.nextInt();
    		for (Inventory inventory : i) 
    		   {
         		int InventoryID=inventory.getInventoryID();

                 inventory.listLowStockProducts(threshold);
             }
    		break;
    		default:
    			System.out.println("Invalid choice");
    			break;
    		
    		
    	}
    }
}
