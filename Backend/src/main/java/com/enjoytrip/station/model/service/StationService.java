package com.enjoytrip.station.model.service;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.dto.StationTourSpotDto;

import java.sql.SQLException;
import java.util.List;

public interface StationService {
    List<StationAndSidoDto> selectStations() throws SQLException;

    List<StationAndSidoDto> selectStationsBySidoCode(Integer sidoCode)throws SQLException;

    List<StationTourSpotDto> selectAttractionNearStation(String stationId)throws SQLException;

    String selectStationByName(String name) throws SQLException;
}
