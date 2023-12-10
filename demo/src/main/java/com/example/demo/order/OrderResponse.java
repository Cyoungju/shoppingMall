package com.example.demo.order;

import com.example.demo.order.item.Item;
import com.example.demo.product.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

    @Data
    public static class FindByDTO {

        private Long id;
        private List<ProductDTO> productDTOS;
        private Long totalPrice;

        public FindByDTO(Order order, List<Item> itemList) {
            this.id = order.getId();
            this.productDTOS = itemList.stream()
                    .map(item -> item.getOption().getProduct()).distinct() //중복제거
                    .map(product -> new ProductDTO(itemList, product)).collect(Collectors.toList());
            this.totalPrice = itemList.stream().mapToLong(item -> item.getOption().getPrice() * item.getQuantity()).sum();
        }
        public FindByDTO(Order order){
            this.id = order.getId();
        }

        @Data
        public class ProductDTO{
            private String productName;
            private List<ItemDTO> itemDTOS;

            public ProductDTO(List<Item> items, Product product) { //List 를 DTO로 변환
                this.productName = product.getProductName();
                this.itemDTOS = items.stream()
                        .filter(item -> item.getOption().getProduct().getId() == product.getId())
                        .map(ItemDTO::new)
                        .collect(Collectors.toList());
            }

            @Data
            public class ItemDTO{
                private Long id;
                private String optionName;
                private Long quantity;
                private Long price;

                public ItemDTO(Item item) {
                    this.id = item.getId();
                    this.optionName = item.getOption().getOptionName();;
                    this.quantity = item.getQuantity();
                    this.price = item.getPrice();
                }
            }

        }
    }
}
