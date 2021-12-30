package org.but.feec.carservice.api;

public class CarDetailedView {
    private Integer carsID;
    private String model;
    private String carsNumber;
    private Integer rentCost;
    private Integer parkingID;
    private String city;
    private String street;
    private String house;
    private String brand;
    private String supportMail;
    private String supportNumber;

    public Integer getCarsID() {return carsID; }

    public void setCarsID(Integer carsID) {this.carsID = carsID;}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarsNumber() {
        return carsNumber;
    }

    public void setCarsNumber(String carsNumber) {
        this.carsNumber = carsNumber;
    }

    public Integer getRentCost() {
        return rentCost;
    }

    public void setRentCost(Integer rentCost) {
        this.rentCost = rentCost;
    }

    public Integer getParkingID() {
        return parkingID;
    }

    public void setParkingID(Integer parkingID) {
        this.parkingID = parkingID;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house){
        this.house = house;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupportMail(){
        return supportMail;
    }

    public void setSupportMail(String supportMail){
        this.supportMail = supportMail;
    }

    public String getSupportNumber(){
        return supportNumber;
    }

    public void setSupportNumber(String supportNumber){
        this.supportNumber = supportNumber;
    }
}
