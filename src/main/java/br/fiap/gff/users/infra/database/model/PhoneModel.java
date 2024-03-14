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
public class PhoneModel {

    @Column(name = "PHONE_ID", unique = true)
    private String phoneId;

    @Column(name = "AREA_CODE")
    private Integer areaCode;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "MAIN")
    private Boolean main;
}
