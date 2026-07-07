package org.example.tuktuksparedepot.objects;

public class dealers {
    private String dealerId;
    private String dealerName;
    private String phoneNum;
    private String location;

    public String getDealerId(){
        return dealerId;
    }
    public void setDealerId(String dealerId){
        String fmtDealerId = dealerId.trim().toUpperCase();
        if (fmtDealerId.length()!=4 || fmtDealerId.charAt(0)!='P'){
            System.out.println("Invalid dealerId.Follow this pattern (Dxxx)");
        }
        else{
            this.dealerId=fmtDealerId;
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
            this.location=location;
        }
    }

    public dealers(String dealerId,String dealerName,String phoneNum,String location){
        setDealerId(dealerId);
        setDealerName(dealerName);
        setPhoneNum(phoneNum);
        setLocation(location);
    }

    @Override
    public String toString(){
        return dealerId+","+dealerName+","+phoneNum+","+location;
    }
}
