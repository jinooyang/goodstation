package com.enjoytrip.trip.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TripDto {
    private Integer tripId;
    private String tripName;
    private Date startDate;
    private Date endDate;
    private String memberId;
    private String startStationId;
    private String endStationId;
}
