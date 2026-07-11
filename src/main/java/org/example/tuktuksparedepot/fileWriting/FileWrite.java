package org.example.tuktuksparedepot.fileWriting;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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




}
