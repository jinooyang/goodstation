package data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CrawlFromNaver {
    public String getAddress(String stationName) {
        String result = null;
        try {
            // 웹 페이지 URL
            String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" + stationName;

            // 웹 페이지에 접속하여 HTML 가져오기
            Document document = Jsoup.connect(url).get();

            // 클래스 이름이 "abc"인 span 요소 선택
            Elements elements = document.select("span.hJmGYYNl4aYsXi6j5ItK");

            // 선택된 요소에서 텍스트 추출
            for (Element element : elements) {
                String text = element.text();
                result = text!=null?text:null;
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("주소 없음 : " + stationName) ;
        }
        return result;
    }
}
