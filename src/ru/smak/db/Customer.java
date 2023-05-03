package ru.smak.db;

import com.lambdaworks.crypto.SCryptUtil;

public class Customer {
    private long phone;
    private String name;
    private String password;


    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SCryptUtil.scrypt(password, 128, 16, 16);
    }

    public void setPasswordHash(String hash){
        this.password = hash;
    }

    public boolean checkPassword(String psw){
        return SCryptUtil.check(psw, password);
    }

    @Override
    public String toString(){
        return getPhone()+": "+getName()+" ("+getPassword()+")";
    }
}
