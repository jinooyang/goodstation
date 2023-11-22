package com.enjoytrip.trip.model.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class TripStationDtoWithName {
    private Integer tripId;
    private String stationId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String stationName;
    private String trainType;
}
