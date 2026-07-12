package org.example.tuktuksparedepot.Operations;

import org.example.tuktuksparedepot.fileHandling.InventoryLoad;
import org.example.tuktuksparedepot.objects.sparePart;
import org.example.tuktuksparedepot.fileWriting.FileWrite;
import java.util.ArrayList;

public class InventoryOp {

    InventoryLoad read = new InventoryLoad();
    ArrayList<sparePart> parts = new ArrayList<sparePart>(read.inventoryRead());
    FileWrite fw=new FileWrite();

    public void saveCheckoutInventory(){
        fw.saveInventory(parts);
    }


    public void addPart(sparePart newPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(newPart.getPartCode())){
                System.out.println("The part code already exists");
                return;
            }
        }
        parts.add(newPart);
        fw.auditLogWrite("Part Added",newPart.getPartCode());
        fw.saveInventory(parts);
        System.out.println("Part added successfully");
    }

    public void deletePart(String delPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(delPart.trim().toUpperCase())) {
                fw.auditLogWrite("Part Deleted",parts.get(i).getPartCode());
                parts.remove(i);
                System.out.println("Part deleted successfully");
                fw.saveInventory(parts);
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
        fw.saveInventory(parts);
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

    public ArrayList<sparePart> sortedInventory(){
        ArrayList<sparePart> sortedList=new ArrayList<sparePart>(parts);
        for(int i=0;i<sortedList.size()-1;i++){
            for(int j=0;j<sortedList.size()-1-i;j++){
                if(sortedList.get(j).getCategory().compareTo(sortedList.get(j+1).getCategory())>0){
                    sparePart temp=sortedList.get(j);
                    sortedList.set(j,sortedList.get(j+1));
                    sortedList.set(j+1,temp);
                }
                else if(sortedList.get(j).getCategory().compareTo(sortedList.get(j+1).getCategory())==0){
                    int prevCode=Integer.parseInt(sortedList.get(j).getPartCode().substring(1));
                    int nextCode=Integer.parseInt(sortedList.get(j+1).getPartCode().substring(1));
                    if(prevCode>nextCode){
                        sparePart temp=sortedList.get(j);
                        sortedList.set(j,sortedList.get(j+1));
                        sortedList.set(j+1,temp);
                    }
                }
            }
        }
        return sortedList;
    }

    public ArrayList<sparePart> searchPart(String keyword,String brand,double minPrice,double maxPrice,String category){

        ArrayList<sparePart> results=new ArrayList<sparePart>();

        for(int i=0;i<parts.size();i++){

            boolean match=false;

            if(keyword!=null){
                if(parts.get(i).getPartName().contains(keyword)){
                    match=true;
                }
                else{
                    match=false;
                }
            }
            else{
                match=true;
            }

            if(brand!=null){
                if(parts.get(i).getBrand().equals(brand)){
                    match=true;
                }
                else{
                    match=false;
                }
            }
            else{
                match=true;
            }

            if(parts.get(i).getPrice()>=minPrice && parts.get(i).getPrice()<=maxPrice){
                match=true;
            }
            else{
                match=false;
            }

            if(category!=null){
                if(parts.get(i).getCategory().equals(category)){
                    match=true;
                }
                else{
                    match=false;
                }
            }
            else{
                match=true;
            }

            if(match==true){
               results.add(parts.get(i));
            }

        }
        return results;
    }


    public int totalQuantity(){
        int totalQuant=0;
        for(int i=0;i<parts.size();i++){
            totalQuant+=parts.get(i).getQuantity();
        }
        return totalQuant;
    }

    public double totalPrice(){
        double totalInventPrice=0;
        for(int i=0;i<parts.size();i++){
            totalInventPrice+=(parts.get(i).getPrice()*parts.get(i).getQuantity());
        }
        return totalInventPrice;
    }

}