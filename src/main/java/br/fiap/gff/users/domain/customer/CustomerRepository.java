package br.fiap.gff.users.domain.customer;

import java.util.List;
import java.util.Optional;

import br.fiap.gff.users.domain.customer.entities.Customer;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);

    Boolean existsById(Long id);

    List<Customer> findAll();

    Customer save(Customer c);

    void deleteById(Long id);
}
