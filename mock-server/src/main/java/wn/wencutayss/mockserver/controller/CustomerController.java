package wn.wencutayss.mockserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wn.wencutayss.mockserver.model.Customer;
import wn.wencutayss.mockserver.service.CustomerService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers")
    public Map<String, Object> getCustomers(
            @RequestParam int page,
            @RequestParam int limit) {
        return service.getCustomers(page, limit);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return service.getById(id);
    }

//    @GetMapping("/health")
//    public String health() {
//        return "OK";
//    }

    @GetMapping
    public String ping() {
        return "Pong";
    }
}
