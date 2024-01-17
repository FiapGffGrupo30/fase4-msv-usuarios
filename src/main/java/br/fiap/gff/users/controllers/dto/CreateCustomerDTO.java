package br.fiap.gff.users.controllers.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.person.Address;
import br.fiap.gff.users.domain.person.Name;
import br.fiap.gff.users.domain.person.Person;
import br.fiap.gff.users.domain.person.Phone;

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
