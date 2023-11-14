package com.enjoytrip.station.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StationInfoDto {
    private String stationId;
    private String stationName;
    private String address;
    private String sidoCode;
    private BigDecimal longitude;
    private BigDecimal latitude;

}
