package br.fiap.gff.users.application.dto;

import br.fiap.gff.users.domain.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private String productId;
        private int quantity;
    }

    public Order toOrder() {
        List<Order.Item> items = this.items.stream().map(
                item -> Order.Item.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .build()
        ).toList();
        return Order.builder()
                .items(items)
                .build();
    }
}
