package com.enjoytrip.station.model.mapper;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.dto.StationTourSpotDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface StationMapper {
    List<StationAndSidoDto> selectStations() throws SQLException;

    List<StationAndSidoDto> selectStationsBySidoCode(Integer sidoCode)throws SQLException;

   List<StationTourSpotDto> selectAttractionNearStation(String stationId)throws SQLException;

   String selectStationByName(String name) throws SQLException;
}
