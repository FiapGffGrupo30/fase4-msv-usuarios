package br.fiap.gff.users.domain.entities;

import br.fiap.gff.users.infra.util.Coalesce;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
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
        return this.toBuilder()
                .line(Coalesce.of(a.line, this.line))
                .buildingNumber(Coalesce.of(a.buildingNumber, this.buildingNumber))
                .complement(Coalesce.of(a.complement, this.complement))
                .city(Coalesce.of(a.city, this.city))
                .state(Coalesce.of(a.state, this.state))
                .zipCode(Coalesce.of(a.zipCode, this.zipCode))
                .main(Coalesce.of(a.main, this.main))
                .build();
    }
}
