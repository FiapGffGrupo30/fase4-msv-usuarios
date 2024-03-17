package br.fiap.gff.users.infra.broker;

import br.fiap.gff.users.domain.entities.Order;
import br.fiap.gff.users.domain.ports.OrderBrokerPort;
import br.fiap.gff.users.infra.util.Jsonfy;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

@Component
@RequiredArgsConstructor
public class OrderBrokerAdapter implements OrderBrokerPort {

    private final SqsTemplate sqs;


    @Override
    public void sendMessage(Order order) {
        sqs.send(options -> options.queue("gff-orders").payload(Jsonfy.parse(order)));
    }

    @SqsListener(queueNames = "gff-orders")
    public void onMessage(Message message) {
        // Processar a mensagem
        System.out.println("Mensagem recebida: " + message.body());
    }


}
