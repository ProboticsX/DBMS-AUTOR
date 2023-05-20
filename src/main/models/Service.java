package main.models;

public class Service {
    String scId;
    String mId;
    String make;
    String sNo;
    String sName;
    String price;

    public Service() {
    }

    public Service(String scId, String mId, String make, String sNo, String sName, String price) {
        this.scId = scId;
        this.mId = mId;
        this.make = make;
        this.sNo = sNo;
        this.sName = sName;
        this.price = price;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
