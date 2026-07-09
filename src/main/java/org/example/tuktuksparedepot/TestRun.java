package org.example.tuktuksparedepot;

import org.example.tuktuksparedepot.objects.sparePart;
import org.example.tuktuksparedepot.fileHandle;

import java.util.ArrayList;

public class TestRun {
    public static void main(String[] args) {
        fileHandle fileHandle=new fileHandle();
        fileHandle.inventoryRead();
        for(int i=0;i<fileHandle.inventoryRead().size();i++){
            System.out.println(fileHandle.inventoryRead().get(i).toString());
        }


    }
}
