package br.fiap.gff.users.controllers;

import br.fiap.gff.users.controllers.dto.CreateCustomerDTO;
import br.fiap.gff.users.controllers.dto.CreatePhoneDTO;
import br.fiap.gff.users.controllers.dto.CustomerResponseDTO;
import br.fiap.gff.users.controllers.dto.RequestAddressDTO;
import br.fiap.gff.users.domain.customer.CustomerUseCase;
import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.person.Address;
import br.fiap.gff.users.domain.person.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/users/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customer;


    @GetMapping("/anonymous")
    public ResponseEntity<?> getAnonymousToken(@RequestParam String nickName) {
        return null;
    }
    

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAll() {
        List<Customer> customers = customer.getAll();
        List<CustomerResponseDTO> response = customers.stream().map(CustomerResponseDTO::fromCustomer).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable Long id) {
        Customer c = customer.getById(id);
        return ResponseEntity.ok(CustomerResponseDTO.fromCustomer(c));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody CreateCustomerDTO request) {
        Customer c = request.toEntity();
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.create(c));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<CustomerResponseDTO> addAddress(@PathVariable Long id,
            @RequestBody RequestAddressDTO request) {
        Address a = request.toAddress();
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.addAddress(id, a));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/phone")
    public ResponseEntity<CustomerResponseDTO> addPhone(@PathVariable Long id, @RequestBody CreatePhoneDTO request) {
        Phone p = request.toPhone();
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.addPhone(id, p));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/address/{addressId}")
    public ResponseEntity<CustomerResponseDTO> updateAddress(@PathVariable Long id,
            @PathVariable String addressId,
            @RequestBody RequestAddressDTO request) {
        Address a = request.toAddress(addressId);
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.updateAddress(id, a));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customer.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/address/{addressId}")
    public ResponseEntity<CustomerResponseDTO> deleteAddress(@PathVariable Long id, @PathVariable String addressId) {
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.deleteAddress(id, addressId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/phone/{phoneId}")
    public ResponseEntity<CustomerResponseDTO> deletePhone(@PathVariable Long id, @PathVariable String phoneId) {
        CustomerResponseDTO response = CustomerResponseDTO.fromCustomer(customer.deletePhone(id, phoneId));
        return ResponseEntity.ok(response);
    }
}
