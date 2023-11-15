package com.enjoytrip.station.model.dto;

import lombok.Data;

@Data
public class StationAndSidoDto {
    private String stationId;
    private String stationName;
    private Integer sidoCode;
    private String sidoName;
}
