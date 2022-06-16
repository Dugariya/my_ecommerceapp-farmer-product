package com.example.myecommerceapp.models;

public class MyCartModel {
    String CurrentTime;
    String CurrentDate;
    String ProductName;
    String ProductPrice;
    String TotalQuantity;
    int totalPrice;
    String Id;

    public MyCartModel() {
    }

    public MyCartModel(String currentTime, String currentDate, String productName, String productPrice, String totalQuantity, int totalPrice,String id) {
        CurrentTime = currentTime;
        CurrentDate = currentDate;
        ProductName = productName;
        ProductPrice = productPrice;
        TotalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        Id = id;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }
    public String getItemId() {
        return Id;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
