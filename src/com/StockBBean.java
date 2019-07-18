package com;

import java.util.Date;

/**
 * Created by Hamza Nawaz on 6/16/2019.
 */
public class StockBBean {
    public String nameofproduct;
    public double quantity;

    public double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getNameofproduct() {
        return nameofproduct;
    }

    public void setNameofproduct(String nameofproduct) {
        this.nameofproduct = nameofproduct;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDateofpurchase() {
        return dateofpurchase;
    }

    public void setDateofpurchase(String dateofpurchase) {
        this.dateofpurchase = dateofpurchase;
    }

    public double purchaseprice;
    public String dateofpurchase;

}
