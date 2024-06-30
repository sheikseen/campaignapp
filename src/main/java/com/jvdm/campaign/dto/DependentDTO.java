package com.jvdm.campaign.dto;

public class DependentDTO
{
    private String dependentId;
    private String dependentName;
    private int age;
    private String phoneNo;

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public DependentDTO setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public DependentDTO() {
    }
}
