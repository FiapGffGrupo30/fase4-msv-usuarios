package br.fiap.gff.users.infra.database.mapper;

import br.fiap.gff.users.domain.entities.Customer;
import br.fiap.gff.users.infra.database.model.CustomerModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "person.firstName", source = "person.name.firstName")
    @Mapping(target = "person.lastName", source = "person.name.lastName")
    CustomerModel toModel(Customer entity);

    @InheritInverseConfiguration
    Customer toEntity(CustomerModel model);

}
