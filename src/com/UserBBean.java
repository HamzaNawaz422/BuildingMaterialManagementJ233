package com;

/**
 * Created by Hamza Nawaz on 6/16/2019.
 */
public class UserBBean {
    public String fname;
    public String lname;
    public long cnic;

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getCnic() {
        return cnic;
    }

    public void setCnic(long cnic) {
        this.cnic = cnic;
    }

    public double getRemainingamount() {
        return remainingamount;
    }

    public void setRemainingamount(double remainingamount) {
        this.remainingamount = remainingamount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double totalamount;
    public double remainingamount;
    public String address;

}
