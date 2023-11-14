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

import java.util.Map;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<ResponseMessage> makeNewTrip(@RequestBody TripDto tripDto, @AuthenticationPrincipal SecurityUser securityUser){
        ResponseMessage rm = new ResponseMessage();
        System.out.println(securityUser);
        try{
            tripDto.setMemberId(securityUser.getUsername());
            tripService.makeNewtrip(tripDto);
            rm.setMessage("[" + tripDto.getTripName() + "] 여행 생성 완료");
            rm.setStatus(StatusEnum.OK);
            rm.setData("tripId",tripService.getLastInsertedId());
            return new ResponseEntity<>(rm, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            rm.setMessage("여행 생성 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }


    //기차역 고르는 페이지로 넘어갈때 여행 정보 받아오기
    @GetMapping("/new")
    public ResponseEntity<ResponseMessage> makeNewStationList(@RequestBody Map<String,String>map ){
        ResponseMessage rm  = new ResponseMessage();
        try{
            rm.setData("trip", tripService.findTripById(Integer.parseInt(map.get("tripId"))));
            rm.setStatus(StatusEnum.OK);
            rm.setMessage("여행 조회 성공");
            return new ResponseEntity<>(rm, HttpStatus.OK);
        }catch(Exception e){
            rm.setMessage("여행 조회 실패. 여행을 먼저 생성해주세요.");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
    }

    //trip_station에 추가하기
    //front에서 받아올 데이터 :
    @PostMapping("/station")
    public ResponseEntity<ResponseMessage> addStationsToTrip(@RequestBody Map<String,Object> map){
        ResponseMessage rm = new ResponseMessage();
        try{
            tripService.addStationToTrip(map);

            rm.setMessage("여행 저장 성공!");
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        }catch (Exception e){
//            e.printStackTrace();
            rm.setMessage("기차역 추가 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }




    }


}
