package br.fiap.gff.users.application.dto;

import br.fiap.gff.users.domain.entities.Customer;

import java.util.List;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String username,
        String fullName,
        String document,
        String email,
        List<AddressResponseDTO> addresses,
        List<PhoneResponseDTO> phones
) {
    public static CustomerResponseDTO fromCustomer(Customer c) {
        List<AddressResponseDTO> addresses = c.person().addresses().stream().map(AddressResponseDTO::fromAddress).toList();
        List<PhoneResponseDTO> phones = c.person().phones().stream().map(PhoneResponseDTO::fromPhone).toList();
        String fullName = c.person().name().getFullName();
        return new CustomerResponseDTO(c.id(), c.username(), fullName, c.person().document(),
                c.person().email(), addresses, phones);
    }
}
