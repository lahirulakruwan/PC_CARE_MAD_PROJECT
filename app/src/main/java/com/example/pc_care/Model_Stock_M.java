package com.example.pc_care;

public class Model_Stock_M {
    private int id;
    private String itemcode;
    private String model;
    private String brand;
    private String availability;

    public Model_Stock_M(int id, String itemcode, String model, String brand, String availability) {
        this.id = id;
        this.itemcode = itemcode;
        this.model = model;
        this.brand = brand;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
