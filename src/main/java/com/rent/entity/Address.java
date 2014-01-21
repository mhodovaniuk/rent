package com.rent.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Mykhailo_Hodovaniuk on 12/2/13.
 */
@Embeddable
public class Address implements Serializable {
    private String country;
    private String city;
    private String street;
    private String buildingNumber;

    public Address() {
    }

    public Address(String country, String city, String street, String buildingNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return buildingNumber;
    }

    public void setBuilding(String building) {
        this.buildingNumber = building;
    }
}
