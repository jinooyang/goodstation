package com.enjoytrip.trip.model.dto;

import lombok.Data;

@Data
public class TripAttractionDto {
    private Integer tripId;
    private String stationId;
    private Integer contentId;
    private Integer orderNum;
}
