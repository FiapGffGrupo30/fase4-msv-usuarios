package br.fiap.gff.users.domain.usecases;

import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.entities.Order;
import br.fiap.gff.users.domain.entities.Phone;

import java.util.List;
import java.util.UUID;

public interface CustomerUseCase {

    Customer getById(UUID id);

    List<Customer> getAll();

    Customer create(Customer c);

    Customer update(Customer c);

    void deleteById(UUID id);

    Customer addAddress(UUID id, Address a);

    Customer updateAddress(UUID id, Address a);

    Customer deleteAddress(UUID id, String addressId);

    Customer addPhone(UUID id, Phone p);

    Customer updatePhone(UUID id, Phone p);

    Customer deletePhone(UUID id, String phoneId);

    void sendOrder(UUID id, Order order);

    void processOrder(Order order);
}
