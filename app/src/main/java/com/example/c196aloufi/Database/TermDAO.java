package com.example.c196aloufi.Database;

import java.util.Date;

public class TermDAO {

    private int id;

    private String title;

    private Date startDate;

    private Date endDate;

    public TermDAO (int id, String title, Date startDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(){
        this.title = title;
    }

    public Date getStartDate(){ return startDate;
    }

    public void setStartDate() { this.startDate = startDate;
    }

    public Date getEndDate(){ return getEndDate();
    }

    public void setEndDate(){
        this.endDate = endDate;
    }
}
