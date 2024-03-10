package com.example.studentmanagementsystem.Model;

public class studentGrade {
    private Integer studentNum;
    private String year;
    private String course;
    private Double first_sem;
    private Double second_sem;
    private Double final_sem;


    public studentGrade(Integer studentNum, String year, String course, Double first_sem, Double second_sem, Double final_sem) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.first_sem = first_sem;
        this.second_sem = second_sem;
        this.final_sem = final_sem;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getFirst_sem() {
        return first_sem;
    }

    public void setFirst_sem(Double first_sem) {
        this.first_sem = first_sem;
    }

    public Double getSecond_sem() {
        return second_sem;
    }

    public void setSecond_sem(Double second_sem) {
        this.second_sem = second_sem;
    }

    public Double getFinal_sem() {
        return final_sem;
    }

    public void setFinal_sem(Double final_sem) {
        this.final_sem = final_sem;
    }
}
