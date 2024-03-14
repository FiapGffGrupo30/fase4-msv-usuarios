package br.fiap.gff.users.domain.entities;

import br.fiap.gff.users.infra.util.Coalesce;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record Phone(
        String phoneId,
        Integer areaCode,
        Integer number,
        Boolean main) {

    public static Phone create(Integer areaCode, Integer number, Boolean main) {
        String phoneId = UUID.randomUUID().toString();
        return new Phone(phoneId, areaCode, number, main);
    }

    public String getFormattedPhone() {
        return String.format("(%d) %d", areaCode, number);
    }

    public Phone updateTo(Phone p) {
        Integer areaCode = Coalesce.of(p.areaCode(), this.areaCode);
        Integer number = Coalesce.of(p.number(), this.number);
        Boolean main = Coalesce.of(p.main(), this.main);
        return this.toBuilder().areaCode(areaCode).number(number).main(main).build();
    }
}
