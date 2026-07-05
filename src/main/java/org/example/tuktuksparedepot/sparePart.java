package org.example.tuktuksparedepot;

public class sparePart {
    String partCode;
    String partName;
    String brand;
    double price;
    int quantity;
    String category;
    String date;
    String img;

    public sparePart(String partCode,String partName,String brand,double price,int quantity,String category,String date,String img) {
        this.partCode = partCode;
        this.partName = partName;
        this.brand = brand;
        this.price = price;
        this.quantity=quantity;
        this.category=category;
        this.date=date;
        this.img=img;
    }
}
