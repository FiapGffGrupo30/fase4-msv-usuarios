package br.fiap.gff.users.infra.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class AddressModel {

    @Column(name = "ADDRESS_ID", unique = true)
    private String addressId;

    @Column(name = "LINE")
    private String line;

    @Column(name = "BUILDING_NUMBER", length = 50)
    private String buildingNumber;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "CITY", length = 10)
    private String city;

    @Column(name = "STATE", length = 10)
    private String state;

    @Column(name = "ZIPCODE", length = 50)
    private String zipCode;

    private Boolean main;
}
