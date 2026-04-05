package wn.wencutayss.pipelineservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import wn.wencutayss.pipelineservice.entity.Customer;
import wn.wencutayss.pipelineservice.repository.CustomerRepository;
import wn.wencutayss.pipelineservice.service.IngestionService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PipelineController {

    @Autowired
    private IngestionService service;

    @Autowired
    private CustomerRepository repo;

    @PostMapping("/ingest")
    public Map<String, Object> ingest() {
        int count = service.ingest();
        return Map.of("status", "success", "recordsProcessed", count);
    }

    @GetMapping("/customers")
    public Page<Customer> getCustomers(
            @RequestParam int page,
            @RequestParam int limit) {
        return repo.findAll(PageRequest.of(page - 1, limit));
    }

    @GetMapping("/customers/{id}")
    public Customer getById(@PathVariable String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}