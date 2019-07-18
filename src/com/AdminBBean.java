package com;

/**
 * Created by Hamza Nawaz on 6/17/2019.
 */
public class AdminBBean {
    public String username;

    public long getCnic() {
        return cnic;
    }

    public void setCnic(long cnic) {
        this.cnic = cnic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long cnic;
    public String password;

}
