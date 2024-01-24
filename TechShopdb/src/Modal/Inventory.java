package Modal;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Inventory {
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
}
