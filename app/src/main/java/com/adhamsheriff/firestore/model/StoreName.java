package com.adhamsheriff.firestore.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class StoreName implements Serializable{


    private String id;
    private String storename, mobilenumber, contactperson, address;

    public StoreName() {

    }

    public StoreName(String storename, String mobilenumber, String contactperson, String address) {
        this.storename = storename;
        this.mobilenumber = mobilenumber;
        this.contactperson = contactperson;
        this.address = address;

    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}