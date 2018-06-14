package com.bloodbank.registration;

import com.google.firebase.database.Exclude;
public class UserDetails {
    private String uFirstName;
    private String uLastName;
    private String uCity;
    private String uBloodGroup;
    private String uEmail;
    private String uPhone;
    public UserDetails(){

    }
    public UserDetails(String firstname, String lastname, String city, String bloodgroup, String email, String phone){
        uFirstName=firstname;
        uLastName=lastname;
        uCity=city;
        uBloodGroup=bloodgroup;
        uEmail=email;
        uPhone = phone;
    }
    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }

    public String getuBloodGroup() {
        return uBloodGroup;
    }

    public void setuBloodGroup(String uBloodGroup) {
        this.uBloodGroup = uBloodGroup;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

}
