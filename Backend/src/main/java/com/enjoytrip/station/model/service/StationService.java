package com.enjoytrip.station.model.service;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.dto.StationTourSpotDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StationService {
    List<StationAndSidoDto> selectStations() throws SQLException;

    List<StationAndSidoDto> selectStationsBySidoCode(Integer sidoCode)throws SQLException;

    List<StationTourSpotDto> selectAttractionNearStation(Map<String,String> map)throws SQLException;

    String selectStationByName(String name) throws SQLException;
}
