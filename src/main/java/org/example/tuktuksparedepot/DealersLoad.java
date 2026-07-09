package org.example.tuktuksparedepot;

import org.example.tuktuksparedepot.objects.dealer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DealersLoad {

    public ArrayList<dealer> dealersRead(){
        ArrayList<dealer> dealers = new ArrayList<dealer>();
        String filepath="src\\main\\java\\org\\example\\tuktuksparedepot\\legacyfiles\\dealers_legacy.txt";

        try(BufferedReader reader=new BufferedReader(new FileReader(filepath))){
            String line;
            while((line=reader.readLine())!=null){
                String[] dInfo=line.split("[,|;]");
                dealer seller=new dealer(dInfo[0],dInfo[1],dInfo[2],dInfo[3]);
                dealers.add(seller);
            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e){
            System.out.println("Error reading file");
        }

        return dealers;
    }
}
