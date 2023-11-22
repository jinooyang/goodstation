package com.enjoytrip.station.controller;

import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import com.enjoytrip.station.model.dto.StationAndSidoDto;
import com.enjoytrip.station.model.dto.StationTourSpotDto;
import com.enjoytrip.station.model.service.StationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationController {
    private final StationService stationService;

    //기차역&시도 조회
    //시도 코드, 혹은 0이 주어졌을때 기차역 목록을 보여준다
    @GetMapping("/find/{sidoCode}")
    public ResponseEntity<ResponseMessage> getStation(@PathVariable String sidoCode) {
        ResponseMessage rm = new ResponseMessage();
        try {
            List<StationAndSidoDto> list;
            if (sidoCode.equals("0")) {
                list = stationService.selectStations();
            } else {
                list = stationService.selectStationsBySidoCode(Integer.parseInt(sidoCode));
            }

            rm.setMessage("역 조회 성공");
            rm.setData("list", list);
            rm.setData("stCnt", list.size());
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setMessage("조회 실패. 역 아이디를 확인하세요");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


    //역근처 관광지 조회하기
    //역 아이디가 주어졌을 때 해당역 근처 데이터를 보여준다
    @PostMapping("/attraction")
    public ResponseEntity<ResponseMessage> getAttractionNearStation(@RequestBody Map<String,String> map) {
        ResponseMessage rm = new ResponseMessage();
        try {
            String station = map.get("station");
            String content = map.get("content");
            String search = map.get("search");
            System.out.println("station : " + station);
            System.out.println("content : " + content);
            System.out.println("search : " + search);
            List<StationTourSpotDto> list = stationService.selectAttractionNearStation(map);
            rm.setData("list" , list);
            rm.setMessage("역 근처 관광지 데이터 조회 성공!");
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setStatus(StatusEnum.FAIL);
            rm.setMessage("역 근처 관광지 데이터 조회 실패.. 역 아이디를 확인해주세요");
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


}
