package com.nguyenminh.orderservice.service;

import com.nguyenminh.orderservice.dto.OrderLineItemDto;
import com.nguyenminh.orderservice.dto.OrderRequest;
import com.nguyenminh.orderservice.model.Order;
import com.nguyenminh.orderservice.model.OrderLineItems;
import com.nguyenminh.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.*;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {


    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        //Call inventory-service and place order
        Boolean result = webClient.get()
                .uri("http://localhost:8082/api/inventory" ,
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()
                )
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            if(TRUE.equals(result)) orderRepository.save(order);
            else {
                throw new IllegalArgumentException("Product is not in stock, try again later");
            }
    }

    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItems;

    }

}
