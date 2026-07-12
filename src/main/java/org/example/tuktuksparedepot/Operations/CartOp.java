package org.example.tuktuksparedepot.Operations;

import org.example.tuktuksparedepot.fileWriting.FileWrite;
import org.example.tuktuksparedepot.objects.cartItem;
import org.example.tuktuksparedepot.objects.sparePart;
import org.example.tuktuksparedepot.Operations.InventoryOp;

import java.io.File;
import java.util.ArrayList;

public class CartOp {

    ArrayList<cartItem> items=new ArrayList<cartItem>();
    FileWrite fw=new FileWrite();
    InventoryOp inventoryOp=new InventoryOp();

    public void addToCart(sparePart part,int quantity){

        if(part==null){
            System.out.println("Invalid part");
            return;
        }
        if(quantity<=0){
            System.out.println("Invalid quantity");
            return;
        }
        if(quantity>part.getQuantity()){
            System.out.println("Only "+part.getQuantity()+" is in stock");
            return;
        }
        for(int i=0;i<items.size();i++) {
            if (part.getPartCode().equals(items.get(i).getPart().getPartCode())) {
                System.out.println("Part is already in the cart");
                return;
            }
        }
        cartItem item=new cartItem(part,quantity);
        items.add(item);
        System.out.println("Part added to cart");
    }

    public void deleteFromCart(String partCode){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getPart().getPartCode().equals(partCode)){
                items.remove(i);
                System.out.println("Item deleted from cart");
                return;
            }
        }
        System.out.println("Item not found");
    }

    public double cartTotal() {

        double cartTotal=0;

        if (items.size() == 0) {
            return 0;
        }

        double sumSubTotal=0;
        for (int i = 0; i < items.size(); i++) {
            double itemSubTotal=items.get(i).getSubTotal();
            if (items.get(i).getCartQuantity() >= 3) {
                itemSubTotal*=0.95;
                sumSubTotal+=itemSubTotal;
            }
            else{
                sumSubTotal+=itemSubTotal;
            }

        }

        boolean Engine=false;
        boolean Electrical=false;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getPart().getCategory().equalsIgnoreCase("Engine")){
                Engine=true;
            }
            if(items.get(i).getPart().getCategory().equalsIgnoreCase("Electrical")){
                Electrical=true;
            }
        }
        if(Engine && Electrical){
            cartTotal=sumSubTotal*0.90;
            return cartTotal;
        }
        return sumSubTotal;
    }

    public void checkout(){
        if(items.size()==0){
            System.out.println("The cart is empty");
            return;
        }

        for(int i=0;i<items.size();i++){
            int newQuantity=items.get(i).getPart().getQuantity()-items.get(i).getCartQuantity();
            items.get(i).getPart().setQuantity(newQuantity);
            fw.auditLogWrite("Item Sold",items.get(i).getPart().getPartCode(),items.get(i).getCartQuantity());
        }
        System.out.println("Checkout successful. Total: "+cartTotal());
        inventoryOp.saveCheckoutInventory();
        clearCart();
    }

    public void clearCart(){
        items.clear();
        System.out.println("Cart is cleared");
    }
}
