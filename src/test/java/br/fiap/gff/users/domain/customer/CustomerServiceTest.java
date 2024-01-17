package br.fiap.gff.users.domain.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.customer.exceptions.CustomerException;
import br.fiap.gff.users.domain.person.Address;
import br.fiap.gff.users.domain.person.Name;
import br.fiap.gff.users.domain.person.Person;
import br.fiap.gff.users.domain.person.Phone;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService service;

    @Mock
    CustomerRepository repository;

    @BeforeEach
    public void init() {
        service = new CustomerService(repository);
    }

    @Test
    void shouldGetACustomerById() {
        Customer c = createCustomer(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(c));
        Customer act = service.getById(1L);
        assertEquals(act, c);
    }

    @Test
    void shouldThrowExceptionWhenGetACustomerById() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> service.getById(1L));
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(createCustomer(1L));
        when(repository.findAll()).thenReturn(customers);
        List<Customer> act = service.getAll();
        assertEquals(customers, act);
    }

    @Test
    void shouldCreateACustomer() {
        Customer c = createCustomer(null);
        Customer customerWithId = createCustomer(1L);
        when(repository.save(c)).thenReturn(customerWithId);
        Customer act = service.create(c);
        assertEquals(act, customerWithId);
    }

    @Test
    void shoudlThrowExceptionWhenTryCreateCustomerWithId() {
        Customer c = createCustomer(1L);
        assertThrows(CustomerException.class, () -> service.create(c));
    }

    @Test
    void shouldUpdateACustomer() {
        Customer c = createCustomer(1L);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any(Customer.class))).thenReturn(c);
        Customer act = service.update(c);
        assertEquals(act, c);
    }

    @Test
    void shoulThrowExceptionWhenTryToUpdateCustomerWithoutId() {
        Customer c = createCustomer(null);
        assertThrows(CustomerException.class, () -> service.update(c));
    }

    @Test
    void shouldThrowExceptionWhenTryToUpdateCustomerNotExists() {
        Customer c = createCustomer(1L);
        when(repository.existsById(1L)).thenReturn(false);
        assertThrows(CustomerException.class, () -> service.update(c));
    }

    @Test
    void shouldDeleteACustomer() {
        when(repository.existsById(1L)).thenReturn(true);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTryToDeleteACustomerThatNotExists() {
        when(repository.existsById(1L)).thenReturn(false);
        assertThrows(CustomerException.class, () -> service.deleteById(1L));
    }

    @Test
    void shouldAddAddressToCustomer() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomer(1L);
        Address address = new Address(addressId, null, "5",
                null, null, null, null, true);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        when(repository.existsById(1L)).thenReturn(true);
        Customer updatedCustomer = service.addAddress(1L, address);
        assertEquals("5", getAddressBuildingNumber(updatedCustomer, addressId));
    }

    @Test
    void shouldUpdateAddress() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithAddress(addressId);
        Address updatedAddress = new Address(addressId, null, "5",
                null, null, null, null, true);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        when(repository.existsById(1L)).thenReturn(true);
        customer.updateCustomerAddress(updatedAddress);
        Customer updatedCustomer = service.updateAddress(1L, updatedAddress);
        assertEquals("5", getAddressBuildingNumber(updatedCustomer, addressId));
    }

    private String getAddressBuildingNumber(Customer customer, String addressId) {
        return customer.person().addresses()
                .stream()
                .filter(it -> Objects.equals(it.addressId(), addressId))
                .map(Address::buildingNumber)
                .findAny()
                .orElse(null);
    }

    void shoudUpdatePhone() {
        String phoneId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithPhone(phoneId);
        Phone updatedPhone = new Phone(phoneId, 79, 999999998, true);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any(Customer.class))).thenReturn(customer);
        when(repository.existsById(1L)).thenReturn(true);
        customer.updateCustomerPhone(updatedPhone);
        Customer updatedCustomer = service.updatePhone(1L, updatedPhone);
        assertEquals(999999998, getPhone(updatedCustomer, phoneId));
    }

    private Integer getPhone(Customer updatedCustomer, String phoneId) {
        return updatedCustomer.person().phones()
                .stream()
                .filter(it -> Objects.equals(it.phoneId(), phoneId))
                .map(Phone::number)
                .findAny()
                .orElse(null);
    }

    // region [DATA]
    private Customer createCustomerWithAddress(String addressId) {
        Customer c = createCustomer(1L);
        c.person().addAddress(createAddress(addressId));
        return c;
    }

    private Customer createCustomerWithPhone(String phoneId) {
        Customer c = createCustomer(1L);
        c.person().addPhone(craetePhone(phoneId));
        return c;
    }

    private Customer createCustomer(Long id) {
        Name name = new Name("Marcus", "Lima");
        Person person = new Person(name, "99999999999", "marcus@lima.com", new ArrayList<>(), new ArrayList<>());
        return new Customer(id, person, "marcus", null);
    }

    private Address createAddress(String addressId) {
        return new Address(addressId, "John Doe Street",
                "4", null, "London", "EN", "0000000", true);
    }

    private Phone craetePhone(String phoneId) {
        return new Phone(phoneId, 79, 99999999, false);
    }
    // endregion
}