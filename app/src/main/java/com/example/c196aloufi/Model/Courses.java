package com.example.c196aloufi.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "courses")

public class Courses {
    @PrimaryKey (autoGenerate = true)
    private int courseId;
    private String courseName;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private String courseStatus;
    private Date startDate;
    private Date endDate;
    private String courseNote;

    @Override
    public String toString() {
        return "Courses{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", courseNote='" + courseNote + '\'' +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public Courses(int courseId, String courseName, String instructorName, String instructorEmail, String instructorPhone,
                   String courseStatus, Date startDate, Date endDate, String courseNote) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.courseStatus = courseStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseNote = courseNote;
    }
}
