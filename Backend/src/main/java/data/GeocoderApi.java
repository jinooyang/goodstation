package data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.*;
public class GeocoderApi {

    private String key = "86C5C0F2-78CA-3F86-9E14-12691BFC6BDA";

    public BigDecimal[] geoCode(String address) throws IOException {

        BigDecimal [] res = new BigDecimal[2];
        String apikey = key;
        String searchType = "road";
        String searchAddr = address;
        String epsg = "epsg:4326";

        StringBuilder urlBuilder = new StringBuilder("https://api.vworld.kr/req/address");
        urlBuilder.append("?service=address");
        urlBuilder.append("&request=getCoord");
        urlBuilder.append("&format=json");
        urlBuilder.append("&crs=" + epsg);
        urlBuilder.append("&key=" + apikey);
        urlBuilder.append("&type=" + searchType);
        urlBuilder.append("&address=").append( URLEncoder.encode(searchAddr, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
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
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        String jsonData = sb.toString();
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonData);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject result = jsonObject.getAsJsonObject("response").getAsJsonObject("result");
        if(result == null)return res;
        JsonObject point = result.getAsJsonObject("point");

        // Extract x and y values
        String x = point.get("x").getAsString();
        String y = point.get("y").getAsString();

        System.out.println("x:경도-longitude: " + x);
        System.out.println("y:위도-latitude: " + y);

        res[0] = new BigDecimal(x);
        res[1] = new BigDecimal(y);
        return res;
    }

}
