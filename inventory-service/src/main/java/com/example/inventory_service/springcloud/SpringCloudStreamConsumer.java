package com.example.inventory_service.springcloud;

import com.example.common.dto.inventory.InventoryResponeDTO;
import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.state.State;
import com.example.inventory_service.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Configuration
public class SpringCloudStreamConsumer {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private IInventoryService _inventoryService;

    @Bean
    public Consumer<OrderRequestDTO> inventoryBinding() {
        return OrderRequestDTO -> {
            System.out.println(OrderRequestDTO.toString());

            if(OrderRequestDTO.getStatus() == State.ORDER_CREATED)
            {
                Mono<InventoryResponeDTO> isExist = _inventoryService.checkInventoryExist(OrderRequestDTO.getProductId());

                if(isExist != null)
                {
                    boolean inStock = _inventoryService.deductInventory(OrderRequestDTO.getProductId(), OrderRequestDTO.getQuantity());
                    if(inStock)
                    {
                        OrderRequestDTO.setStatus(State.IN_STOCK);
                        streamBridge.send("order-out-0", OrderRequestDTO);
                    }
                }
                else {
                    OrderRequestDTO.setStatus(State.OUT_OF_STOCK);
                    streamBridge.send("order-out-0", OrderRequestDTO) ;
                }
            }
            if(OrderRequestDTO.getStatus() == State.ORDER_CANCELED)
            {
                boolean isRevert = _inventoryService.revertInventory(OrderRequestDTO.getProductId(), OrderRequestDTO.getQuantity());
                if(isRevert)
                {
                    OrderRequestDTO.setStatus(State.ORDER_CANCELED);
                    streamBridge.send("order-out-0", OrderRequestDTO) ;
                }
            }
        };
    }
}
