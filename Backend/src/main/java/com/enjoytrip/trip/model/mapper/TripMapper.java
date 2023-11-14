package com.enjoytrip.trip.model.mapper;

import com.enjoytrip.trip.model.dto.TripDto;
import com.enjoytrip.trip.model.dto.TripStationDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripMapper {
    void insertTrip(TripDto tripDto)throws SQLException;

    Integer getLastInsertedId() throws SQLException;

    TripDto findTripById(Integer tripId) throws SQLException;

    List<TripDto> findTripByMemberId(String memberId) throws  SQLException;

    void insertTripStation(List<TripStationDto> stationList) throws SQLException;
}
