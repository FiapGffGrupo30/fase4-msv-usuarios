package br.fiap.gff.users.application.service;

import java.util.List;

import br.fiap.gff.users.domain.ports.CustomerDatabasePort;
import br.fiap.gff.users.domain.usecases.CustomerUseCase;
import org.springframework.stereotype.Service;

import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.exceptions.CustomerException;
import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Phone;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {

    private final CustomerDatabasePort databasePort;

    @Override
    public Customer getById(final Long id) {
        return databasePort.findById(id)
                .orElseThrow(() -> new CustomerException(String.format("Customer of id %d not found!", id)));
    }

    @Override
    public List<Customer> getAll() {
        return databasePort.findAll();
    }

    @Override
    public Customer create(final Customer c) {
        if (c.id() != null)
            throw new CustomerException("To create a customer the id must be null!");
        return databasePort.save(c);
    }

    @Override
    public Customer update(final Customer c) {
        if (c.id() == null)
            throw new CustomerException("A customer must have a id to be updated!");
        if (!databasePort.existsById(c.id()))
            throw new CustomerException(String.format("Customer of id %d not found!", c.id()));
        return databasePort.save(c);
    }

    @Override
    public void deleteById(final Long id) {
        if (!databasePort.existsById(id))
            throw new CustomerException(String.format("Customer of id %d not found!", id));
        databasePort.deleteById(id);
    }

    @Override
    public Customer addAddress(final Long id, final Address a) {
        final Customer c = getById(id);
        if (a.main() && c.hasMainAddress())
            throw new CustomerException("The customer already has a main address!");
        c.addAddress(a);
        return update(c);
    }

    @Override
    public Customer updateAddress(final Long id, final Address a) {
        final Customer c = getById(id);
        c.updateCustomerAddress(a);
        return update(c);
    }

    @Override
    public Customer deleteAddress(final Long id, final String addressId) {
        final Customer c = getById(id);
        c.removeCustomerAddress(addressId);
        return update(c);
    }

    @Override
    public Customer addPhone(final Long id, final Phone p) {
        final Customer c = getById(id);
        if (p.main() && c.person().hasMainPhone())
            throw new CustomerException("The customer already has a main phone!");
        c.person().addPhone(p);
        return update(c);
    }

    @Override
    public Customer updatePhone(Long id, Phone p) {
        final Customer c = getById(id);
        c.updateCustomerPhone(p);
        return update(c);
    }

    @Override
    public Customer deletePhone(Long id, String phoneId) {
        final Customer c = getById(id);
        c.removeCustomerPhone(phoneId);
        return update(c);
    }
}
