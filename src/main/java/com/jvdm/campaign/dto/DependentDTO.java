package com.jvdm.campaign.dto;

public class DependentDTO {
    private String dependentId;
    private String dependentName;
    private int age;
    private String gender;
    private String place;

    public String getGender() {
        return gender;
    }

    public DependentDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public DependentDTO setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getDependentId() {
        return dependentId;
    }

    public DependentDTO setDependentId(String dependentId) {
        this.dependentId = dependentId;
        return this;
    }

    public String getDependentName() {
        return dependentName;
    }

    public DependentDTO setDependentName(String dependentName) {
        this.dependentName = dependentName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public DependentDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public DependentDTO() {
    }
}
