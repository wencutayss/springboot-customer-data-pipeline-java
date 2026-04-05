package wn.wencutayss.pipelineservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wn.wencutayss.pipelineservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
