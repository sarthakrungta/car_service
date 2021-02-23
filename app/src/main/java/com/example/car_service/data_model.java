package com.example.car_service;

import java.util.Date;

public class data_model {
    private int id;
    private String name;
    private String Date;
    private String carDetail;

    //CONSTRUCTOR
    public data_model(int id, String name, String carDetail, String date) {
        this.id = id;
        this.name = name;
        this.Date = date;
        this.carDetail = carDetail;
    }

    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(String carDetail) {
        this.carDetail = carDetail;
    }

    //TO-STRING METHOD
    @Override
    public String toString() {
        return "data_model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Date='" + Date + '\'' +
                ", carDetail='" + carDetail + '\'' +
                '}';
    }
}
