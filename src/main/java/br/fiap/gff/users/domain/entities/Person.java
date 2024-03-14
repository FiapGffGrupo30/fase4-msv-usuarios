package br.fiap.gff.users.domain.entities;

import lombok.Builder;

import java.util.List;

@Builder
public record Person(
        Name name,
        String document,
        String email,
        List<Address> addresses,
        List<Phone> phones) {

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public boolean hasMainAddress() {
        return !addresses.stream().filter(Address::main).toList().isEmpty();
    }

    public boolean hasMainPhone() {
        return !phones.stream().filter(Phone::main).toList().isEmpty();
    }
}
