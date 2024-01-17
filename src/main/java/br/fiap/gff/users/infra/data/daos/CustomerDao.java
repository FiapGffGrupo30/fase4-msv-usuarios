package br.fiap.gff.users.infra.data.daos;

import br.fiap.gff.users.infra.data.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerModel, Long> {
}
