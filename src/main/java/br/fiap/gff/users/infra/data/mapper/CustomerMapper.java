package br.fiap.gff.users.infra.data.mapper;

import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.infra.data.model.CustomerModel;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "person.firstName", source = "person.name.firstName")
    @Mapping(target = "person.lastName", source = "person.name.lastName")
    CustomerModel toModel(Customer entity);

    List<CustomerModel> toModel(List<Customer> entities);

    @Mapping(target = "person.name.firstName", source = "person.firstName")
    @Mapping(target = "person.name.lastName", source = "person.lastName")
    Customer toEntity(CustomerModel model);

    List<Customer> toEntity(List<CustomerModel> models);
}
