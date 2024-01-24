package Modal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Products 
{
    private int productID;
    private String productName;
    private String description;
    private int price;

   public Products(int productID, String productName, String description,int price)
    {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
    public Products() {
		// TODO Auto-generated constructor stub
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
}

	