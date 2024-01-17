package br.fiap.gff.users.domain.person;

import br.fiap.gff.users.infra.util.Coalesce;
import lombok.Builder;

import java.util.UUID;

@Builder
public record Address(
        String addressId,
        String line,
        String buildingNumber,
        String complement,
        String city,
        String state,
        String zipCode,
        Boolean main
) {
    public static Address create(String line, String buildingNumber, String complement, String city, String state, String zipCode, Boolean main) {
        String addressId = UUID.randomUUID().toString();
        return new Address(addressId, line, buildingNumber, complement, city, state, zipCode, main);
    }

    public Address updateTo(Address a) {
        String line = Coalesce.of(a.line, this.line);
        String buildingNumber = Coalesce.of(a.buildingNumber, this.buildingNumber);
        String complement = Coalesce.of(a.complement, this.complement);
        String city = Coalesce.of(a.city, this.city);
        String state = Coalesce.of(a.state, this.state);
        String zipCode = Coalesce.of(a.zipCode, this.zipCode);
        Boolean main = Coalesce.of(a.main, this.main);
        return new Address(this.addressId, line, buildingNumber, complement, city, state, zipCode, main);
    }
}
