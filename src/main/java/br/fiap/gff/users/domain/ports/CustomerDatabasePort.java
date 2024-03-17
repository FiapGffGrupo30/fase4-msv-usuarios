package br.fiap.gff.users.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.fiap.gff.users.domain.entities.Customer;

public interface CustomerDatabasePort {
    Optional<Customer> findById(UUID id);

    Boolean existsById(UUID id);

    List<Customer> findAll();

    Customer save(Customer c);

    void deleteById(UUID id);
}
