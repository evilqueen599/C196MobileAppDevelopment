package com.example.c196aloufi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessments implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private Integer assessmentId;
    @ColumnInfo
    private String assessmentTitle;
    @ColumnInfo
    private String startDate;
    @ColumnInfo
    private String endDate;
    @ColumnInfo
    private String assessmentType;
    @ColumnInfo
    private Integer courseId;

    protected Assessments(Parcel in) {
        if (in.readByte() == 0) {
            assessmentId = null;
        } else {
            assessmentId = in.readInt();
        }
        if (in.readByte() == 0) {
            courseId = null;
        }else {
            courseId = in.readInt();
        }

        endDate = in.readString();
    }

    public static final Creator<Assessments> CREATOR = new Creator<Assessments>() {
        @Override
        public Assessments createFromParcel(Parcel in) {
            return new Assessments(in);
        }

        @Override
        public Assessments[] newArray(int size) {
            return new Assessments[size];
        }
    };

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentId=" + assessmentId +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", startDate='" + startDate +
                ", endDate=" + endDate +
                ", assessmentType='" + assessmentType + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getStartDate() {return startDate;}

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Assessments(Integer assessmentId, String assessmentTitle, String startDate, String endDate, String assessmentType, Integer courseId) {
        this.assessmentId = assessmentId;
        this.assessmentTitle = assessmentTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assessmentType = assessmentType;
        this.courseId = courseId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if(assessmentId == null) {
            dest.writeByte((byte) 0);
        }else {
            dest.writeByte((byte) 1);
            dest.writeInt(assessmentId);
        }
        if (courseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(courseId);
        }
        dest.writeString(assessmentTitle);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(assessmentType);
        dest.writeInt(courseId);
    }
}

