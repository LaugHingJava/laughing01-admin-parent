package com.laughing.crowd.testpojo;

import java.util.List;
import java.util.Map;

public class Student {
    private Integer stuId;
    private String stuName;
    private Integer stuAge;
    private Address address;
    private List<SchoolList> schoolLists;

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", address=" + address +
                ", schoolLists=" + schoolLists +
                ", scoreMap=" + scoreMap +
                '}';
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<SchoolList> getSchoolLists() {
        return schoolLists;
    }

    public void setSchoolLists(List<SchoolList> schoolLists) {
        this.schoolLists = schoolLists;
    }

    public Map getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map scoreMap) {
        this.scoreMap = scoreMap;
    }

    private Map scoreMap;
}
