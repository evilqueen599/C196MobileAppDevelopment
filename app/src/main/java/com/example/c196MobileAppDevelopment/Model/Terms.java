package com.example.c196MobileAppDevelopment.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms")
public class Terms implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public Integer termId;
    @ColumnInfo(name = "termName")
    public String termName;
    @ColumnInfo(name = "startDate")
    public String startDate;
    @ColumnInfo(name = "endDate")
    public String endDate;

    protected Terms(Parcel in) {
        if (in.readByte() == 0) {
            termId = null;
        } else {
            termId = in.readInt();
        }
        termName = in.readString();
    }

    public static final Creator<Terms> CREATOR = new Creator<Terms>() {
        @Override
        public Terms createFromParcel(Parcel in) {
            return new Terms(in);
        }

        @Override
        public Terms[] newArray(int size) {
            return new Terms[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Terms{" +
                "termId=" + termId +
                ", termName='" + termName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
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

    public Terms(Integer termId, String termName, String startDate, String endDate) {
        this.termId = termId;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (termId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(termId);
        }
        dest.writeString(termName);
        dest.writeString(startDate);
        dest.writeString(endDate);
    }
}
