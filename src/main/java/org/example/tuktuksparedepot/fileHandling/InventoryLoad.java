package org.example.tuktuksparedepot.fileHandling;
import org.example.tuktuksparedepot.objects.sparePart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryLoad {

    public ArrayList<sparePart> inventoryRead(){
        ArrayList<sparePart> parts=new ArrayList<sparePart>();
        String textFile="src\\main\\java\\org\\example\\tuktuksparedepot\\legacyfiles\\inventory_legacy.txt";

        try(BufferedReader reader=new BufferedReader(new FileReader(textFile))){
            String line;
            while((line=reader.readLine())!=null){
                String [] attributes=line.split("[,|;]");
                if (attributes.length>8){
                   String delimeter=detectDel(line);
                   String[] corAttributes=line.split(delimeter);

                    String partCode=addAttribute(corAttributes,0);
                    String partName=addAttribute(corAttributes,1);
                    String brand=addAttribute(corAttributes,2);
                    double price =parsePrice(addAttribute(corAttributes,3));
                    int quantity=parseQuantity(addAttribute(corAttributes,4));
                    String category=addAttribute(corAttributes,5).toLowerCase();
                    String date=addAttribute(corAttributes,6).toLowerCase();
                    String img=addAttribute(corAttributes,7);


                    sparePart part=new sparePart(partCode,partName,brand,price,quantity,category,date,img);
                    parts.add(part);

                }
                else{
                    String partCode=addAttribute(attributes,0);
                    String partName=addAttribute(attributes,1);
                    String brand=addAttribute(attributes,2);
                    double price =parsePrice(addAttribute(attributes,3));
                    int quantity=parseQuantity(addAttribute(attributes,4));
                    String category=addAttribute(attributes,5).toLowerCase();
                    String date=addAttribute(attributes,6).toLowerCase();
                    String img=addAttribute(attributes,7);


                    sparePart part=new sparePart(partCode,partName,brand,price,quantity,category,date,img);
                    parts.add(part);
                }



            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading file");
        }
        return parts;
    }

    private String detectDel(String line){
        int comma=0;
        int pipe=0;
        int semiColon=0;

        for(int i=0;i<line.length();i++){
            if(line.charAt(i)==','){
                comma++;
            }
            if(line.charAt(i)=='|'){
                pipe++;
            }
            if(line.charAt(i)==';'){
                semiColon++;
            }
        }

        if(comma>pipe && comma>semiColon){
            return ",";
        }
        else if(pipe>comma && pipe>semiColon){
            return "|";
        }
        else{
            return ";";
        }
    }

    private String addAttribute(String[] attributes,int attIndex){
        if(attIndex>=attributes.length){
            return " ";
        }
        else{
            return attributes[attIndex].trim();
        }
    }

    private double parsePrice(String price){
        if(price==null||price.trim().isEmpty()){
            return 0;
        }
        else{
            price=price.replace("Rs." ," ").replace("Rs"," ");
            price=price.trim();
            return Double.parseDouble(price);
        }
    }

    private int parseQuantity(String quantity){
        if(quantity==null||quantity.trim().isEmpty()){
            return 0;
        }
        else{
            return Integer.parseInt(quantity);
        }
    }




}