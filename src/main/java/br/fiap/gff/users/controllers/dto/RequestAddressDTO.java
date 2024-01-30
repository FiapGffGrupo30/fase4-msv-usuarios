package br.fiap.gff.users.controllers.dto;

import br.fiap.gff.users.domain.person.Address;

public record RequestAddressDTO(
            String line,
            String buildingNumber,
            String complement,
            String city,
            String state,
            String zipCode,
            Boolean main
) {
    public Address toAddress() {
        return Address.create(line, buildingNumber, complement, city, state, zipCode, main);
    }

    public Address toAddress(String addressId) {
        return new Address(addressId, line, buildingNumber, complement, city, state, zipCode, main);
    }
}
