package br.fiap.gff.users.domain.customer;

import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.person.Address;
import br.fiap.gff.users.domain.person.Phone;

import java.util.List;

public interface CustomerUseCase {

    void getTokenForAnonymousUser(String nickName);

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
