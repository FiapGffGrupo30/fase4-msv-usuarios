package br.fiap.gff.users.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String userId;
    private String nickName;
    private boolean anonymous;
    private List<Item> items;
    private String status;

    @Data
    public static class Item {
        private String productId;
        private int quantity;
    }
}
