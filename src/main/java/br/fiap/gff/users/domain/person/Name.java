package br.fiap.gff.users.domain.person;

import lombok.Builder;

@Builder
public record Name(String firstName, String lastName) {
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
