package com.enjoytrip.trip.model.dto;

import lombok.Data;

import java.sql.Date;
@Data
public class TripStationDto {
    private Integer tripId;
    private String stationId;
    private Date startDate;
    private Date endDate;
}
