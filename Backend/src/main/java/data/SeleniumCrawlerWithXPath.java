//package data;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.List;
//
//public class SeleniumCrawlerWithXPath {
//
//    public void getAddress() {
//        // 크롬 드라이버의 경로를 설정
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jinoo\\Downloads\\chromedriver-win64/chromedriver.exe");
//
//        // 크롬 브라우저를 열기
//        WebDriver driver = new ChromeDriver();
//
//        // 웹 페이지 열기
//        driver.get("https://www.kric.go.kr/jsp/board/portal/sub05/est/estationList.jsp?pageNo=1");
//
//        // 클래스 이름이 "bd_railway_add"인 모든 tr 요소 찾기
//        List<WebElement> elements = driver.findElements(By.cssSelector("table > tbody > tr.bd_railway_add"));
//
//        // 찾은 각 요소의 텍스트 출력
//        for (WebElement element : elements) {
//            System.out.println("텍스트: " + element.getText());
//        }
//
//        // 브라우저 닫기
//        driver.quit();
//    }
//
//}