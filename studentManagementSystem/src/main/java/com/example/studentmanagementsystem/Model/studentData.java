package com.example.studentmanagementsystem.Model;

public class studentData {
    private Integer studentNum;
    private String year;
    private String course;
    private String firstName;
    private String lastName;
    private String gender;
    private String birth;
    private String status;
    private String image;

    public studentData(Integer studentNum, String year, String course, String firstName, String lastName, String gender, String birth, String status, String image) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.image = image;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }



}

