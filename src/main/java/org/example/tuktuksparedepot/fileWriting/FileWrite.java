package org.example.tuktuksparedepot.fileWriting;

import org.example.tuktuksparedepot.objects.sparePart;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileWrite {

    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public void auditLogWrite(String action,String partCode){

        String timeStamp=LocalDateTime.now().format(dtf);
        String writeLine=timeStamp+" | "+action+" | "+partCode+"\n";

        try(FileWriter fw=new FileWriter("audit_log.txt",true)){
            fw.write(writeLine);
        }
        catch(IOException e){
        System.out.println("File writing failed");
        }
    }

    public void auditLogWrite(String action,String partCode,int quantity){

        String timeStamp=LocalDateTime.now().format(dtf);
        String writeLine=timeStamp+" | "+action+" | "+partCode+" | "+quantity+"\n";

        try(FileWriter fw=new FileWriter("audit_log.txt",true)){
            fw.write(writeLine);
        }
        catch(IOException e){
            System.out.println("File writing failed");
        }
    }

    public void saveInventory(ArrayList<sparePart> inventory){
        try(FileWriter si=new FileWriter("src\\main\\java\\org\\example\\tuktuksparedepot\\legacyfiles\\SavedInventory.txt")){
            for(int i=0;i< inventory.size();i++){
                si.write(inventory.get(i).toString()+"\n");
            }
        }
        catch(IOException e){
            System.out.println("File writing failed");
        }
    }




}
