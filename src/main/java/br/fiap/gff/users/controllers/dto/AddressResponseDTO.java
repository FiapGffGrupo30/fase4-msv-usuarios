package br.fiap.gff.users.controllers.dto;

import br.fiap.gff.users.domain.person.Address;

import java.io.Serializable;

public record AddressResponseDTO(
        String addressId,
        String fullAddress,
        Boolean main) implements Serializable {
    public static AddressResponseDTO fromAddress(Address a) {
        String fullAddress = String.format("%s, %s, %s, %s - %s, %s",
                a.line(), a.buildingNumber(), a.complement(),
                a.city(), a.state(), a.zipCode());
        return new AddressResponseDTO(a.addressId(), fullAddress, a.main());
    }
}
