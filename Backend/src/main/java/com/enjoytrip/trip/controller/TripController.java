package com.enjoytrip.trip.controller;

import com.enjoytrip.jwt.JwtTokenProvider;
import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import com.enjoytrip.security.SecurityUser;
import com.enjoytrip.station.model.service.StationService;
import com.enjoytrip.trip.model.dto.TrainApiDto;
import com.enjoytrip.trip.model.dto.TripDto;
import com.enjoytrip.trip.model.service.TripService;
import com.enjoytrip.util.DateConverter;
import com.google.gson.*;
import data.SidoCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final StationService stationService;

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
        System.out.println("들어왔습니다!!");
        ResponseMessage rm = new ResponseMessage();
        try {
            tripService.deleteTripStation((Integer) map.get("tripId"));

            tripService.addStationToTrip(map);

            rm.setMessage("여행 저장 성공!");
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
            tripService.deleteAttraction((Integer) map.get("tripId"));
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


    @GetMapping("/trainapi")
    public ResponseEntity<ResponseMessage> getTrainListFromApi(@RequestParam Map<String, String> map) {

        ResponseMessage rm = new ResponseMessage();
        BufferedReader rd = null;
        HttpURLConnection conn = null;
        try {
            String startStaton = map.get("startStation");
            startStaton = stationService.selectStationByName(startStaton);

            String endStation = map.get("endStation");
            endStation = stationService.selectStationByName(endStation);

            String date = map.get("date");
            date = DateConverter.convertToStringDate(date);


            String key = "19Gk40rL7q%2FZEZCBH36HX1Q9H20AzqV01x%2Bh6E%2F2BrCV%2FjARhSZ4b5oSxK%2B5hETeOreZ72eGj9ydEkRhb0l0xQ%3D%3D";
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
            urlBuilder.append("&" + URLEncoder.encode("depPlaceId", "UTF-8") + "=" + URLEncoder.encode(startStaton, "UTF-8")); /*출발기차역ID [상세기능3. 시/도별 기차역 목록조회]에서 조회 가능*/
            urlBuilder.append("&" + URLEncoder.encode("arrPlaceId", "UTF-8") + "=" + URLEncoder.encode(endStation, "UTF-8")); /*도착기차역ID [상세기능3. 시/도별 기차역 목록조회]에서 조회 가능*/
            urlBuilder.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*출발일(YYYYMMDD)*/
//            urlBuilder.append("&" + URLEncoder.encode("trainGradeCode", "UTF-8") + "=" + URLEncoder.encode("00", "UTF-8")); /*차량종류코드*/
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("전송한 URL : " + url);
            System.out.println("가져온 데이터 : " + sb);

            String jsonData = sb.toString();

            Gson gson = new Gson();

            // JSON 문자열을 JsonObject로 파싱
            JsonElement jsonElement = JsonParser.parseString(jsonData);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // 필요한 데이터 추출
            JsonObject response = jsonObject.getAsJsonObject("response");
            JsonObject body = response.getAsJsonObject("body");
            JsonObject items = body.getAsJsonObject("items");
            JsonArray itemArray = items.getAsJsonArray("item");
            List<TrainApiDto> list = new ArrayList<>();
            // citycode와 cityname를 출력
            for (JsonElement item : itemArray) {
                JsonObject trainInfo = item.getAsJsonObject();
                String adultcharge = trainInfo.get("adultcharge").getAsString();
                String arrplacename = trainInfo.get("arrplacename").getAsString();
                String arrplandtime = trainInfo.get("arrplandtime").getAsString();
                String depplacename = trainInfo.get("depplacename").getAsString();
                String depplandtime = trainInfo.get("depplandtime").getAsString();
                String traingradename = trainInfo.get("traingradename").getAsString();
                String trainno = trainInfo.get("trainno").getAsString();

                TrainApiDto dto = new TrainApiDto();

                dto.setArrplacename(arrplacename);
                dto.setArrplandtime(arrplandtime);
                dto.setDepplacename(depplacename);
                dto.setDepplandtime(depplandtime);
                dto.setAdultcharge(adultcharge);
                dto.setTraingradename(traingradename);
                dto.setTrainno(trainno);

                list.add(dto);
            }
            rm.setMessage("데이터 가져오기 성공");
            rm.setData("trainList", list);
            rm.setStatus(StatusEnum.OK);
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("FAIL");
            rm.setMessage("데이터 가져오기 실패");
            rm.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }


    }

}
