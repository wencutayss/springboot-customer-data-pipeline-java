package wn.wencutayss.pipelineservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wn.wencutayss.pipelineservice.entity.Customer;
import wn.wencutayss.pipelineservice.repository.CustomerRepository;

import java.util.List;
import java.util.Map;

@Service
public class IngestionService {

    @Autowired
    private MockClient client;

    @Autowired
    private CustomerRepository repo;

    public int ingest() {
        List<Map<String, Object>> data = client.fetchAll();

        for (Map<String, Object> m : data) {
            Customer c = new Customer();
            c.setCustomerId((String) m.get("customerId"));
            c.setFirstName((String) m.get("firstName"));
            c.setLastName((String) m.get("lastName"));
            c.setEmail((String) m.get("email"));

            repo.save(c);
        }

        return data.size();
    }
}