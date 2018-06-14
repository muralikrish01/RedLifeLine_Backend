package com.bloodbank.registration;
import com.google.firebase.database.Exclude;
public class RequestorDetails {
    private String rCity;
    private String rBloodGroup;
    private String rDate;

    public RequestorDetails(){

    }
    public RequestorDetails(String city, String bloodgroup, String date){
        rCity=city;
        rBloodGroup=bloodgroup;
        rDate=date;

    }
    public String getrCity() {
        return rCity;
    }

    public void setrCity(String rCity) {
        this.rCity = rCity;
    }

    public String getrBloodGroup() {
        return rBloodGroup;
    }

    public void setrBloodGroup(String rBloodGroup) {
        this.rBloodGroup = rBloodGroup;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }
}
