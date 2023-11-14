package com.enjoytrip.trip.model.service;

import com.enjoytrip.trip.model.dto.TripDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TripService {
    void makeNewtrip(TripDto tripDto)throws SQLException;

    Integer getLastInsertedId() throws SQLException;

    TripDto findTripById(Integer tripId) throws SQLException;

    List<TripDto> findTripByMemberId(String memberId) throws  SQLException;

    void addStationToTrip(Map<String, Object> map) throws Exception;
}
