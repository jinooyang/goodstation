package com.enjoytrip.trip.model.service;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.mapper.StationMapper;
import com.enjoytrip.trip.model.dto.TripAttractionDto;
import com.enjoytrip.trip.model.dto.TripDto;
import com.enjoytrip.trip.model.dto.TripStationDto;
import com.enjoytrip.trip.model.dto.TripStationDtoWithName;
import com.enjoytrip.trip.model.mapper.TripMapper;
import com.enjoytrip.util.DateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripMapper tripMapper;
    private final StationMapper stationMapper;

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
        System.out.println(map.toString());
        DateConverter dateConverter = new DateConverter();
        Integer tripId = (Integer) map.get("tripId");
        List<Map<String, String>> list = (List<Map<String, String>>) map.get("stationList");
        List<StationAndSidoDto> stlist = stationMapper.selectStations();
        Map<String, String> stIdlist = new HashMap<>();
        for (StationAndSidoDto dto : stlist) {
            stIdlist.put(dto.getStationName(), dto.getStationId());
        }
        List<TripStationDto> tripStationDtoList = new ArrayList<>();

        Map<String, String> first = list.get(0);
        System.out.println("first : " + first);
        TripStationDto firstdto = new TripStationDto();
        firstdto.setTripId(tripId);
        firstdto.setStationId(stIdlist.get(first.get("depplacename") + "역"));
        tripStationDtoList.add(firstdto);

        for (int i = 1; i < list.size(); i++) {
            Map<String, String> beforetrain = list.get(i - 1);
            Map<String, String> train = list.get(i);
            TripStationDto tsdto = new TripStationDto();
            tsdto.setTripId(tripId);
            tsdto.setStationId(stIdlist.get(beforetrain.get("arrplacename") + "역"));
            tsdto.setStartDate(DateConverter.parseDateString(beforetrain.get("arrplandtime")));
            tsdto.setEndDate(DateConverter.parseDateString(train.get("depplandtime")));
            tripStationDtoList.add(tsdto);
        }

        System.out.println(tripStationDtoList);
        tripMapper.insertTripStation(tripStationDtoList);


    }

    @Override
    public void deleteTripStation(Integer tripId) throws SQLException {
        tripMapper.deleteTripStation(tripId);
    }

    @Override
    public void insertAttraction(Map<String, Object> map) throws SQLException {
        //받은 맵을 tripAttractionDto에ㅓ 넣느다(서비스에서)
        Integer tripId = (Integer) map.get("tripId");
        List<Object> jsonList = (List<Object>) map.get("list");
        List<TripAttractionDto> list = new ArrayList<>();
        int order = 0;
        for (Object o : jsonList) {
            Map<String, Object> objMap = (Map<String, Object>) o;
            TripAttractionDto tripAtt = new TripAttractionDto();
            tripAtt.setTripId(tripId);
            tripAtt.setStationId((String) objMap.get("stationId"));
            tripAtt.setContentId(Integer.parseInt( objMap.get("contentId").toString()));
            tripAtt.setOrderNum(++order);
            list.add(tripAtt);
        }
        System.out.println(list.toString());
        tripMapper.insertAttraction(list);
    }

    @Override
    public void deleteAttraction(Integer tripId) throws SQLException {
        tripMapper.deleteTripAttraction(tripId);
    }

    @Override
    public List<TripStationDtoWithName> searchTripStation(Integer tripId) throws SQLException {

        return tripMapper.searchTripStation(tripId);
    }


}
