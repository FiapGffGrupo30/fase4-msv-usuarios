package br.fiap.gff.users.domain.ports;

import java.util.List;
import java.util.Optional;

import br.fiap.gff.users.domain.entities.Customer;

public interface CustomerDatabasePort {
    Optional<Customer> findById(Long id);

    Boolean existsById(Long id);

    List<Customer> findAll();

    Customer save(Customer c);

    void deleteById(Long id);
}
