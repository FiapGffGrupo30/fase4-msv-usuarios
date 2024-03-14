package br.fiap.gff.users.infra.database.repositories;

import br.fiap.gff.users.infra.database.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
