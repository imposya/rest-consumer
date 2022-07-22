import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://reqres.in/api/users/2";
        String url2 = "https://reqres.in/api/users";
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "imposya");
        jsonToSend.put("job", "java developer");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);

        String responseGet = restTemplate.getForObject(url, String.class);
        String responsePost = restTemplate.postForObject(url2, request, String.class);
        System.out.println(responseGet);
        System.out.println(responsePost);
    }
}
