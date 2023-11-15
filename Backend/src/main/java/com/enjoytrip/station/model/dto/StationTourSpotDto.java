package com.enjoytrip.station.model.dto;

import lombok.Data;

@Data
public class StationTourSpotDto {
    private String stationId;
    private String contentId;
    private String title;
    private Double longitude;
    private Double latitude;
    private String address;
}
