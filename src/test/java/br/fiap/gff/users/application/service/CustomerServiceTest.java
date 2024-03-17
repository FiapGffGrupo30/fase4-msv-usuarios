package br.fiap.gff.users.application.service;

import br.fiap.gff.users.domain.entities.*;
import br.fiap.gff.users.domain.exceptions.CustomerException;
import br.fiap.gff.users.domain.ports.CustomerDatabasePort;
import br.fiap.gff.users.domain.ports.OrderBrokerPort;
import br.fiap.gff.users.domain.usecases.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService service;
    @Mock
    CustomerDatabasePort databasePort;
    @Mock
    OrderBrokerPort orderBrokerPort;

    @BeforeEach
    public void init() {
        service = new CustomerService(databasePort, orderBrokerPort);
    }

    @Test
    void shouldGetACustomerById() {
        Customer c = createCustomer(UUID.randomUUID());
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(c));
        Customer act = service.getById(UUID.randomUUID());
        assertEquals(act, c);
    }

    @Test
    void shouldThrowExceptionWhenGetACustomerById() {
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(createCustomer(UUID.randomUUID()));
        when(databasePort.findAll()).thenReturn(customers);
        List<Customer> act = service.getAll();
        assertEquals(customers, act);
    }

    @Test
    void shouldCreateACustomer() {
        Customer c = createCustomer(null);
        Customer customerWithId = createCustomer(UUID.randomUUID());
        when(databasePort.save(c)).thenReturn(customerWithId);
        Customer act = service.create(c);
        assertEquals(act, customerWithId);
    }

    @Test
    void shoudlThrowExceptionWhenTryCreateCustomerWithId() {
        Customer c = createCustomer(UUID.randomUUID());
        assertThrows(CustomerException.class, () -> service.create(c));
    }

    @Test
    void shouldUpdateACustomer() {
        Customer c = createCustomer(UUID.randomUUID());
        when(databasePort.existsById(c.id())).thenReturn(true);
        when(databasePort.save(any(Customer.class))).thenReturn(c);
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
        Customer c = createCustomer(UUID.randomUUID());
        when(databasePort.existsById(any(UUID.class))).thenReturn(false);
        assertThrows(CustomerException.class, () -> service.update(c));
    }

    @Test
    void shouldDeleteACustomer() {
        UUID id = UUID.randomUUID();
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        service.deleteById(id);
        verify(databasePort, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenTryToDeleteACustomerThatNotExists() {
        when(databasePort.existsById(any(UUID.class))).thenReturn(false);
        assertThrows(CustomerException.class, () -> service.deleteById(UUID.randomUUID()));
    }

    @Test
    void shouldAddAddressToCustomer() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomer(UUID.randomUUID());
        Address address = new Address(addressId, null, "5",
                null, null, null, null, true);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        Customer updatedCustomer = service.addAddress(UUID.randomUUID(), address);
        assertEquals("5", getAddressBuildingNumber(updatedCustomer, addressId));
    }

    @Test
    void shouldThrowExceptionWhenTryToAddADoubleMainAddress() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithAddress(addressId);
        Address address = new Address(addressId, null, "5",
                null, null, null, null, true);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        assertThrows(CustomerException.class, () -> service.addAddress(UUID.randomUUID(), address));
    }

    @Test
    void shouldUpdateAddress() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithAddress(addressId);
        Address updatedAddress = new Address(addressId, null, "5",
                null, null, null, null, true);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        customer.updateCustomerAddress(updatedAddress);
        Customer updatedCustomer = service.updateAddress(customer.id(), updatedAddress);
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

    @Test
    void shouldDeleteCustomerAddress() {
        String addressId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithAddress(addressId);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        Customer updatedCustomer = service.deleteAddress(UUID.randomUUID(), addressId);
        assertEquals(0, updatedCustomer.person().addresses().size());
    }

    @Test
    void shouldAddPhoneToCustomer() {
        String phoneId = UUID.randomUUID().toString();
        Customer customer = createCustomer(UUID.randomUUID());
        Phone phone = new Phone(phoneId, 79, 999999999, true);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        Customer updatedCustomer = service.addPhone(UUID.randomUUID(), phone);
        assertEquals(999999999, getPhone(updatedCustomer, phoneId));
    }

    @Test
    void shoudUpdatePhone() {
        String phoneId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithPhone(phoneId);
        Phone updatedPhone = new Phone(phoneId, 79, 999999998, true);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        customer.updateCustomerPhone(updatedPhone);
        Customer updatedCustomer = service.updatePhone(UUID.randomUUID(), updatedPhone);
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


    @Test
    void shouldDeleteCustomerPhone() {
        String phoneId = UUID.randomUUID().toString();
        Customer customer = createCustomerWithPhone(phoneId);
        when(databasePort.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(databasePort.save(any(Customer.class))).thenReturn(customer);
        when(databasePort.existsById(any(UUID.class))).thenReturn(true);
        Customer updatedCustomer = service.deletePhone(UUID.randomUUID(), phoneId);
        assertEquals(0, updatedCustomer.person().phones().size());
    }

    // region [DATA]
    private Customer createCustomerWithAddress(String addressId) {
        Customer c = createCustomer(UUID.randomUUID());
        c.person().addAddress(createAddress(addressId));
        return c;
    }

    private Customer createCustomerWithPhone(String phoneId) {
        Customer c = createCustomer(UUID.randomUUID());
        c.person().addPhone(craetePhone(phoneId));
        return c;
    }

    private Customer createCustomer(UUID id) {
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