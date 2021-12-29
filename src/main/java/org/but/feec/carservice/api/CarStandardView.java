package org.but.feec.carservice.api;

public class CarStandardView {
    private Integer carsID;
    private String brand;
    private Integer parkingID;
    private String model;
    private String carsNumber;
    private Integer rentCost;

    public Integer getCarsID() {return carsID; }

    public void setCarsID(Integer carsID) {this.carsID = carsID;}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getParkingID() {
        return parkingID;
    }

    public void setParkingID(Integer parkingID) {
        this.parkingID = parkingID;
    }

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

}
