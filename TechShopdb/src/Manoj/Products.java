package Manoj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Products 
{
    private int productID;
    private String productName;
    private String description;
    private int price;
   static Conn c=null;

    public Products(int productID, String productName, String description,int price)
    {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void getProductDetails()
    {
        System.out.println("Product Details:");
        System.out.println("ID: " + productID);
        System.out.println("Name: " + productName);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }

    public void updateProductInfo(int newPrice, String newDescription) throws SQLException
    {
        System.out.println("Updating product information...");
        this.price = newPrice;
        this.description = newDescription;
        System.out.println("Product information updated successfully.");
        String query1="UPDATE Products set price='"+newPrice+"' WHERE ProductID='"+productID+"'";
        String query2="UPDATE Products set Description='"+newDescription+"' WHERE ProductID='"+productID+"'";
        c.s.executeUpdate(query1);
        c.s.executeUpdate(query2);
    }

    public boolean isProductInStock(int ProductID,int pavailable) 
    {
    	if(pavailable>0)
    	{
    	System.out.println(pavailable+  "Quantity");
    	}
    	else
    	{
    		System.out.println("No stock available");
    	}
      
        return true;
    }
 



	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		List<Products> pro=new ArrayList<>();
		try
	      {
			  c=new Conn();
		      System.out.println("Connected successfully");
	          System.out.println("Enter ProductID");
	          int productID=sc.nextInt();
	          String query="select * from Products p INNER JOIN Inventory i ON p.ProductID=i.ProductID where p.ProductID='"+productID+"'";
	          ResultSet rs=c.s.executeQuery(query);
	         // System.out.println(rs.getInt("CustomerID"));
	          if(rs.next())
	          {
	        	  int ProductID=rs.getInt("ProductID");
	        	  String ProductName=(rs.getString("ProductName"));
	        	  String Description=(rs.getString("Description"));
	        	  int price=(rs.getInt("price"));
	        	 //Products p=new Products(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("Description"),rs.getInt("price"));
	          	  //p.getProductDetails();
	        	  pro.add(new Products(ProductID,ProductName,Description,price));
	        	  System.out.println("1.To display the product");
	        	  System.out.println("2.To update the products");
	        	  System.out.println("3.Product Availability");
	        	  System.out.println("4.Insert new product");
	        	  System.out.println("Enter your choice");
	        	  int ch=sc.nextInt();
	        	  switch(ch)
	        	  {
	        	  case 1:
	        	  
	                    //11pro.add(new Products(ProductID,ProductName,Description,price));
	                    for(Products p:pro)
	                    {
	            	        p.getProductDetails();
	                    }
	                    break;
	        	  case 2:
	        		  System.out.println("Update details");
	                 
	                  if (!pro.isEmpty()) {
	                      Products productToUpdate = pro.get(0);
	                      System.out.println("Enter new price:");
	                      int newPrice = sc.nextInt();
	                      System.out.println("Enter new Description");
	                      String newDescription = sc.next();
	                     productToUpdate.updateProductInfo(newPrice, newDescription);
	                    // System.out.println("cannot code");
	                     //String query1="UPDATE Products set price='"+newPrice+"' AND Description='"+newDescription+"' WHERE ProductID='"+rs.getInt(ProductID)+"'";
	                    //1 c.s.executeUpdate(query1);
	                    // productToUpdate.updateProductInfo(newPrice, newDescription);
	                  } 
	                  else 
	                  {
	                      System.out.println("No products in the list to update.");
	                  }
	                 for (Products p : pro) {
	                  p.getProductDetails();
	                      System.out.println("----------------------");
	                  }
	                  break;
	        	  case 3:
	        		  int pavailable=rs.getInt("QuantityStock");
	        		  for (Products p : pro) {
		                  p.isProductInStock(ProductID,pavailable);
		                  }
	        		  break;
	        	  case 4:
	        		  ProductName=rs.getString("ProductName");
	        		  System.out.println(ProductName);
	        		  System.out.println("Enter new ProductID:");
                      int PID= sc.nextInt();
                      System.out.println("Enter new Name");
                      String PName= sc.next();
                      System.out.println("Enter new Description");
                      String PDescription = sc.next();
                      System.out.println("Enter new Price:");
                      int PPrice = sc.nextInt();
                      String query5="Select ProductName from Products";
                      ResultSet rs1=c.s.executeQuery(query5);
	        		  Products i=new Products(PID,PName,PDescription,PPrice);
	        		  try
	        		  {
	        			  
	        		  if(ProductName!=PName)
	        		  {
	        				String query6="insert into Products values('"+PID+"','"+PName+"','"+PDescription+"','"+PPrice+"')";
	        			  c.s.executeUpdate(query6);
	        			  System.out.println("Inserted Succesfully");
	        		  }
	        		  else
	        		  {
	        			  System.out.println("Existing produvt");
	        		  }
	        		  }
	        		  catch(Exception e)
	        		  {
	        			 System.out.println(e.getMessage()); 
	        		  }
	        		  break;
	             default:
	                  
	            System.out.println("Invalid choice");
	          }
	          }
	          else
	          {
	              System.out.println("INVALID LOGIN CREDENTIALS");
	          }
	      
	      }
	      catch(SQLException e)
	      {
	          System.out.println(e.getMessage());
	      }
		
	
		//Products p= new Products(1101,"keyboard","input device",600); 
        //p.getProductDetails();   
	}

}
