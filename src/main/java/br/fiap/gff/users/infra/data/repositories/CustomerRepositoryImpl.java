package br.fiap.gff.users.infra.data.repositories;

import br.fiap.gff.users.domain.customer.entities.Customer;
import br.fiap.gff.users.domain.customer.CustomerRepository;
import br.fiap.gff.users.infra.data.daos.CustomerDao;
import br.fiap.gff.users.infra.data.mapper.CustomerMapper;
import br.fiap.gff.users.infra.data.model.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerDao dao;
    private final CustomerMapper mapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return dao.findById(id).map(mapper::toEntity);
    }

    @Override
    public List<Customer> findAll() {
        return dao.findAll().stream().map(mapper::toEntity).toList();
    }

    @Override
    public Customer save(Customer c) {
        CustomerModel m = mapper.toModel(c);
        return mapper.toEntity(dao.save(m));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return dao.existsById(id);
    }

}
