package br.fiap.gff.users.application.dto;

import br.fiap.gff.users.domain.entities.Phone;

public record CreatePhoneDTO(
        Integer areaCode,
        Integer number,
        Boolean main
) {
    public Phone toPhone() {
        return Phone.create(areaCode, number, main);
    }
}
