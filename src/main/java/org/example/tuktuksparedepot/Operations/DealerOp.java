package org.example.tuktuksparedepot.Operations;

import org.example.tuktuksparedepot.objects.dealer;
import org.example.tuktuksparedepot.fileHandling.DealersLoad;
import java.util.ArrayList;
import java.util.Random;

public class DealerOp {
    DealersLoad read=new DealersLoad();
    ArrayList<dealer> dealers=new ArrayList<>(read.dealersRead());

    public ArrayList<dealer> sortedRandDealers(){
        ArrayList<dealer> sortedDealers =new ArrayList<>(getRandDealers());

        for(int i = 0; i< sortedDealers.size()-1; i++){
            for(int j = 0; j< sortedDealers.size()-1-i; j++){
                if(sortedDealers.get(j).getLocation().compareTo(sortedDealers.get(j+1).getLocation())>0){
                    dealer temp= sortedDealers.get(j);
                    sortedDealers.set(j, sortedDealers.get(j+1));
                    sortedDealers.set(j+1,temp);
                }
            }
        }
        return sortedDealers;
    }

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
