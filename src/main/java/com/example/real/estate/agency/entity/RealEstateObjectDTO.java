package com.example.real.estate.agency.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RealEstateObjectDTO {
    private String address;
    private int area;
    private int price;
    private String description;
    private int buildYear;
    private int numberOfLivingRooms;
    private int numberOfBathRooms;
    private String photoURL;
    private List<User> requestUsers;

    public void addRequestUser(User user) {
        this.requestUsers.add(user);
    }

    public RealEstateObjectDTO(RealEstateObject object, ArrayList<User> users) {
        this.address = object.getAddress();
        this.area = object.getArea();
        this.price = object.getPrice();
        this.description = object.getDescription();
        this.buildYear = object.getBuildYear();
        this.numberOfLivingRooms = object.getNumberOfLivingRooms();
        this.numberOfBathRooms = object.getNumberOfBathRooms();
        this.photoURL = object.getPhotoURL();
        this.requestUsers = users;
    }
}
