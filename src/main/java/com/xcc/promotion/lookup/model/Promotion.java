package com.xcc.promotion.lookup.model;

import java.io.Serializable;
import java.sql.Date;

public class Promotion implements Serializable {
    private static final long serialVersionUID = -7788619177798333712L;

    private String eventIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;

    public String getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(String eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Promotion(String eventIdentifier, String description, Date startDate, Date endDate) {
        this.eventIdentifier = eventIdentifier;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
