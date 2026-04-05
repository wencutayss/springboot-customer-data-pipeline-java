package wn.wencutayss.mockserver.service;

import wn.wencutayss.mockserver.model.Customer;

import java.util.Map;

public interface CustomerService {
    Map<String, Object> getCustomers(int page, int limit);

    Customer getById(String id);
}
