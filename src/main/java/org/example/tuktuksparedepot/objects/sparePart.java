package org.example.tuktuksparedepot.objects;

public class sparePart {
    private String partCode;
    private String partName;
    private String brand;
    private double price;
    private int quantity;
    private String category;
    private String date;
    private String img;

    public sparePart(String partCode, String partName, String brand, double price, int quantity, String category, String date, String img) {
        setPartCode(partCode);
        setPartName(partName);
        setBrand(brand);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
        setDate(date);
        setImg(img);
    }


    public String getPartCode() {
        return this.partCode;
    }

    public void setPartCode(String partCode) {
        if (partCode==null||partCode.trim().isEmpty()){
            System.out.println("Part code cannot be empty");
        }
        else if (partCode.trim().length() != 4 || partCode.trim().toUpperCase().charAt(0) != 'P') {
            System.out.println("Invalid Part Code.Follow this pattern (Pxxx)");
        }
        else {
            this.partCode = partCode.trim().toUpperCase();
        }
    }

    public String getPartName() {
        return this.partName;
    }

    public void setPartName(String partName) {
        if (partName == null || partName.trim().isEmpty()) {
            System.out.println("Part Name cannot be empty");
        }
        else {
            this.partName = partName.trim();
        }

    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            this.brand = "No brand";
        } else {
            this.brand = brand.trim();
        }
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid Price.");
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid Quantity");
            this.quantity = 0;
        }
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            this.category = "No category";
        } else {
            this.category = category;
        }
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            this.date = "No date";
        } else {
            this.date = date;
        }
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        if (img == null || img.trim().isEmpty()) {
            this.img = "No image";
        }
        else {
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return partCode+"|"+partName+"|"+brand+"|"+price+"|"+quantity+"|"+category+"|"+date+"|"+img;
    }
}
