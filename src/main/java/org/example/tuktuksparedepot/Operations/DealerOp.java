package org.example.tuktuksparedepot.Operations;

import org.example.tuktuksparedepot.objects.dealer;
import org.example.tuktuksparedepot.fileHandling.DealersLoad;
import java.util.ArrayList;
import java.util.Random;

public class DealerOp {
    DealersLoad read=new DealersLoad();
    ArrayList<dealer> dealers=new ArrayList<>(read.dealersRead());



    private ArrayList<dealer> getRandDealers(){
        ArrayList<dealer> randDealers=new ArrayList<>();
        ArrayList<Integer> randNums=getRandNums();
        for(int i=0;i<randNums.size();i++){
            randDealers.add(dealers.get(randNums.get(i)));
        }
        return randDealers;
    }

    private ArrayList<Integer> getRandNums(){

        ArrayList<Integer> randNums=new ArrayList<>();
        Random rand=new Random();

        for(int i=0;i<4;i++){
            boolean match=false;
            while(match==false){
                int randNum=rand.nextInt(8);
                if(!randNums.contains(randNum)){
                    randNums.add(randNum);
                    match=true;
                }
            }
        }
        return randNums;
    }


}
