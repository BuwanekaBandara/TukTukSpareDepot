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

    public sparePart getPartByCode(String matchPart){
        for(int i=0;i<parts.size();i++){
            if(parts.get(i).getPartCode().equals(matchPart.trim().toUpperCase())){
                return parts.get(i);
            }
        }
        System.out.println("The part code does not exist");
        return null;
    }

}