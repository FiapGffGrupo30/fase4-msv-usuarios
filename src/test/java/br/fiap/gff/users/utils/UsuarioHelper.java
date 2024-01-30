package br.fiap.gff.users.utils;

import br.fiap.gff.users.controllers.dto.CreateCustomerDTO;
import br.fiap.gff.users.controllers.dto.CreatePhoneDTO;
import br.fiap.gff.users.controllers.dto.RequestAddressDTO;
import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.person.Address;
import br.fiap.gff.users.domain.person.Name;
import br.fiap.gff.users.domain.person.Person;
import br.fiap.gff.users.domain.person.Phone;

import java.util.ArrayList;
import java.util.List;

public class UsuarioHelper {
    public static CreateCustomerDTO gerarUsuarioRequest() {
        return new CreateCustomerDTO( "fulano123",
                                        "Fulano",
                                        "Da Silva",
                                        "11111111111",
                                        "fulano@hotmail.com");
    }
    public static Customer createCustomer(long id) {
        return new Customer(id, createPerson(), "fulano123", "12312432242" );
    }

    public static Customer createCustomerWithoutAddress(long id) {
        return new Customer(id, createPersonWithoutAddress(), "fulano123", "12312432242" );
    }

    public static Person createPerson(){
        return new Person( new Name("Fulano", "Da Silva"), "11111111111", "fulano@gmail.com", createAddress(), createPhone());
    }

    public static Person createPersonWithoutAddress(){
        return new Person( new Name("Fulano", "Da Silva"), "11111111111", "fulano@gmail.com", null, createPhone());
    }
    public static List<Address> createAddress(){
        ArrayList<Address> enderecos = new ArrayList<Address>();
        Address endereco = new Address("1", "1", "0", "povo", "sp", "sp", "111111", true);
        enderecos.add(endereco);

        return enderecos;
    }

    public static List<Phone> createPhone(){
        ArrayList<Phone> telefones = new ArrayList<Phone>();
        Phone telefone = new Phone("1", 1, 111111111, true);
        telefones.add(telefone);

        return telefones;
    }


    public static RequestAddressDTO gerarEnderecoRequest() {
        return new RequestAddressDTO( "line",
                "buildingNumber",
                "complement",
                "city",
                "state",
                "zipCode",
                false);
    }

    public static CreatePhoneDTO gerarTelefoneRequest() {
        return new CreatePhoneDTO(1,
                2,
                false);
    }
}
