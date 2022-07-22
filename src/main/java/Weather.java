import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class Weather {
    public static void main(String[] args) throws JsonProcessingException {
        String lat = "59.938951";
        String lon = "30.315635";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> jsonData = new HashMap<>();
        headers.setContentType(MediaType.APPLICATION_JSON);
        jsonData.put("lat", lat);
        jsonData.put("lon", lon);
        headers.add("X-Yandex-API-Key", "MYAPIKEY");
        String url = "https://api.weather.yandex.ru/v2/informers";
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class, jsonData);

        System.out.println(response.getBody());

        //Parsing
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(response.getBody());

        System.out.println("Температура в Питере: " + obj.get("fact").get("temp"));
        System.out.println("Чувствуется как: " + obj.get("fact").get("feels_like"));
    }
}
