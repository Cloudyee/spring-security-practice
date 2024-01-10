package com.eazybytes.repository;

import com.eazybytes.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JPA 프레임워크 활용
public interface CustomerRepository extends CrudRepository<Customer , Long> {
    List<Customer> findByEmail(String email);
}
