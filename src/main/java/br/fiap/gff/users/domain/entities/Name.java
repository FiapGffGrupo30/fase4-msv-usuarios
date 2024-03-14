package br.fiap.gff.users.domain.entities;

import lombok.Builder;

@Builder
public record Name(String firstName, String lastName) {
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
