package org.example.tuktuksparedepot;

import org.example.tuktuksparedepot.Operations.InventoryOp;

public class TestRun {
    public static void main(String[] args) {
        /*InventoryLoad fileHandle=new InventoryLoad();
        fileHandle.inventoryRead();
        for(int i=0;i<fileHandle.inventoryRead().size();i++){
            System.out.println(fileHandle.inventoryRead().get(i).toString());
        }*/

        /*DealersLoad read=new DealersLoad();
        System.out.println(read.dealersRead());
        System.out.println(read.dealersRead().size());
        for(int i=0;i<read.dealersRead().size();i++){
            System.out.println(read.dealersRead().get(i).toString());
        }*/

        InventoryOp read=new InventoryOp();
        for(int i=0;i<read.sortedInventory().size();i++){
            System.out.println(read.sortedInventory().get(i).getCategory()+" | "+read.sortedInventory().get(i).getPartCode());
        }


    }
}
