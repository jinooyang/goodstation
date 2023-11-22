package com.enjoytrip.trip.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TripResultDto {
    private Integer tripId;
    private String stationName;
    private String stationId;
    private Date startDate;
    private Date endDate;
    private String trainType;
    private Integer contentId;
    private String title;
    private String address;
    private String img;
}
