package br.fiap.gff.users.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Order {

    private UUID userId;
    private String nickName;
    private boolean anonymous;
    private List<Item> items;
    private String status;

    @Data
    @Builder
    public static class Item {
        private String productId;
        private int quantity;
    }
}
