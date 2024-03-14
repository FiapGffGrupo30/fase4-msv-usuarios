package br.fiap.gff.users.domain.usecases;

import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Phone;

import java.util.List;

public interface CustomerUseCase {

    Customer getById(Long id);

    List<Customer> getAll();

    Customer create(Customer c);

    Customer update(Customer c);

    void deleteById(Long id);

    Customer addAddress(Long id, Address a);

    Customer updateAddress(Long id, Address a);

    Customer deleteAddress(Long id, String addressId);

    Customer addPhone(Long id, Phone p);

    Customer updatePhone(Long id, Phone p);

    Customer deletePhone(Long id, String phoneId);
}
