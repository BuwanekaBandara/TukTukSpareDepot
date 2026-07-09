package org.example.tuktuksparedepot.objects;

public class dealer {
    private String dealerId;
    private String dealerName;
    private String phoneNum;
    private String location;

    public dealer(String dealerId,String dealerName,String phoneNum,String location){
        setDealerId(dealerId);
        setDealerName(dealerName);
        setPhoneNum(phoneNum);
        setLocation(location);
    }

    public String getDealerId(){
        return dealerId;
    }
    public void setDealerId(String dealerId){
        if (dealerId==null||dealerId.trim().isEmpty()){
            System.out.println("Dealer ID cannot be empty");
        }
        else if (dealerId.trim().length()!=4 || dealerId.trim().toUpperCase().charAt(0)!='D'){
            System.out.println("Invalid dealerId.Follow this pattern (Dxxx)");
        }
        else{
            this.dealerId=dealerId.trim().toUpperCase();
        }
    }

    public String getDealerName(){
        return dealerName;
    }
    public void setDealerName(String dealerName){
        if (dealerName==null || dealerName.trim().isEmpty()){
            System.out.println("Dealer name cannot be empty");
        }
        else{
            this.dealerName=dealerName;
        }
    }

    public String getPhoneNum(){
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        if (phoneNum==null||phoneNum.isEmpty()){
            this.phoneNum="No Phone Number";
            return;
        }
        String fmtPhoneNum = phoneNum.trim();
        if(fmtPhoneNum.length()!=10){
            System.out.println("Invalid Phone Number");
            this.phoneNum="No Phone Number";
        }
        else{
            this.phoneNum=fmtPhoneNum;
        }
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        if (location==null || location.trim().isEmpty()){
            this.location="No location";
        }
        else{
            this.location=location.trim();
        }
    }

    @Override
    public String toString(){
        return dealerId+","+dealerName+","+phoneNum+","+location;
    }
}
