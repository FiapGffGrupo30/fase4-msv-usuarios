package br.fiap.gff.users.infra.database.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class PersonModel {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "EMAIL")
    private String email;

    @ElementCollection
    @CollectionTable(name = "TB_CUSTOMER_ADDRESSES")
    private List<AddressModel> addresses;

    @ElementCollection
    @CollectionTable(name = "TB_CUSTOMER_PHONES")
    private List<PhoneModel> phones;
}
