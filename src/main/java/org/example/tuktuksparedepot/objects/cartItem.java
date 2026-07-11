package org.example.tuktuksparedepot.objects;

public class cartItem {

    private sparePart part;
    private int quantity;

    public cartItem(sparePart part, int quantity) {
        setPart(part);
        if (part!=null) {
            setCartQuantity(quantity);
        }
    }

    public sparePart getPart(){
        return part;
    }
    public void setPart(sparePart part){
        if(part == null){
            System.out.println("The part cannot be empty");
            return;
        }
        this.part=part;
    }

    public int getCartQuantity(){
        return quantity;
    }
    public void setCartQuantity(int quantity){
        if(quantity<=0 || quantity> part.getQuantity()){
            this.quantity=1;
            System.out.println("Invalid quantity");
            return;
        }
        else{
            this.quantity=quantity;
        }
    }

    public double getSubTotal(){
        if (part==null || quantity<=0){
            return 0;
        }
        double subTotal=part.getPrice()*quantity;
        return subTotal;
    }


}
