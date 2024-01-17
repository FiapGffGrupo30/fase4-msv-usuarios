package br.fiap.gff.users.controllers.dto;

import br.fiap.gff.users.domain.person.Phone;

public record CreatePhoneDTO(
        Integer areaCode,
        Integer number,
        Boolean main
) {
    public Phone toPhone() {
        return Phone.create(areaCode, number, main);
    }
}
