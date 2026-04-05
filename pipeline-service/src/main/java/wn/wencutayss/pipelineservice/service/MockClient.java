package wn.wencutayss.pipelineservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MockClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> fetchAll() {
        List<Map<String, Object>> all = new ArrayList<>();
        int page = 1;
        int limit = 10;

        while (true) {
            String url = "http://mock-server:5000/api/customers?page=" + page + "&limit=" + limit;

            Map response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

            if (data.isEmpty()) break;

            all.addAll(data);
            page++;
        }
        return all;
    }
}
