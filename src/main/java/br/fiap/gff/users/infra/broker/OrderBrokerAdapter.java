package br.fiap.gff.users.infra.broker;

import br.fiap.gff.users.domain.entities.Order;
import br.fiap.gff.users.domain.ports.OrderBrokerPort;
import br.fiap.gff.users.domain.usecases.CustomerUseCase;
import br.fiap.gff.users.infra.util.Jsonfy;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

@Component
@RequiredArgsConstructor
public class OrderBrokerAdapter implements OrderBrokerPort {

    private final SqsTemplate sqs;

    @Value("${queue-order-name}")
    private String queueOrderName;

    @Override
    public void sendMessage(Order order) {
        sqs.send(options -> options.queue(queueOrderName).payload(Jsonfy.parse(order)));
    }

    @SqsListener("${queue-order-proceeded-name}")
    public void onMessage(Message message) {
        // processando a mensagem
        Order order = Jsonfy.parse(message.body(), Order.class);
        System.out.println("Mensagem recebida: " + message.body());
    }


}
