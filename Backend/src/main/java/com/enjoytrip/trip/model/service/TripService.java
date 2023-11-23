package com.enjoytrip.trip.model.service;

import com.enjoytrip.trip.model.dto.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TripService {
    void makeNewtrip(TripDto tripDto)throws SQLException;

    Integer getLastInsertedId() throws SQLException;

    TripDto findTripById(Integer tripId) throws SQLException;

    List<TripDto> findTripByMemberId(String memberId) throws  SQLException;

    void addStationToTrip(Map<String, Object> map) throws Exception;

//    List<TripDto>getMemberTripList(String memberId) throws SQLException;

    void deleteTripStation(Integer tripId) throws SQLException;

    void insertAttraction(Map<String,Object> map) throws SQLException;

    void deleteAttraction(Integer tripId) throws SQLException;

    List<TripStationDtoWithName> searchTripStation(Integer tripId) throws SQLException;


    List<Map<String, Object>> getResult(Integer tripId) throws SQLException;
}
