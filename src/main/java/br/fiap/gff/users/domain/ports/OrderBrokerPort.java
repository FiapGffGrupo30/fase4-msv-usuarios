package br.fiap.gff.users.domain.ports;

import br.fiap.gff.users.domain.entities.Order;

public interface OrderBrokerPort {
    void sendMessage(Order order);
}
