package com.example.c196aloufi.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey (autoGenerate = true)
    private int assessmentId;
    private String assessmentTitle;
    private Date endDate;
    private String assessmentType;
    private int courseId;

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentId=" + assessmentId +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", endDate=" + endDate +
                ", assessmentType='" + assessmentType + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Assessments(int assessmentId, String assessmentTitle, Date endDate, String assessmentType, int courseId) {
        this.assessmentId = assessmentId;
        this.assessmentTitle = assessmentTitle;
        this.endDate = endDate;
        this.assessmentType = assessmentType;
        this.courseId = courseId;
    }
}

