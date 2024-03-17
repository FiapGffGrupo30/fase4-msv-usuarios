package br.fiap.gff.users.domain.usecases.service;

import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.entities.Order;
import br.fiap.gff.users.domain.entities.Phone;
import br.fiap.gff.users.domain.exceptions.CustomerException;
import br.fiap.gff.users.domain.ports.CustomerDatabasePort;
import br.fiap.gff.users.domain.ports.OrderBrokerPort;
import br.fiap.gff.users.domain.usecases.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {

    private final CustomerDatabasePort databasePort;
    private final OrderBrokerPort orderBroker;

    @Override
    public Customer getById(UUID id) {
        return databasePort.findById(id)
                .orElseThrow(() -> new CustomerException(String.format("Customer of id %s not found!", id)));
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
            throw new CustomerException(String.format("Customer of id %s not found!", c.id()));
        return databasePort.save(c);
    }

    @Override
    public void deleteById(UUID id) {
        if (!databasePort.existsById(id))
            throw new CustomerException(String.format("Customer of id %s not found!", id));
        databasePort.deleteById(id);
    }

    @Override
    public Customer addAddress(UUID id, final Address a) {
        final Customer c = getById(id);
        if (a.main() && c.hasMainAddress())
            throw new CustomerException("The customer already has a main address!");
        c.addAddress(a);
        return update(c);
    }

    @Override
    public Customer updateAddress(UUID id, final Address a) {
        final Customer c = getById(id);
        c.updateCustomerAddress(a);
        return update(c);
    }

    @Override
    public Customer deleteAddress(UUID id, final String addressId) {
        final Customer c = getById(id);
        c.removeCustomerAddress(addressId);
        return update(c);
    }

    @Override
    public Customer addPhone(UUID id, final Phone p) {
        final Customer c = getById(id);
        if (p.main() && c.person().hasMainPhone())
            throw new CustomerException("The customer already has a main phone!");
        c.person().addPhone(p);
        return update(c);
    }

    @Override
    public Customer updatePhone(UUID id, Phone p) {
        final Customer c = getById(id);
        c.updateCustomerPhone(p);
        return update(c);
    }

    @Override
    public Customer deletePhone(UUID id, String phoneId) {
        final Customer c = getById(id);
        c.removeCustomerPhone(phoneId);
        return update(c);
    }

    @Override
    public void sendOrder(UUID id, Order order) {

        if (Objects.nonNull(id)) {
            Customer c = getById(id);
            order.setUserId(id);
            order.setNickName(c.username());
            order.setAnonymous(false);
        } else {
            order.setUserId(UUID.randomUUID());
            order.setAnonymous(true);
        }

        orderBroker.sendMessage(order);
    }
}
