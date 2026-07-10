package org.example.tuktuksparedepot;

import org.example.tuktuksparedepot.fileHandling.InventoryLoad;
import org.example.tuktuksparedepot.objects.sparePart;
import java.util.ArrayList;

public class InventoryOp {

    InventoryLoad read = new InventoryLoad();
    ArrayList<sparePart> parts = new ArrayList<sparePart>(read.inventoryRead());

    public ArrayList<sparePart> getAllParts(){
        return parts;
    }

    public void addPart(sparePart newPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(newPart.getPartCode())){
                System.out.println("The part code already exists");
                return;
            }
        }
        parts.add(newPart);
        System.out.println("Part added successfully");
    }

    public void deletePart(String delPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(delPart.trim().toUpperCase())) {
                parts.remove(i);
                System.out.println("Part deleted successfully");
                return;
            }
        }
        System.out.println("The part code does not exist");
    }

    public void updatePart(String partCode, String newPartName, String newPartBrand, double newPartPrice, int newPartQuantity, String newPartCategory,String newPartDate, String newPartImg){
        sparePart part=getPartByCode(partCode);
        if (part==null){
            System.out.println("The part code does not exist");
            return;
        }

        if(newPartName!=null) {
            part.setPartName(newPartName);
        }
        else{
            part.setPartName(part.getPartName());
        }

        if (newPartBrand != null){
            part.setBrand(newPartBrand);
        }
        else{
            part.setBrand(part.getBrand());
        }

        if (newPartPrice >=0){
            part.setPrice(newPartPrice);
        }
        else{
            part.setPrice(part.getPrice());
        }

        if (newPartQuantity >=0){
            part.setQuantity(newPartQuantity);
        }
        else{
            part.setQuantity(part.getQuantity());
        }

        if(newPartCategory!=null){
            part.setCategory(newPartCategory);
        }
        else{
            part.setCategory(part.getCategory());
        }

        if (newPartDate != null){
            part.setDate(newPartDate);
        }
        else{
            part.setDate(part.getDate());
        }

        if (newPartImg!=null){
            part.setImg(newPartImg);
        }
        else{
            part.setImg(part.getImg());
        }
        System.out.println("Part updated successfully");
    }

    public sparePart getPartByCode(String matchPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(matchPart.trim().toUpperCase())){
                return parts.get(i);
            }
        }
        System.out.println("The part code does not exist");
        return null;
    }

    public ArrayList<sparePart> getLowStockParts(){
        ArrayList<sparePart> lowStock= new ArrayList<sparePart>();
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getQuantity()<=3){
                lowStock.add(parts.get(i));
            }
        }
        return lowStock;
    }



}