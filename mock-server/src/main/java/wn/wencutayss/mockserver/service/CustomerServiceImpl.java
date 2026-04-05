package wn.wencutayss.mockserver.service;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import wn.wencutayss.mockserver.model.Customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private List<Customer> customers;

//    @Autowired
//    private ObjectMapper mapper;

    @PostConstruct
    public void loadData() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        InputStream is = getClass().getResourceAsStream("/data/customers.json");
//        customers = Arrays.asList(mapper.readValue(is, Customer[].class));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream is = getClass().getResourceAsStream("/data/customers.json");
        customers = Arrays.asList(mapper.readValue(is, Customer[].class));
    }

    public Map<String, Object> getCustomers(int page, int limit) {
        int start = (page - 1) * limit;
        int end = Math.min(start + limit, customers.size());

        List<Customer> data = customers.subList(start, end);

        return Map.of(
                "data", data,
                "total", customers.size(),
                "page", page,
                "limit", limit
        );
    }

    public Customer getById(String id) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}