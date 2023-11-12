package data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

    public Map<String,String> getAdress() {
        Map<String,String> map = new HashMap<>();
        try {
            // 웹 페이지 URL
            for (int i = 1; i <= 22; i++) {

                String url = "https://www.kric.go.kr/jsp/board/portal/sub05/est/estationList.jsp?pageNo="+i;

                // 웹 페이지에 접속하여 HTML 가져오기
                Document document = Jsoup.connect(url).get();

                // tbody 내의 각 역 정보 추출
                Elements rows = document.select("tbody > tr");

                // 각 역 정보 출력
                for (Element row : rows) {
                    Element stationNameElement = row.selectFirst(".bd_railway_station a");
                    Element addressElement = row.selectFirst(".bd_railway_add");

                    String stationName = stationNameElement != null ? stationNameElement.text() : "";
                    String address = addressElement != null ? addressElement.text() : "";

//                    System.out.println("역 이름: " + stationName);
//                    System.out.println("주소: " + address);
//                    System.out.println("---");
                    map.put(stationName, address);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}