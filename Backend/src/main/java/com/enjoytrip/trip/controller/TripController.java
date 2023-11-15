package com.enjoytrip.trip.controller;

import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import com.enjoytrip.security.SecurityUser;
import com.enjoytrip.trip.model.dto.TripDto;
import com.enjoytrip.trip.model.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    //여행 생성하기
    @PostMapping("/new")
    @Transactional
    public ResponseEntity<ResponseMessage> makeNewTrip(@RequestBody TripDto tripDto, @AuthenticationPrincipal SecurityUser securityUser) {
        ResponseMessage rm = new ResponseMessage();
        System.out.println(securityUser);
        try {
            tripDto.setMemberId(securityUser.getUsername());
            tripService.makeNewtrip(tripDto);
            rm.setMessage("[" + tripDto.getTripName() + "] 여행 생성 완료");
            rm.setStatus(StatusEnum.OK);
            rm.setData("tripId", tripService.getLastInsertedId());
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setMessage("여행 생성 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    //기차역 고르는 페이지로 넘어갈때 여행 정보 받아오기
    @GetMapping("/new")
    public ResponseEntity<ResponseMessage> makeNewStationList(@RequestBody Map<String, String> map) {
        ResponseMessage rm = new ResponseMessage();
        try {
            rm.setData("trip", tripService.findTripById(Integer.parseInt(map.get("tripId"))));
            rm.setStatus(StatusEnum.OK);
            rm.setMessage("여행 조회 성공");
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("여행 조회 실패. 여행을 먼저 생성해주세요.");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    //trip_station에 추가하기
    //front에서 받아올 데이터 :
    //{
    //"stationList": [
    //{
    //"stationId": "NAT010000",
    //"startDate": "2022-08-13",
    //"endDate": "2022-08-14"
    //},
    //{
    //"stationId": "NAT010032",
    //"startDate": "2022-08-14",
    //"endDate": "2022-08-15"
    //},
    //{
    //"stationId": "NAT010058",
    //"startDate": "2022-08-15",
    //"endDate": "2022-08-16"
    //}
    //],
    //"tripId": 1
    //}
    @PostMapping("/station")
    @Transactional
    public ResponseEntity<ResponseMessage> addStationsToTrip(@RequestBody Map<String, Object> map) {
        ResponseMessage rm = new ResponseMessage();
        try {
            tripService.deleteTripStation((Integer) map.get("tripId"));

            tripService.addStationToTrip(map);

            rm.setMessage("여행 저장 성공!");
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            rm.setMessage("기차역 추가 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    //관광지추가하기(tripAttraction)
    //fron에서 받아올 데이터 형식
    //{
    //"tripId" : 2,
    //"list" : [{
    //"stationId" : "NAT010000",
    //"contentId" : 125266
    //},{
    //"stationId" : "NAT010000",
    //"contentId" : 125410
    //},{
    //"stationId" : "NAT010032",
    //"contentId" : 125417
    //},{
    //"stationId" : "NAT010032",
    //"contentId" : 125418
    //},{
    //"stationId" : "NAT010058",
    //"contentId" : 125428
    //},{
    //"stationId" : "NAT010058",
    //"contentId" : 125455
    //}]
    //}
    @PostMapping("/attraction")
    @Transactional
    public ResponseEntity<ResponseMessage> addAttractionToTripStation(@RequestBody Map<String, Object> map) {
        ResponseMessage rm = new ResponseMessage();
        try {
            tripService.deleteAttraction((Integer)map.get("tripId"));
            tripService.insertAttraction(map);
            rm.setMessage("관광지 추가 성공");
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setMessage("관광지 추가 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }
}
