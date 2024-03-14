package br.fiap.gff.users.application.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.domain.entities.Address;
import br.fiap.gff.users.domain.entities.Name;
import br.fiap.gff.users.domain.entities.Person;
import br.fiap.gff.users.domain.entities.Phone;

public record CreateCustomerDTO(
        String username,
        String firstName,
        String lastName,
        String document,
        String email) implements Serializable {
    public Customer toEntity() {
        Name n = new Name(firstName, lastName);
        List<Address> addresses = new ArrayList<>();
        List<Phone> phones = new ArrayList<>();
        Person p = new Person(n, document, email, addresses, phones);
        return new Customer(null, p, username, null);
    }
}
