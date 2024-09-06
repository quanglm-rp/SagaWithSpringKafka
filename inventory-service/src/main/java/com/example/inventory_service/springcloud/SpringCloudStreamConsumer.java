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
    public Consumer<OrderRequestDTO> checkInventoryBinding() {
        return OrderRequestDTO -> {
            System.out.println(OrderRequestDTO.toString());
            Mono<InventoryResponeDTO> isExist = _inventoryService.checkInventoryExist(OrderRequestDTO.getProductId());
            if(isExist != null)
            {
                boolean inStock = _inventoryService.deductInventory(OrderRequestDTO.getProductId(), OrderRequestDTO.getQuantity());
                if(inStock)
                {
                    System.out.println("Success");
                    OrderRequestDTO.setStatus(State.IN_STOCK);
                    streamBridge.send("order-out-0", OrderRequestDTO);
                }
                else{
                    System.out.println("Success");
                    OrderRequestDTO.setStatus(State.OUT_OF_STOCK);
                    streamBridge.send("order-out-0", OrderRequestDTO);
                }
            }
        };
    }

    @Bean
    public Consumer<OrderRequestDTO> revertInventoryBinding() {
        return OrderRequestDTO -> {
            System.out.println(OrderRequestDTO.toString());
            OrderRequestDTO.setStatus(State.OUT_OF_STOCK);
            streamBridge.send("order-out-0", OrderRequestDTO) ;
        };
    }
}
