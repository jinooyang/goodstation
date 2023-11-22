package com.enjoytrip.trip.model.mapper;

import com.enjoytrip.trip.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface TripMapper {
    void insertTrip(TripDto tripDto) throws SQLException;

    Integer getLastInsertedId() throws SQLException;

    TripDto findTripById(Integer tripId) throws SQLException;

    List<TripDto> findTripByMemberId(String memberId) throws SQLException;

    void insertTripStation(List<TripStationDto> stationList) throws SQLException;

    void deleteTripStation(Integer tripId) throws SQLException;

    void insertAttraction(List<TripAttractionDto> list) throws SQLException;

    void deleteTripAttraction(Integer tripId);

    List<TripStationDtoWithName> searchTripStation(Integer tripId) throws SQLException;

    List<StationsInTripDto> searchStationsInTrip(Integer tripId) throws SQLException;

    List<TripResultDto> getResultByTripId(TripIdAndStationIdDto tripIdAndStationIdDto) throws  SQLException;
}
