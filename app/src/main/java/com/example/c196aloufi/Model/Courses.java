package com.example.c196aloufi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "courses")

public class Courses implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private Integer courseId;
    @ColumnInfo
    private String courseName;
    @ColumnInfo
    private String instructorName;
    @ColumnInfo
    private String instructorEmail;
    @ColumnInfo
    private String instructorPhone;
    @ColumnInfo
    private String courseStatus;
    @ColumnInfo
    private String startDate;
    @ColumnInfo
    private String endDate;
    @ColumnInfo
    private String courseNote;
    @ColumnInfo
    private Integer termId;

    protected Courses(Parcel in) {
        if (in.readByte() == 0) {
            courseId = null;
        }
        courseName = in.readString();
        instructorName = in.readString();
        instructorEmail = in.readString();
        instructorPhone = in.readString();
        courseStatus = in.readString();
        courseNote = in.readString();
        termId = in.readInt();
    }

    public static final Creator<Courses> CREATOR = new Creator<Courses>() {
        @Override
        public Courses createFromParcel(Parcel in) {
            return new Courses(in);
        }

        @Override
        public Courses[] newArray(int size) {
            return new Courses[size];
        }
    };

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
                ", termId='" + termId + '\'' +
                '}';
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public Integer getTermId() {return termId; }

    public void setTermId(Integer termId) { this.termId = termId; }

    public Courses(Integer courseId, String courseName, String instructorName, String instructorEmail, String instructorPhone,
                   String courseStatus, String startDate, String endDate, String courseNote, Integer termId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.courseStatus = courseStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseNote = courseNote;
        this.termId = termId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (courseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(courseId);
        }
        dest.writeString(courseName);
        dest.writeString(instructorName);
        dest.writeString(instructorEmail);
        dest.writeString(instructorPhone);
        dest.writeString(courseStatus);
        dest.writeString(courseNote);
        dest.writeInt(termId);
    }
}
