package com.enjoytrip.trip.model.service;

import com.enjoytrip.trip.model.dto.TripDto;
import com.enjoytrip.trip.model.dto.TripStationDto;
import com.enjoytrip.trip.model.mapper.TripMapper;
import com.enjoytrip.util.DateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService{
    private final TripMapper tripMapper;

    @Override
    public void makeNewtrip(TripDto tripDto) throws SQLException {
        tripMapper.insertTrip(tripDto);
    }

    @Override
    public Integer getLastInsertedId() throws SQLException {
        return tripMapper.getLastInsertedId();
    }

    @Override
    public TripDto findTripById(Integer tripId) throws SQLException {
        return tripMapper.findTripById(tripId);
    }

    @Override
    public List<TripDto> findTripByMemberId(String memberId) throws SQLException {
        return tripMapper.findTripByMemberId(memberId);
    }

    @Override
    public void addStationToTrip(Map<String, Object> map) throws Exception {
        DateConverter dateConverter = new DateConverter();
        Integer tripId = (Integer)map.get("tripId");
        List<Map<String,String>> list = (List<Map<String,String>>)map.get("stationList");

        List<TripStationDto> stationList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map<String,String> eachStationInfo = list.get(i);
            TripStationDto st = new TripStationDto();
            st.setTripId(tripId);
            st.setStationId(eachStationInfo.get("stationId"));
            st.setStartDate(dateConverter.convertStringToSqlDate(eachStationInfo.get("startDate")));
            st.setEndDate(dateConverter.convertStringToSqlDate(eachStationInfo.get("endDate")));
            stationList.add(st);
        }

        tripMapper.insertTripStation(stationList);


    }
}
