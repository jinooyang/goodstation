package com.enjoytrip.trip.model.service;

import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.mapper.StationMapper;
import com.enjoytrip.trip.model.dto.*;
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
        Map<String,String> last = list.get(list.size()-1);
        System.out.println("first : " + first);
        TripStationDto firstdto = new TripStationDto();
        firstdto.setTripId(tripId);
        firstdto.setStationId(stIdlist.get(first.get("depplacename") + "역"));
        firstdto.setTrainType(first.get("traingradename"));
        firstdto.setEndDate(DateConverter.parseDateString(last.get("arrplandtime")));
        firstdto.setStartDate(DateConverter.parseDateString(first.get("depplandtime")));
        tripStationDtoList.add(firstdto);

        for (int i = 1; i < list.size(); i++) {
            Map<String, String> beforetrain = list.get(i - 1);
            Map<String, String> train = list.get(i);
            TripStationDto tsdto = new TripStationDto();
            tsdto.setTripId(tripId);
            tsdto.setStationId(stIdlist.get(beforetrain.get("arrplacename") + "역"));
            tsdto.setStartDate(DateConverter.parseDateString(beforetrain.get("arrplandtime")));
            System.out.println(beforetrain.get("arrplandtime") + "==>"+ tsdto.getStartDate().toString());
            tsdto.setEndDate(DateConverter.parseDateString(train.get("depplandtime")));
            System.out.println(train.get("depplandtime") + "==>"+ tsdto.getEndDate().toString());
            tsdto.setTrainType(train.get("traingradename"));
            tripStationDtoList.add(tsdto);
        }

        System.out.println(tripStationDtoList);
        tripMapper.insertTripStation(tripStationDtoList);


    }

//    @Override
//    public List<TripDto> getMemberTripList(String memberId) throws SQLException {
//
//
//        return tripMapper.getMemberTripList(memberId);
//    }

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
            tripAtt.setContentId(Integer.parseInt(objMap.get("contentId").toString()));
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

    @Override
    public List<Map<String, Object>> getResult(Integer tripId) throws SQLException {
        List<TripStationDtoWithName> stationList = tripMapper.searchTripStation(tripId);
        List<List<TripResultDto>> resultList = new ArrayList<>();
        List<Map<String,Object>> res = new ArrayList<>();
        int idx = 0;
        for(TripStationDtoWithName st : stationList){
            //각 기차역에 해당하는 정보를 불러온다
            String stationId = st.getStationId();
            TripIdAndStationIdDto tands = new TripIdAndStationIdDto();
            tands.setTripId(tripId);
            tands.setStationId(stationId);
            List<TripResultDto> result = tripMapper.getResultByTripId(tands);
            Map<String,Object> map = new HashMap<>();
            map.put("stationId", stationId);
            map.put("startDate", st.getStartDate());
            map.put("endDate", st.getEndDate());
            map.put("trainType", st.getTrainType());
            map.put("stationName", st.getStationName());
            map.put("content", result);
            res.add(map);
            idx++;
        }
        //각 역에 대한 관광 정보를 반환한다
        return res;
    }


}
