package com.enjoytrip.station.model.service;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.dto.StationTourSpotDto;
import com.enjoytrip.station.model.mapper.StationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService{
    private final StationMapper stationMapper;

    @Override
    public List<StationAndSidoDto> selectStations() throws SQLException {
        return stationMapper.selectStations();
    }

    @Override
    public List<StationAndSidoDto> selectStationsBySidoCode(Integer sidoCode) throws SQLException {

        return stationMapper.selectStationsBySidoCode(sidoCode);
    }

    @Override
    public List<StationTourSpotDto> selectAttractionNearStation(String stationId) throws SQLException {
        return stationMapper.selectAttractionNearStation(stationId);
    }

    @Override
    public String selectStationByName(String name) throws SQLException {
        return stationMapper.selectStationByName(name);
    }
}
