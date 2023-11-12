//package data;
//
//
///* Java 1.8 샘플 코드 */
//
//
//import com.enjoytrip.EnjoytripApplication;
//import com.enjoytrip.station.model.dto.StationInfoDto;
//import com.google.gson.*;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//
//import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
////역 정보와 시도코드까지 가져오기 완성
//@SpringBootApplication
//@ComponentScan(basePackages = {"com.enjoytrip", "data"})
//public class ApiExplorer {
//
//
//    public static void main(String[] args) throws IOException {
//        ApplicationContext ac = SpringApplication.run(EnjoytripApplication.class, args);
//        //1. 기차역 시도 주소 이름 아이디 가져오기
//        //getIdNameAddSido(ac);
//
//        //2. 디비 들어가서 빈 주소 채워 넣기
//
//        //3. 좌표 변환
//        //changeAddToLongLat(ac);
//
//        //4. 거리 계산해서 station_tour_spot 추가하기
//
//        //관광지별 좌표를 가져온다, 같은 시 안에 있는 관광지중에서 거리가 x인 관광지를 가져온다
//        //거리 계산해서 Map<StationId, List<ContentID>>에 넣는다
//        //DB에 저장한다
////        makeStationAttractionTable(ac);
//
//        System.out.println("fin");
//
//
//
//        //출도착지기반열차정보조회(key);
//
//
//    }
//    public static double distanceInKilometerByHaversine(double x1, double y1, double x2, double y2) {
//        // Radius of the Earth in kilometers
//        final double R = 6371.0;
//
//        // Convert latitude and longitude from degrees to radians
//        double lat1Rad = Math.toRadians(x1);
//        double lon1Rad = Math.toRadians(y1);
//        double lat2Rad = Math.toRadians(x2);
//        double lon2Rad = Math.toRadians(y2);
//
//        // Differences in coordinates
//        double dLat = lat2Rad - lat1Rad;
//        double dLon = lon2Rad - lon1Rad;
//
//        // Haversine formula
//        double a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) +
//                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
//                        Math.sin(dLon / 2.0) * Math.sin(dLon / 2.0);
//
//        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        // Distance in kilometers
//        double distance = R * c;
//
//        return distance;
//    }
//    private static void makeStationAttractionTable(ApplicationContext ac) {
//        //역별 좌표를 가져온다
//        DataService serv = ac.getBean(DataService.class);
//        List<StationInfoDto> list = serv.findStationPos();
//        List<AttractionBySidoDto> attList = new ArrayList<>();
//        String sidoCode = "";
//
//        Map<String, List<String>> attractionNearStation = new HashMap<>();
//
//        for(StationInfoDto st : list){
//            if(st.getLongitude() == null)
//                continue;
//            String stationId = st.getStationId();
////            System.out.println(stationId);
//            attractionNearStation.put(stationId, new ArrayList<>());
//            if(!st.getSidoCode().equals(sidoCode)){
//                sidoCode = st.getSidoCode();
//                attList = serv.findAttractionBySidoCode(sidoCode);
//            }
//
//            Double longSt = st.getLongitude().doubleValue();
//            Double latSt = st.getLatitude().doubleValue();
//
//            for(AttractionBySidoDto att : attList){
//                Double longAtt = att.getLongitude().doubleValue();
//                Double latAtt = att.getLatitude().doubleValue();
//                //long x, lat y
//                Double dist = distanceInKilometerByHaversine(longSt, latSt,longAtt,latAtt);
////                System.out.println(dist);
//                if(dist<15.0){
//                    attractionNearStation.get(stationId).add(att.getContent_id());
//                }
//
//            }
//            if(attractionNearStation.get(stationId).size()==0)attractionNearStation.remove(stationId);
//
//        }
////        System.out.println(attractionNearStation.toString());
//        for(String stationNm : attractionNearStation.keySet()){
//            TourSpotInsertDto t = new TourSpotInsertDto();
//            t.setName(stationNm);
//            t.setContentIDs(attractionNearStation.get(stationNm));
//            serv.addStTourSpot(t);
//        }
//
//
//
//        //
//
//
//
//
//    }
//
//    private static void changeAddToLongLat(ApplicationContext ac) throws IOException {
//        DataService serv = ac.getBean(DataService.class);
//        List<StationInfoDto> list = serv.selectAll();
//        GeocoderApi geocoderApi = new GeocoderApi();
//
//        for (int i = 0; i < list.size(); i++) {
//            StationInfoDto stDto = list.get(i);
//
//            BigDecimal[] xy = geocoderApi.geoCode(stDto.getAddress());
//            if(xy[0]==null){
//                System.out.println(stDto.getStationName() + "는 변경 불가합니다");
//                continue;
//            }
//            stDto.setLongitude(xy[0]);//경도 x
//            stDto.setLatitude(xy[1]);//위도 y
//            serv.addLongLat(stDto);
//        }
//        System.out.println("fin");
//    }
//
//    private static void getIdNameAddSido(ApplicationContext ac) throws IOException {
//        String key = "19Gk40rL7q%2FZEZCBH36HX1Q9H20AzqV01x%2Bh6E%2F2BrCV%2FjARhSZ4b5oSxK%2B5hETeOreZ72eGj9ydEkRhb0l0xQ%3D%3D";
//
//        DataService serv = ac.getBean(DataService.class);
//        Map<String, StationInfoDto> allStations = new HashMap<>();
//
//        //역 주소
//        Crawling crawlStation = new Crawling();
//        Map<String, String> stationAddressMap = crawlStation.getAdress();
//        System.out.println("crawl map size : " + stationAddressMap.size());
//        //시도 정보
//        List<SidoCodeDto> sidoList = 도시코드목록조회(key);
//        changeCityNameAndCityCode(sidoList);
//
//
//        //시도 별 기차 목록 조회
//        for (int i = 0; i < sidoList.size(); i++) {
//            List<StationDto> stations = 시도별기차역목록조회(key, Integer.parseInt(sidoList.get(i).getCityCode()));//각 도시의 기차역 정보 반환
//
//            //역 아이디
//            //역 이름
//            for (int j = 0; j < stations.size(); j++) {
//                StationInfoDto stationInfoDto = new StationInfoDto();
//                String stationCode = stations.get(j).getStationCode();
//                String stationName = stations.get(j).getStationName();
//                String stationAddress = stationAddressMap.get(stationName);
//                String sidoCode = sidoList.get(i).getMyCityCode();
//                if (stationAddress == null)
//                    System.out.println("주소 자료 없음 : " + stationName);
//
//                stationInfoDto.setStationId(stationCode);
//                stationInfoDto.setStationName(stationName);
//                stationInfoDto.setAddress(stationAddress);
//                stationInfoDto.setSidoCode(sidoCode);
//                if (allStations.containsKey(stationName)) {
//                    System.out.println("중복 : " + stationName);
//                }
//
//                allStations.put(stationName, stationInfoDto);
//            }
//        }
//
//        //디비에 역아이디, 역이름, 역주소, 역 시도코드 저장하기
//        for (String st : allStations.keySet()) {
//            StationInfoDto stDto = allStations.get(st);
//            serv.insInfNoLonLat(stDto);
//        }
//        System.out.println("fin");
//        //
//        //누락된 데이터 추가
//        //
//    }
//
//    private static void changeCityNameAndCityCode(List<SidoCodeDto> list) {
//        for (int i = 0; i < list.size(); i++) {
//            String before = list.get(i).getCityName();
//            if (before.equals("서울특별시")) {
//                list.get(i).setCityName("서울");
//                list.get(i).setMyCityCode("1");
//            } else if (before.equals("세종특별시")) {
//                list.get(i).setCityName("세종특별자치시");
//                list.get(i).setMyCityCode("8");
//            } else if (before.equals("부산광역시")) {
//                list.get(i).setCityName("부산");
//                list.get(i).setMyCityCode("6");
//            } else if (before.equals("대구광역시")) {
//                list.get(i).setCityName("대구");
//                list.get(i).setMyCityCode("4");
//            } else if (before.equals("인천광역시")) {
//                list.get(i).setCityName("인천");
//                list.get(i).setMyCityCode("2");
//            } else if (before.equals("광주광역시")) {
//                list.get(i).setCityName("광주");
//                list.get(i).setMyCityCode("5");
//            } else if (before.equals("대전광역시")) {
//                list.get(i).setCityName("대전");
//                list.get(i).setMyCityCode("3");
//            } else if (before.equals("울산광역시")) {
//                list.get(i).setCityName("울산");
//                list.get(i).setMyCityCode("7");
//            } else if (before.equals("경기도")) {
//                list.get(i).setCityName("경기도");
//                list.get(i).setMyCityCode("31");
//            } else if (before.equals("강원도")) {
//                list.get(i).setCityName("강원도");
//                list.get(i).setMyCityCode("32");
//            } else if (before.equals("충청북도")) {
//                list.get(i).setCityName("충청북도");
//                list.get(i).setMyCityCode("33");
//            } else if (before.equals("충청남도")) {
//                list.get(i).setCityName("충청남도");
//                list.get(i).setMyCityCode("34");
//            } else if (before.equals("전라북도")) {
//                list.get(i).setCityName("전라북도");
//                list.get(i).setMyCityCode("37");
//            } else if (before.equals("전라남도")) {
//                list.get(i).setCityName("전라남도");
//                list.get(i).setMyCityCode("38");
//            } else if (before.equals("경상북도")) {
//                list.get(i).setCityName("경상북도");
//                list.get(i).setMyCityCode("35");
//            } else if (before.equals("경상남도")) {
//                list.get(i).setCityName("경상남도");
//                list.get(i).setMyCityCode("36");
//            }
//
//        }
//    }
//
//    public static void 출도착지기반열차정보조회(String key) throws IOException {
//
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getStrtpntAlocFndTrainInfo"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
//        urlBuilder.append("&" + URLEncoder.encode("depPlaceId", "UTF-8") + "=" + URLEncoder.encode("NAT010000", "UTF-8")); /*출발기차역ID [상세기능3. 시/도별 기차역 목록조회]에서 조회 가능*/
//        urlBuilder.append("&" + URLEncoder.encode("arrPlaceId", "UTF-8") + "=" + URLEncoder.encode("NAT011668", "UTF-8")); /*도착기차역ID [상세기능3. 시/도별 기차역 목록조회]에서 조회 가능*/
//        urlBuilder.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode("20231110", "UTF-8")); /*출발일(YYYYMMDD)*/
//        urlBuilder.append("&" + URLEncoder.encode("trainGradeCode", "UTF-8") + "=" + URLEncoder.encode("00", "UTF-8")); /*차량종류코드*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//
//
//    }
//
//
//    public static List<SidoCodeDto> 도시코드목록조회(String key) throws IOException {
//        List<SidoCodeDto> list = new ArrayList<>();
//
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
//        URL url = new URL(urlBuilder.toString());
//        System.out.println(url);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
////        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
////        System.out.println(sb.toString());
//
//        String jsonData = sb.toString();
//
//        Gson gson = new Gson();
//
//        // JSON 문자열을 JsonObject로 파싱
//        JsonElement jsonElement = JsonParser.parseString(jsonData);
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//        // 필요한 데이터 추출
//        JsonObject response = jsonObject.getAsJsonObject("response");
//        JsonObject body = response.getAsJsonObject("body");
//        JsonObject items = body.getAsJsonObject("items");
//        JsonArray itemArray = items.getAsJsonArray("item");
//
//        // citycode와 cityname를 출력
//        for (JsonElement item : itemArray) {
//            JsonObject cityInfo = item.getAsJsonObject();
//            int citycode = cityInfo.get("citycode").getAsInt();
//
//            String cityname = cityInfo.get("cityname").getAsString();
////            System.out.println("City Code: " + citycode + ", City Name: " + cityname);
//            SidoCodeDto dto = new SidoCodeDto();
//            dto.setCityName(cityname);
//            dto.setCityCode("" + citycode);
//            list.add(dto);
//        }
//        return list;
//    }
//
//    public static List<StationDto> 시도별기차역목록조회(String key, Integer sidoCode) throws IOException {
//        List<StationDto> list = new ArrayList<>();
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/TrainInfoService/getCtyAcctoTrainSttnList"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
////        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
//        urlBuilder.append("&" + URLEncoder.encode("cityCode", "UTF-8") + "=" + URLEncoder.encode(sidoCode.toString(), "UTF-8")); /*시/도 ID*/
//        URL url = new URL(urlBuilder.toString());
////        System.out.println(urlBuilder);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
////        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
////        System.out.println(sb.toString());
//        String jsonData = sb.toString();
//
//        Gson gson = new Gson();
//
//        // JSON 문자열을 JsonObject로 파싱
//        JsonElement jsonElement = JsonParser.parseString(jsonData);
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//        // 필요한 데이터 추출
//        JsonObject response = jsonObject.getAsJsonObject("response");
//        JsonObject body = response.getAsJsonObject("body");
//        JsonObject items = body.getAsJsonObject("items");
//        JsonArray itemArray = items.getAsJsonArray("item");
//
//        for (JsonElement item : itemArray) {
//            JsonObject stationInfo = item.getAsJsonObject();
//            String stationId = stationInfo.get("nodeid").toString();
//            stationId = stationId.replace("\"", "");
//            String stationName = stationInfo.get("nodename").toString();
//            stationName = stationName.replace("\"", "");
//            StationDto station = new StationDto();
//            station.setStationCode(stationId);
//
//            station.setStationName(stationName + "역");
//            list.add(station);
//        }
//        return list;
//    }
//}
