package br.fiap.gff.users.application.dto;

import br.fiap.gff.users.domain.entities.Phone;

public record PhoneResponseDTO(String phoneId, String fullNumber, Boolean main) {

    public static PhoneResponseDTO fromPhone(Phone p) {
        String fullPhone = p.getFormattedPhone();
        return new PhoneResponseDTO(p.phoneId(), fullPhone, p.main());
    }
}
